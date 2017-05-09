package io.oldering.rxdatabinding;

import android.databinding.ObservableByte;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableByteObservable extends Observable<Byte> {
  private final ObservableByte observableByte;

  ObservableByteObservable(ObservableByte observableByte) {
    this.observableByte = observableByte;
  }

  @Override protected void subscribeActual(Observer<? super Byte> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableByte, observer);
    observer.onSubscribe(listener);
    observableByte.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    private final ObservableByte observableByte;
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;

    Listener(final ObservableByte observableByte, final Observer<? super Byte> observer) {
      this.observableByte = observableByte;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override public void onPropertyChanged(android.databinding.Observable observable,
                int ignoredPropertyId) {
              if (!isDisposed()) {
                ObservableByte observableByte = (ObservableByte) observable;
                observer.onNext(observableByte.get());
              }
            }
          };
    }

    @Override protected void onDispose() {
      observableByte.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }
  }
}
