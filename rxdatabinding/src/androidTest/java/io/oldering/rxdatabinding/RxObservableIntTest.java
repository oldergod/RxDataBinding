package io.oldering.rxdatabinding;

import android.databinding.ObservableInt;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import io.oldering.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableIntTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  private final ObservableInt observableInt = new ObservableInt();

  @Test @UiThreadTest public void propertyChangeEvents() {
    int value = 1;

    RecordingObserver<Integer> o = new RecordingObserver<>();
    RxObservableInt.propertyChangedEvents(observableInt).subscribe(o);
    o.assertNoMoreEvents();

    observableInt.set(value);
    assertEquals(value, o.takeNext().intValue());

    value++;
    observableInt.set(value);
    assertEquals(value, o.takeNext().intValue());

    o.dispose();

    value++;
    observableInt.set(value);
    o.assertNoMoreEvents();
  }
}
