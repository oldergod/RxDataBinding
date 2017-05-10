package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableChar;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableCharObservable extends Observable<Character> {
  private final ObservableChar observableChar;

  ObservableCharObservable(ObservableChar observableChar) {
    this.observableChar = observableChar;
  }

  @Override protected void subscribeActual(Observer<? super Character> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableChar, observer);
    observer.onSubscribe(listener);
    observableChar.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;
    private final ObservableChar observableChar;

    Listener(final ObservableChar observableChar, final Observer<? super Character> observer) {
      this.observableChar = observableChar;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable observable,
                int ignoredPropertyId) {
              if (!isDisposed()) {
                ObservableChar observableChar = (ObservableChar) observable;
                observer.onNext(observableChar.get());
              }
            }
          };
    }

    @Override protected void onDispose() {
      observableChar.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }
  }
}
