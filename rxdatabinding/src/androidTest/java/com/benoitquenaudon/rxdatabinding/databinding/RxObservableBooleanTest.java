package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.benoitquenaudon.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableBooleanTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  private final ObservableBoolean observableBoolean = new ObservableBoolean();

  @SuppressWarnings("ConstantConditions") //
  @Test @UiThreadTest public void propertyChangeEvents() {
    boolean value = true;

    observableBoolean.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
      @Override public void onPropertyChanged(Observable observable, int propertyId) {
        // do something
      }
    });

    RecordingObserver<Boolean> o = new RecordingObserver<>();
    RxObservableBoolean.propertyChanges(observableBoolean).subscribe(o);
    o.assertNoMoreEvents();

    observableBoolean.set(value);
    assertEquals(value, o.takeNext());

    value = !value;
    observableBoolean.set(value);
    assertEquals(value, o.takeNext());

    o.dispose();

    value = !value;
    observableBoolean.set(value);
    o.assertNoMoreEvents();
  }
}
