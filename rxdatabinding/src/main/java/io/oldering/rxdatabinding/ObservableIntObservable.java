package io.oldering.rxdatabinding;

import android.databinding.ObservableInt;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableIntObservable extends Observable<Integer> {
  private final ObservableInt observableInt;

  ObservableIntObservable(ObservableInt observableInt) {
    this.observableInt = observableInt;
  }

  @Override protected void subscribeActual(Observer<? super Integer> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableInt, observer);
    observer.onSubscribe(listener);
    observableInt.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    private final ObservableInt observableInt;
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;

    Listener(final ObservableInt observableInt, final Observer<? super Integer> observer) {
      this.observableInt = observableInt;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override public void onPropertyChanged(android.databinding.Observable observable,
                int ignoredPropertyId) {
              if (!isDisposed()) {
                ObservableInt observableInt = (ObservableInt) observable;
                observer.onNext(observableInt.get());
              }
            }
          };
    }

    @Override protected void onDispose() {
      observableInt.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }
  }
}
