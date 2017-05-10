package io.oldering.rxdatabinding;

import android.databinding.ObservableArrayMap;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableArrayMap}.
 */
public final class RxObservableArrayMap {
  /**
   * Create an observable of property changed events on {@code observableArrayMap}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * observableArrayMap}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static <K, V> Observable<ObservableArrayMapMapChangedEvent<K, V>> mapChangedEvents(
      @NonNull ObservableArrayMap<K, V> observableArrayMap) {
    checkNotNull(observableArrayMap, "observableArrayMap == null");
    return new ObservableArrayMapObservable<>(observableArrayMap);
  }

  private RxObservableArrayMap() {
    throw new AssertionError("No instances.");
  }
}
