package io.oldering.rxdatabinding.databinding;

import android.databinding.ObservableArrayList;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import io.oldering.rxdatabinding.internal.RecordingObserver;
import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class) //
public class RxObservableArrayListTest {
  @Rule public final UiThreadTestRule uiThread = new UiThreadTestRule();

  private final ObservableArrayList<String> observableArrayList = new ObservableArrayList<>();

  @Test @UiThreadTest public void itemRangeChangeEvents() {
    String value = "value";
    observableArrayList.addAll(Arrays.asList(value, value, value));
    ObservableArrayListItemRangeChangeEvent<String> next;

    RecordingObserver<ObservableArrayListItemRangeChangeEvent<String>> o =
        new RecordingObserver<>();
    RxObservableArrayList.itemRangeChangeEvents(observableArrayList).subscribe(o);
    o.assertNoMoreEvents();

    observableArrayList.set(2, value);
    next = o.takeNext();
    assertEquals(observableArrayList, next.observableArrayList());
    assertEquals(2, next.positionStart());
    assertEquals(1, next.itemCount());

    observableArrayList.set(1, value);
    next = o.takeNext();
    assertEquals(observableArrayList, next.observableArrayList());
    assertEquals(1, next.positionStart());
    assertEquals(1, next.itemCount());

    o.dispose();

    observableArrayList.set(0, value);
    o.assertNoMoreEvents();
  }

  @Test @UiThreadTest public void itemRangeInsertionEvents() {
    String value = "value";
    ObservableArrayListItemRangeInsertionEvent<String> next;

    RecordingObserver<ObservableArrayListItemRangeInsertionEvent<String>> o =
        new RecordingObserver<>();
    RxObservableArrayList.itemRangeInsertionEvents(observableArrayList).subscribe(o);
    o.assertNoMoreEvents();

    observableArrayList.add(value);
    next = o.takeNext();
    assertEquals(observableArrayList, next.observableArrayList());
    assertEquals(0, next.positionStart());
    assertEquals(1, next.itemCount());

    observableArrayList.addAll(Arrays.asList(value, value, value));
    next = o.takeNext();
    assertEquals(observableArrayList, next.observableArrayList());
    assertEquals(1, next.positionStart());
    assertEquals(3, next.itemCount());

    o.dispose();

    observableArrayList.add(value);
    o.assertNoMoreEvents();
  }

  @Test @UiThreadTest public void itemRangeRemovalEvents() {
    String value = "value";
    observableArrayList.addAll(Arrays.asList(value, value, value, value + "other"));
    ObservableArrayListItemRangeRemovalEvent<String> next;

    RecordingObserver<ObservableArrayListItemRangeRemovalEvent<String>> o =
        new RecordingObserver<>();
    RxObservableArrayList.itemRangeRemovalEvents(observableArrayList).subscribe(o);
    o.assertNoMoreEvents();

    observableArrayList.remove(1);
    next = o.takeNext();
    assertEquals(observableArrayList, next.observableArrayList());
    assertEquals(1, next.positionStart());
    assertEquals(1, next.itemCount());

    observableArrayList.remove(value);
    next = o.takeNext();
    assertEquals(observableArrayList, next.observableArrayList());
    assertEquals(0, next.positionStart());
    assertEquals(1, next.itemCount());

    o.dispose();

    observableArrayList.remove(0);
    o.assertNoMoreEvents();
  }
}
