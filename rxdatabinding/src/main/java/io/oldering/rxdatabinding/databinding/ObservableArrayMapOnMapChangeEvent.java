package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableArrayMap;
import android.support.annotation.Nullable;
import com.google.auto.value.AutoValue;

@AutoValue public abstract class ObservableArrayMapOnMapChangeEvent<K, V> {
  public static <K, V> ObservableArrayMapOnMapChangeEvent<K, V> create(
      ObservableArrayMap<K, V> observableArrayMap, @Nullable K key) {
    return new AutoValue_ObservableArrayMapOnMapChangeEvent<>(observableArrayMap, key);
  }

  /** The observable from which this event occurred. */
  public abstract ObservableArrayMap<K, V> observableArrayMap();
  @Nullable public abstract K key();
}
