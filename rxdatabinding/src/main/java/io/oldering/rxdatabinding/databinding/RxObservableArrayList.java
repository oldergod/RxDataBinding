package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableArrayList;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableArrayList}.
 */
public final class RxObservableArrayList {
  private RxObservableArrayList() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of item range change events on {@code observableArrayList}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * observableArrayList}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull
  public static <T> Observable<ObservableArrayListItemRangeChangeEvent<T>> itemRangeChangeEvents(
      @NonNull ObservableArrayList<T> observableArrayList) {
    checkNotNull(observableArrayList, "observableArrayList == null");
    return new ObservableArrayListItemRangeChangeEventObservable<>(observableArrayList);
  }

  /**
   * Create an observable of item range insertion events on {@code observableArrayList}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * observableArrayList}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull
  public static <T> Observable<ObservableArrayListItemRangeInsertionEvent<T>> itemRangeInsertionEvents(
      @NonNull ObservableArrayList<T> observableArrayList) {
    checkNotNull(observableArrayList, "observableArrayList == null");
    return new ObservableArrayListItemRangeInsertionEventObservable<>(observableArrayList);
  }

  /**
   * Create an observable of item range removal events on {@code observableArrayList}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * observableArrayList}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull
  public static <T> Observable<ObservableArrayListItemRangeRemovalEvent<T>> itemRangeRemovalEvents(
      @NonNull ObservableArrayList<T> observableArrayList) {
    checkNotNull(observableArrayList, "observableArrayList == null");
    return new ObservableArrayListItemRangeRemovalEventObservable<>(observableArrayList);
  }
}
