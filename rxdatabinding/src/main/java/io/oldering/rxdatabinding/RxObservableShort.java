package io.oldering.rxdatabinding;

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
  /**
   * Create an observable of property changed events on {@code observableShort}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableShort}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Short> propertyChangedEvents(@NonNull ObservableShort observableShort) {
    checkNotNull(observableShort, "observableShort == null");
    return new ObservableShortObservable(observableShort);
  }

  private RxObservableShort() {
    throw new AssertionError("No instances.");
  }
}
