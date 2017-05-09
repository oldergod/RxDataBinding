package io.oldering.rxdatabinding;

import android.databinding.ObservableField;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkMainThread;

public class ObservableFieldObservable<T> extends Observable<T> {
  private final ObservableField<T> field;

  ObservableFieldObservable(ObservableField<T> field) {
    this.field = field;
  }

  @Override protected void subscribeActual(Observer<? super T> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    ObservableFieldObservable.Listener listener = new Listener(field, observer);
    observer.onSubscribe(listener);
    field.addOnPropertyChangedCallback(listener.onPropertyChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    private final ObservableField<T> observableField;
    final android.databinding.Observable.OnPropertyChangedCallback onPropertyChangedCallback;

    Listener(final ObservableField<T> observableField, final Observer<? super T> observer) {
      this.observableField = observableField;
      this.onPropertyChangedCallback =
          new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override public void onPropertyChanged(android.databinding.Observable observable,
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