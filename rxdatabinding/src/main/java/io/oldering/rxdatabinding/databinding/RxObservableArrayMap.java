package io.oldering.rxdatabinding.databinding;

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
  private RxObservableArrayMap() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of property change events on {@code observableArrayMap}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code
   * observableArrayMap}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull
  public static <K, V> Observable<ObservableArrayMapOnMapChangeEvent<K, V>> mapChange(
      @NonNull ObservableArrayMap<K, V> observableArrayMap) {
    checkNotNull(observableArrayMap, "observableArrayMap == null");
    return new ObservableArrayMapObservable<>(observableArrayMap);
  }
}
