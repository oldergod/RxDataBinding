package io.oldering.rxdatabinding;

import android.databinding.ObservableDouble;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableDoubleObservable extends Observable<Double> {
  private final ObservableDouble observableDouble;

  ObservableDoubleObservable(ObservableDouble observableDouble) {
    this.observableDouble = observableDouble;
  }

  @Override protected void subscribeActual(Observer<? super Double> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableDouble, observer);
    observer.onSubscribe(listener);
    observableDouble.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    private final ObservableDouble observableDouble;
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;

    Listener(final ObservableDouble observableDouble, final Observer<? super Double> observer) {
      this.observableDouble = observableDouble;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override public void onPropertyChanged(android.databinding.Observable observable,
                int ignoredPropertyId) {
              if (!isDisposed()) {
                ObservableDouble observableDouble = (ObservableDouble) observable;
                observer.onNext(observableDouble.get());
              }
            }
          };
    }

    @Override protected void onDispose() {
      observableDouble.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }
  }
}
