package io.oldering.rxdatabinding;

import android.databinding.ObservableArrayList;
import com.google.auto.value.AutoValue;

import static io.oldering.rxdatabinding.ObservableArrayListOnListChangedEvent.Status.ITEM_RANGE_CHANGED;
import static io.oldering.rxdatabinding.ObservableArrayListOnListChangedEvent.Status.ITEM_RANGE_INSERTED;
import static io.oldering.rxdatabinding.ObservableArrayListOnListChangedEvent.Status.ITEM_RANGE_REMOVED;

@AutoValue public abstract class ObservableArrayListOnListChangedEvent<T> {
  public static <T> ObservableArrayListOnListChangedEvent<T> itemRangeChanged(
      ObservableArrayList<T> observableArrayList, int positionStart, int itemCount) {
    Builder<T> builder = builder();
    return builder.observableArrayList(observableArrayList)
        .status(ITEM_RANGE_CHANGED)
        .positionStart(positionStart)
        .itemCount(itemCount)
        .build();
  }

  public static <T> ObservableArrayListOnListChangedEvent<T> itemRangeInserted(
      ObservableArrayList<T> observableArrayList, int positionStart, int itemCount) {
    Builder<T> builder = builder();
    return builder.observableArrayList(observableArrayList)
        .status(ITEM_RANGE_INSERTED)
        .positionStart(positionStart)
        .itemCount(itemCount)
        .build();
  }

  public static <T> ObservableArrayListOnListChangedEvent<T> itemRangeRemoved(
      ObservableArrayList<T> observableArrayList, int positionStart, int itemCount) {
    Builder<T> builder = builder();
    return builder.observableArrayList(observableArrayList)
        .status(ITEM_RANGE_REMOVED)
        .positionStart(positionStart)
        .itemCount(itemCount)
        .build();
  }

  public abstract ObservableArrayList<T> observableArrayList();

  public abstract Status status();

  public abstract int positionStart();

  public abstract int positionEnd();

  public abstract int itemCount();

  private static <T> Builder<T> builder() {
    return new AutoValue_ObservableArrayListOnListChangedEvent.Builder<T>() //
        .positionStart(-1).positionEnd(-1).itemCount(-1);
  }

  @AutoValue.Builder abstract static class Builder<T> {
    public abstract Builder<T> observableArrayList(ObservableArrayList<T> observableArrayList);

    public abstract Builder<T> status(Status status);

    public abstract Builder<T> positionStart(int positionStart);

    public abstract Builder<T> positionEnd(int positionEnd);

    public abstract Builder<T> itemCount(int itemCount);

    public abstract ObservableArrayListOnListChangedEvent<T> build();
  }

  public enum Status {
    ITEM_RANGE_CHANGED, ITEM_RANGE_INSERTED, ITEM_RANGE_REMOVED
  }
}
