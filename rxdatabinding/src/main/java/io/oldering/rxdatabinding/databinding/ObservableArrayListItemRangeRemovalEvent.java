package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableArrayList;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class ObservableArrayListItemRangeRemovalEvent<T> {
  public static <T> ObservableArrayListItemRangeRemovalEvent<T> create(
      ObservableArrayList<T> observableArrayList, int positionStart, int itemCount) {
    return new AutoValue_ObservableArrayListItemRangeRemovalEvent<>(observableArrayList,
        positionStart, itemCount);
  }

  /** The observable from which this event occurred. */
  public abstract ObservableArrayList<T> observableArrayList();

  public abstract int positionStart();

  public abstract int itemCount();
}
