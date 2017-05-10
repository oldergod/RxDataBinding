package io.oldering.rxdatabinding;

import android.databinding.ObservableChar;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import io.reactivex.Observable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkNotNull;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link
 * ObservableChar}.
 */
public final class RxObservableChar {
  /**
   * Create an observable of property changed events on {@code observableChar}.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code observableChar}.
   * Unsubscribe to free this reference.
   */
  @CheckResult @NonNull //
  public static Observable<Character> propertyChangedEvents(
      @NonNull ObservableChar observableChar) {
    checkNotNull(observableChar, "observableChar == null");
    return new ObservableCharObservable(observableChar);
  }

  private RxObservableChar() {
    throw new AssertionError("No instances.");
  }
}
