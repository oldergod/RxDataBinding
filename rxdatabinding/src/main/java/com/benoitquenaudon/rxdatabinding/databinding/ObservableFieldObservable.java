package com.benoitquenaudon.rxdatabinding.databinding;

import android.databinding.ObservableField;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static com.benoitquenaudon.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableFieldObservable<T> extends Observable<T> {
  private final ObservableField<T> observableField;

  ObservableFieldObservable(ObservableField<T> observableField) {
    this.observableField = observableField;
  }

  @Override protected void subscribeActual(Observer<? super T> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableField, observer);
    observer.onSubscribe(listener);
    observableField.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;
    private final ObservableField<T> observableField;

    Listener(final ObservableField<T> observableField, final Observer<? super T> observer) {
      this.observableField = observableField;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable observable,
                int ignoredPropertyId) {
              if (!isDisposed()) {
                ObservableField<T> observableField = convert(observable);
                observer.onNext(observableField.get());
              }
            }
          };
    }

    ObservableField<T> convert(android.databinding.Observable observable) {
      return observableField.getClass().cast(observable);
    }

    @Override protected void onDispose() {
      observableField.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }
  }
}