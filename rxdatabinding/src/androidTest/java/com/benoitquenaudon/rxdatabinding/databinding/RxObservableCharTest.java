package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableChar;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.benoitquenaudon.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableCharTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  private final ObservableChar observableChar = new ObservableChar();

  @Test @UiThreadTest public void propertyChangeEvents() {
    char value = 'f';

    RecordingObserver<Character> o = new RecordingObserver<>();
    RxObservableChar.propertyChanges(observableChar).subscribe(o);
    o.assertNoMoreEvents();

    observableChar.set(value);
    assertEquals(value, o.takeNext().charValue());

    value = 'a';
    observableChar.set(value);
    assertEquals(value, o.takeNext().charValue());

    o.dispose();

    value = 'b';
    observableChar.set(value);
    o.assertNoMoreEvents();
  }
}
