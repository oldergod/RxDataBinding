package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableParcelable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.benoitquenaudon.rxdatabinding.internal.RecordingObserver;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableParcelableTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  @Test @UiThreadTest public void propertyChangeEvents() {
    final ObservableParcelable<MyParcelable> observableField = new ObservableParcelable<>();
    MyParcelable value;

    RecordingObserver<MyParcelable> o = new RecordingObserver<>();
    RxObservableParcelable.propertyChanges(observableField).subscribe(o);
    o.assertNoMoreEvents();

    value = new MyParcelable(1, "a");
    observableField.set(value);
    assertEquals(value, o.takeNext());

    value = new MyParcelable(2, "b");
    observableField.set(value);
    assertEquals(value, o.takeNext());

    o.dispose();

    value = new MyParcelable(3, "c");
    observableField.set(value);
    o.assertNoMoreEvents();
  }

  class MyParcelable implements Parcelable {
    @SuppressWarnings("unused") public final Parcelable.Creator<MyParcelable> CREATOR =
        new Parcelable.Creator<MyParcelable>() {
          @Override public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in.readInt(), in.readString());
          }

          @Override public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
          }
        };
    private int a;
    private String b;

    protected MyParcelable(int a, String b) {
      this.a = a;
      this.b = b;
    }

    @Override public int describeContents() {
      return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(a);
      dest.writeString(b);
    }
  }
}
