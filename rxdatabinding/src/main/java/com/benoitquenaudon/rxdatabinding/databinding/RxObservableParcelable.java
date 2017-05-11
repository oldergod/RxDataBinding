package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableParcelable;
import android.os.Parcelable;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static com.benoitquenaudon.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableParcelable<T extends  Parcelable >}.
 */
public final class RxObservableParcelable<T extends Parcelable> {
  private RxObservableParcelable() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of property change events on {@code observableParcelable}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * observableParcelable}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static <T extends Parcelable> Observable<? extends T> propertyChange(
      @NonNull ObservableParcelable<T> observableParcelable) {
    checkNotNull(observableParcelable, "observableParcelable == null");
    return new ObservableFieldObservable<>(observableParcelable);
  }
}
