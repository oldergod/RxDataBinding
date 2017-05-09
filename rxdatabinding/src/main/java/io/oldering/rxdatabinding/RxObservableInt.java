package io.oldering.rxdatabinding;

import android.databinding.ObservableInt;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableInt}.
 */
public final class RxObservableInt {
  /**
   * Create an observable of property changed events on {@code observableInt}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableInt}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Integer> propertyChangedEvents(@NonNull ObservableInt observableInt) {
    checkNotNull(observableInt, "observableInt == null");
    return new ObservableIntObservable(observableInt);
  }

  private RxObservableInt() {
    throw new AssertionError("No instances.");
  }
}
