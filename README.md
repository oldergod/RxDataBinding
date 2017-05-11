RxBinding
=========

RxJava2 binding APIs for Android'S Data Binding Library. Basically, all the
`android.databinding.Observable*` that allow some kind of callback. 

Usage
-----

```java
ObservableBoolean bool = new ObservableBoolean();
RxObservableBoolean.propertyChange(bool)
  .subscribe(someBoolean -> log.d(TAG, "Do something with someBoolean"));
```

Download
--------

TBD

Development
-----------

The code is heavily inspired on [RxBinding](https://github.com/JakeWharton/RxBinding/), hence the 
 many similarities.

Weak references should not be used. RxJava's subscription graph allows for proper garbage
 collections of reference-holding objects provided the caller unsubscribes.

Naming conventions of classes and their packages should provide unambiguous information on where
 functionality can be found. Helpers for platform classes can be found in packages of the same name
 but prefixed with `com.benoitquenaudon.rxdatabinding.` instead of `android.` and classes of the 
 same name but prefixed with `Rx`. For example, `android.databinding.ObservableBoolean` bindings are
 in `com.benoitquenaudon.rxdatabinding.databinding.RxObservableBoolean`.

Observable factory method names is the plural of the verb (e.g., click --> clicks). The verb
 should be in the present tense, regardless of the platform's use (e.g., changed -> change`).
 
If the listener callback provides more than one parameter of useful data, a factory method overload
 named in the singular and suffixed with "Events" is included. This overload emits wrapper objects
 containing all the additional information about the event. The name of the wrapper object is the 
 concatenation of the view simple name, the verb (with optional adverb prefix), and "Event". These 
 classes are in the public API.

Events for listeners with multiple methods should share an abstract base class. The naming follows 
the same rules as a normal event class but without the qualifying prefix. The constructor should be 
package-private to prevent subclasses other than those defined for the listener methods. This class 
should be in the public API.

License
-------

    Copyright (C) 2017 Benoît Quenaudon

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
