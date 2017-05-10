package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableDouble;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import io.oldering.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableDoubleTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  private final ObservableDouble observableDouble = new ObservableDouble();

  @Test @UiThreadTest public void propertyChangeEvents() {
    double value = 1;

    RecordingObserver<Double> o = new RecordingObserver<>();
    RxObservableDouble.propertyChange(observableDouble).subscribe(o);
    o.assertNoMoreEvents();

    observableDouble.set(value);
    assertEquals(value, o.takeNext(), 0.0);

    value = 3.4;
    observableDouble.set(value);
    assertEquals(value, o.takeNext(), 0.0);

    o.dispose();

    value = 5;
    observableDouble.set(value);
    o.assertNoMoreEvents();
  }
}
