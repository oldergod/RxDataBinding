package io.oldering.rxdatabinding;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableArrayListObservable<T>
    extends Observable<ObservableArrayListOnListChangedEvent<T>> {
  private final ObservableArrayList<T> observableArrayList;

  ObservableArrayListObservable(ObservableArrayList<T> observableArrayList) {
    this.observableArrayList = observableArrayList;
  }

  @Override protected void subscribeActual(
      Observer<? super ObservableArrayListOnListChangedEvent<T>> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableArrayList, observer);
    observer.onSubscribe(listener);
    observableArrayList.addOnListChangedCallback(listener.onListChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    private final ObservableArrayList<T> observableArrayList;
    final ObservableList.OnListChangedCallback<ObservableArrayList<T>> onListChangedCallback;

    Listener(final ObservableArrayList<T> observableArrayList,
        final Observer<? super ObservableArrayListOnListChangedEvent<T>> observer) {
      this.observableArrayList = observableArrayList;
      this.onListChangedCallback =
          new ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {
            @Override public void onChanged(ObservableArrayList<T> observableArrayList) {
              // do nothing as ObservableArrayList does not use it
            }

            @Override public void onItemRangeChanged(ObservableArrayList<T> observableArrayList,
                int positionStart, int itemCount) {
              observer.onNext(
                  ObservableArrayListOnListChangedEvent.itemRangeChanged(observableArrayList,
                      positionStart, itemCount));
            }

            @Override public void onItemRangeInserted(ObservableArrayList<T> observableArrayList,
                int positionStart, int itemCount) {
              observer.onNext(
                  ObservableArrayListOnListChangedEvent.itemRangeInserted(observableArrayList,
                      positionStart, itemCount));
            }

            @Override public void onItemRangeMoved(ObservableArrayList<T> observableArrayList,
                int positionStart, int positionEnd, int itemCount) {
              // do nothing as ObservableArrayList does not use it
            }

            @Override public void onItemRangeRemoved(ObservableArrayList<T> observableArrayList,
                int positionStart, int itemCount) {
              observer.onNext(
                  ObservableArrayListOnListChangedEvent.itemRangeRemoved(observableArrayList,
                      positionStart, itemCount));
            }
          };
    }

    @Override protected void onDispose() {
      observableArrayList.removeOnListChangedCallback(onListChangedCallback);
    }
  }
}
