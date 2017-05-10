package io.oldering.rxdatabinding;

import android.databinding.ObservableLong;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import io.oldering.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableLongTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  private final ObservableLong observableLong = new ObservableLong();

  @Test @UiThreadTest public void propertyChangeEvents() {
    long value = 1L;

    RecordingObserver<Long> o = new RecordingObserver<>();
    RxObservableLong.propertyChangedEvents(observableLong).subscribe(o);
    o.assertNoMoreEvents();

    observableLong.set(value);
    assertEquals(value, o.takeNext().longValue());

    value = 33L;
    observableLong.set(value);
    assertEquals(value, o.takeNext().longValue());

    o.dispose();

    value = 806L;
    observableLong.set(value);
    o.assertNoMoreEvents();
  }
}
