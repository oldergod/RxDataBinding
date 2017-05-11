package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableChar;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static com.benoitquenaudon.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableChar}.
 */
public final class RxObservableChar {
  private RxObservableChar() {
    throw new AssertionError("No instances.");
  }

  /**
   * Create an observable of property change events on {@code observableChar}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableChar}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Character> propertyChanges(@NonNull ObservableChar observableChar) {
    checkNotNull(observableChar, "observableChar == null");
    return new ObservableCharObservable(observableChar);
  }
}
