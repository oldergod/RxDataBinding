package io.oldering.rxdatabinding;

import android.databinding.ObservableLong;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableLong}.
 */
public final class RxObservableLong {
  /**
   * Create an observable of property changed events on {@code observableLong}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableLong}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Long> propertyChangedEvents(@NonNull ObservableLong observableLong) {
    checkNotNull(observableLong, "observableLong == null");
    return new ObservableLongObservable(observableLong);
  }

  private RxObservableLong() {
    throw new AssertionError("No instances.");
  }
}
