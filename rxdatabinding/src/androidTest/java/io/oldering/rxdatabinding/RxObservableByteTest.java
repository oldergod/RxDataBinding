package io.oldering.rxdatabinding;

import android.databinding.ObservableByte;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import io.oldering.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableByteTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  private final ObservableByte observableByte = new ObservableByte();

  @Test @UiThreadTest public void propertyChangeEvents() {
    byte value = 1;

    RecordingObserver<Byte> o = new RecordingObserver<>();
    RxObservableByte.propertyChangedEvents(observableByte).subscribe(o);
    o.assertNoMoreEvents();

    observableByte.set(value);
    assertEquals(value, o.takeNext().byteValue());

    value++;
    observableByte.set(value);
    assertEquals(value, o.takeNext().byteValue());

    o.dispose();

    value++;
    observableByte.set(value);
    o.assertNoMoreEvents();
  }
}
