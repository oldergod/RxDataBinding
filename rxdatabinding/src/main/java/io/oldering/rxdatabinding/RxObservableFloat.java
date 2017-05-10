package io.oldering.rxdatabinding;

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
  /**
   * Create an observable of property changed events on {@code observableFloat}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableFloat}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Float> propertyChangedEvents(@NonNull ObservableFloat observableFloat) {
    checkNotNull(observableFloat, "observableFloat == null");
    return new ObservableFloatObservable(observableFloat);
  }

  private RxObservableFloat() {
    throw new AssertionError("No instances.");
  }
}
