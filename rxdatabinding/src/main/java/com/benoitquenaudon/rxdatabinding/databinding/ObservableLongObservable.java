package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableLong;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.benoitquenaudon.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableLongObservable extends Observable<Long> {
  private final ObservableLong observableLong;

  ObservableLongObservable(ObservableLong observableLong) {
    this.observableLong = observableLong;
  }

  @Override protected void subscribeActual(Observer<? super Long> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableLong, observer);
    observer.onSubscribe(listener);
    observableLong.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;
    private final ObservableLong observableLong;

    Listener(final ObservableLong observableLong, final Observer<? super Long> observer) {
      this.observableLong = observableLong;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable observable,
                int ignoredPropertyId) {
              if (!isDisposed()) {
                ObservableLong observableLong = (ObservableLong) observable;
                observer.onNext(observableLong.get());
              }
            }
          };
    }

    @Override protected void onDispose() {
      observableLong.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }
  }
}
