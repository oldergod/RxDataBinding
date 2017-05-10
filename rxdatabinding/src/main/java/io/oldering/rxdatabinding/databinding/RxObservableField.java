package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableField;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableField<T>}.
 */
public final class RxObservableField<T> {
  private RxObservableField() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of property change events on {@code observableField}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableField}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static <T> Observable<? extends T> propertyChange(
      @NonNull ObservableField<T> observableField) {
    checkNotNull(observableField, "observableField == null");
    return new ObservableFieldObservable<>(observableField);
  }
}
