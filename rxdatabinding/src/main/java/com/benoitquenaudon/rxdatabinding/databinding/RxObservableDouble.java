package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableDouble;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static com.benoitquenaudon.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableDouble}.
 */
public final class RxObservableDouble {
  private RxObservableDouble() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of property change events on {@code observableDouble}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableDouble}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Double> propertyChange(@NonNull ObservableDouble observableDouble) {
    checkNotNull(observableDouble, "observableDouble == null");
    return new ObservableDoubleObservable(observableDouble);
  }
}
