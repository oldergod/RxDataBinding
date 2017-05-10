package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableArrayList;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class ObservableArrayListItemRangeChangeEvent<T> {
  public static <T> ObservableArrayListItemRangeChangeEvent<T> create(
      ObservableArrayList<T> observableArrayList, int positionStart, int itemCount) {
    return new AutoValue_ObservableArrayListItemRangeChangeEvent<>(observableArrayList,
        positionStart, itemCount);
  }

  /** The observable from which this event occurred. */
  public abstract ObservableArrayList<T> observableArrayList();

  public abstract int positionStart();

  public abstract int itemCount();
}
