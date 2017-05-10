package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableField;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import io.oldering.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableFieldTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  @Test @UiThreadTest public void propertyChangeEvents_String() {
    final ObservableField<String> observableField = new ObservableField<>();
    String value = "databinding";

    RecordingObserver<String> o = new RecordingObserver<>();
    RxObservableField.propertyChange(observableField).subscribe(o);
    o.assertNoMoreEvents();

    observableField.set(value);
    assertEquals(value, o.takeNext());

    value += " is cool.";
    observableField.set(value);
    assertEquals(value, o.takeNext());

    o.dispose();

    value += " Yeah?";
    observableField.set(value);
    o.assertNoMoreEvents();
  }

  @Test @UiThreadTest public void propertyChangeEvents_Object() {
    final ObservableField<Object> observableField = new ObservableField<>();
    Object value;

    RecordingObserver<Object> o = new RecordingObserver<>();
    RxObservableField.propertyChange(observableField).subscribe(o);
    o.assertNoMoreEvents();

    value = new Object();
    observableField.set(value);
    assertEquals(value, o.takeNext());

    value = new Object();
    observableField.set(value);
    assertEquals(value, o.takeNext());

    o.dispose();

    value = new Object();
    observableField.set(value);
    o.assertNoMoreEvents();
  }
}
