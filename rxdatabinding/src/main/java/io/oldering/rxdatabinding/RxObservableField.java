package io.oldering.rxdatabinding;

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
  /**
   * Create an observable of property changed events on {@code observableInt}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableInt}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static <T> Observable<? extends T> propertyChangedEvents(
      @NonNull ObservableField<T> observableField) {
    checkNotNull(observableField, "observableField == null");
    return new ObservableFieldObservable<>(observableField);
  }

  private RxObservableField() {
    throw new AssertionError("No instances.");
  }
}
