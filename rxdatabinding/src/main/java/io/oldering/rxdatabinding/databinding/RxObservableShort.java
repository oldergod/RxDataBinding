package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableShort;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableShort}.
 */
public final class RxObservableShort {
  private RxObservableShort() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of property change events on {@code observableShort}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableShort}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Short> propertyChange(@NonNull ObservableShort observableShort) {
    checkNotNull(observableShort, "observableShort == null");
    return new ObservableShortObservable(observableShort);
  }
}
