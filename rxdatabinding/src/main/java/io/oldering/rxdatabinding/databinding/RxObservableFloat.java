package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableFloat;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableFloat}.
 */
public final class RxObservableFloat {
  private RxObservableFloat() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of property change events on {@code observableFloat}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableFloat}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Float> propertyChange(@NonNull ObservableFloat observableFloat) {
    checkNotNull(observableFloat, "observableFloat == null");
    return new ObservableFloatObservable(observableFloat);
  }
}
