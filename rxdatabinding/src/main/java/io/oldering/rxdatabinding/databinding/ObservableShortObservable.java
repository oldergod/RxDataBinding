package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableShort;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableShortObservable extends Observable<Short> {
  private final ObservableShort observableShort;

  ObservableShortObservable(ObservableShort observableShort) {
    this.observableShort = observableShort;
  }

  @Override protected void subscribeActual(Observer<? super Short> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableShort, observer);
    observer.onSubscribe(listener);
    observableShort.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;
    private final ObservableShort observableShort;

    Listener(final ObservableShort observableShort, final Observer<? super Short> observer) {
      this.observableShort = observableShort;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override public void onPropertyChanged(android.databinding.Observable observable,
                int ignoredPropertyId) {
              if (!isDisposed()) {
                ObservableShort observableShort = (ObservableShort) observable;
                observer.onNext(observableShort.get());
              }
            }
          };
    }

    @Override protected void onDispose() {
      observableShort.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }
  }
}
