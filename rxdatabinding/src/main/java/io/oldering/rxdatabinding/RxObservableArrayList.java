package io.oldering.rxdatabinding;

import android.databinding.ObservableArrayList;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.util.Log;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableArrayList}.
 */
public final class RxObservableArrayList {
  /**
   * Create an observable of property changed events on {@code observableArrayList}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * observableArrayList}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull
  public static <T> Observable<ObservableArrayListOnListChangedEvent<T>> listChangedEvents(
      @NonNull ObservableArrayList<T> observableArrayList) {
    checkNotNull(observableArrayList, "observableArrayList == null");
    return new ObservableArrayListObservable<>(observableArrayList);
  }

  private RxObservableArrayList() {
    throw new AssertionError("No instances.");
  }
}
