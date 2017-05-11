package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableBoolean;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static com.benoitquenaudon.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableBoolean}.
 */
public final class RxObservableBoolean {
  private RxObservableBoolean() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of property change events on {@code observableBoolean}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableBoolean}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Boolean> propertyChanges(@NonNull ObservableBoolean observableBoolean) {
    checkNotNull(observableBoolean, "observableBoolean == null");
    return new ObservableBooleanObservable(observableBoolean);
  }
}
