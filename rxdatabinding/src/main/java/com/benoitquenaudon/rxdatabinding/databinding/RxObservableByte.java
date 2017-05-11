package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableByte;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static com.benoitquenaudon.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableByte}.
 */
public final class RxObservableByte {
  private RxObservableByte() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of property change events on {@code observableByte}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableByte}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Byte> propertyChange(@NonNull ObservableByte observableByte) {
    checkNotNull(observableByte, "observableByte == null");
    return new ObservableByteObservable(observableByte);
  }
}
