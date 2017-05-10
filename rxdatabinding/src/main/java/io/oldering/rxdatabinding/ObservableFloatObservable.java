package io.oldering.rxdatabinding;

import android.databinding.ObservableFloat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableFloatObservable extends Observable<Float> {
  private final ObservableFloat observableFloat;

  ObservableFloatObservable(ObservableFloat observableFloat) {
    this.observableFloat = observableFloat;
  }

  @Override protected void subscribeActual(Observer<? super Float> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableFloat, observer);
    observer.onSubscribe(listener);
    observableFloat.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    private final ObservableFloat observableFloat;
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;

    Listener(final ObservableFloat observableFloat, final Observer<? super Float> observer) {
      this.observableFloat = observableFloat;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override public void onPropertyChanged(android.databinding.Observable observable,
                int ignoredPropertyId) {
              if (!isDisposed()) {
                ObservableFloat observableFloat = (ObservableFloat) observable;
                observer.onNext(observableFloat.get());
              }
            }
          };
    }

    @Override protected void onDispose() {
      observableFloat.removeOnPropertyChangedCallback(onPropertyChangedCallback);
    }
  }
}
