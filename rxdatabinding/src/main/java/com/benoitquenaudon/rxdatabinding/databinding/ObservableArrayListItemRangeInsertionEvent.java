package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableArrayList;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class ObservableArrayListItemRangeInsertionEvent<T> {
  public static <T> ObservableArrayListItemRangeInsertionEvent<T> create(
      ObservableArrayList<T> observableArrayList, int positionStart, int itemCount) {
    return new AutoValue_ObservableArrayListItemRangeInsertionEvent<>(observableArrayList,
        positionStart, itemCount);
  }

  ObservableArrayListItemRangeInsertionEvent() {
  }

  /** The observable from which this event occurred. */
  public abstract ObservableArrayList<T> observableArrayList();
  public abstract int positionStart();
  public abstract int itemCount();
}
