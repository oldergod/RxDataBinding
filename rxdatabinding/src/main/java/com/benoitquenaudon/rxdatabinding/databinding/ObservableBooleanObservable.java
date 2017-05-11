package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableBoolean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.benoitquenaudon.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableBooleanObservable extends Observable<Boolean> {
  private final ObservableBoolean observableBoolean;

  ObservableBooleanObservable(ObservableBoolean observableBoolean) {
    this.observableBoolean = observableBoolean;
  }

  @Override protected void subscribeActual(Observer<? super Boolean> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableBoolean, observer);
    observer.onSubscribe(listener);
    observableBoolean.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;
    private final ObservableBoolean observableBoolean;

    Listener(final ObservableBoolean observableBoolean, final Observer<? super Boolean> observer) {
      this.observableBoolean = observableBoolean;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable observable,
                int ignoredPropertyId) {
              if (!isDisposed()) {
                ObservableBoolean observableBoolean = (ObservableBoolean) observable;
                observer.onNext(observableBoolean.get());
              }
            }
          };
    }

    @Override protected void onDispose() {
      observableBoolean.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }
  }
}
