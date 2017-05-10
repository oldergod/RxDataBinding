package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableMap;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.MainThreadDisposable;

import static io.oldering.rxdatabinding.internal.Preconditions.checkMainThread;

final class ObservableArrayMapObservable<K, V>
    extends Observable<ObservableArrayMapOnMapChangeEvent<K, V>> {
  private final ObservableArrayMap<K, V> observableArrayMap;

  ObservableArrayMapObservable(ObservableArrayMap<K, V> observableArrayMap) {
    this.observableArrayMap = observableArrayMap;
  }

  @Override protected void subscribeActual(
      Observer<? super ObservableArrayMapOnMapChangeEvent<K, V>> observer) {
    if (!checkMainThread(observer)) {
      return;
    }
    Listener listener = new Listener(observableArrayMap, observer);
    observer.onSubscribe(listener);
    observableArrayMap.addOnMapChangedCallback(listener.onMapChangedCallback);
  }

  private final class Listener extends MainThreadDisposable {
    final ObservableMap.OnMapChangedCallback<ObservableArrayMap<K, V>, K, V> onMapChangedCallback;
    private final ObservableArrayMap<K, V> observableArrayMap;

    Listener(final ObservableArrayMap<K, V> observableArrayMap,
        final Observer<? super ObservableArrayMapOnMapChangeEvent<K, V>> observer) {
      this.observableArrayMap = observableArrayMap;
      this.onMapChangedCallback =
          new ObservableMap.OnMapChangedCallback<ObservableArrayMap<K, V>, K, V>() {
            @Override public void onMapChanged(ObservableArrayMap<K, V> observableArrayMap, K key) {
              observer.onNext(ObservableArrayMapOnMapChangeEvent.create(observableArrayMap, key));
            }
          };
    }

    @Override protected void onDispose() {
      observableArrayMap.removeOnMapChangedCallback(onMapChangedCallback);
    }
  }
}
