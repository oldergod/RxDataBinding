package io.oldering.rxdatabinding;

import android.databinding.ObservableBoolean;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableBoolean}.
 */
public final class RxObservableBoolean {
  /**
   * Create an observable of property changed events on {@code observableBoolean}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableBoolean}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Boolean> propertyChangedEvents(
      @NonNull ObservableBoolean observableBoolean) {
    checkNotNull(observableBoolean, "observableBoolean == null");
    return new ObservableBooleanObservable(observableBoolean);
  }

  private RxObservableBoolean() {
    throw new AssertionError("No instances.");
  }
}
