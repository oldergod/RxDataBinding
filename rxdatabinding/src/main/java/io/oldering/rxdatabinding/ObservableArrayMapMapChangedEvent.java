package io.oldering.rxdatabinding;

import android.databinding.ObservableArrayMap;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class ObservableArrayMapMapChangedEvent<K, V> {
  public static <K, V> ObservableArrayMapMapChangedEvent<K, V> create(
      ObservableArrayMap<K, V> observableArrayMap, @Nullable K key) {
    return new AutoValue_ObservableArrayMapMapChangedEvent<>(observableArrayMap, key);
  }

  public abstract ObservableArrayMap<K, V> observableArrayMap();

  @Nullable public abstract K key();
}
