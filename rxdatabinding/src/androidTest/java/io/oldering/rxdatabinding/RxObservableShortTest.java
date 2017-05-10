package io.oldering.rxdatabinding;

import android.databinding.ObservableShort;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import io.oldering.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableShortTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  private final ObservableShort observableShort = new ObservableShort();

  @Test @UiThreadTest public void propertyChangeEvents() {
    short value = -1;

    RecordingObserver<Short> o = new RecordingObserver<>();
    RxObservableShort.propertyChangedEvents(observableShort).subscribe(o);
    o.assertNoMoreEvents();

    observableShort.set(value);
    assertEquals(value, o.takeNext().shortValue());

    value = 2;
    observableShort.set(value);
    assertEquals(value, o.takeNext().shortValue());

    o.dispose();

    value = -3;
    observableShort.set(value);
    o.assertNoMoreEvents();
  }
}
