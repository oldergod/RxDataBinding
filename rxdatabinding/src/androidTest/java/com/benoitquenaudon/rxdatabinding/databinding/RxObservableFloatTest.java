package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableFloat;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.benoitquenaudon.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableFloatTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  private final ObservableFloat observableFloat = new ObservableFloat();

  @Test @UiThreadTest public void propertyChangeEvents() {
    float value = 1F;

    RecordingObserver<Float> o = new RecordingObserver<>();
    RxObservableFloat.propertyChange(observableFloat).subscribe(o);
    o.assertNoMoreEvents();

    observableFloat.set(value);
    assertEquals(value, o.takeNext(), 0F);

    value = 2.3F;
    observableFloat.set(value);
    assertEquals(value, o.takeNext(), 0F);

    o.dispose();

    value = 3;
    observableFloat.set(value);
    o.assertNoMoreEvents();
  }
}
