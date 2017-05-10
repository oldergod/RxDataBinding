package io.oldering.rxdatabinding;

import android.databinding.ObservableArrayMap;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class ObservableArrayMapOnMapChangedEvent<K, V> {
  public static <K, V> ObservableArrayMapOnMapChangedEvent<K, V> create(
      ObservableArrayMap<K, V> observableArrayMap, @Nullable K key) {
    return new AutoValue_ObservableArrayMapOnMapChangedEvent<>(observableArrayMap, key);
  }

  public abstract ObservableArrayMap<K, V> observableArrayMap();

  @Nullable public abstract K key();
}
