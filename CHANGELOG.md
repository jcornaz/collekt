# Change log

## 0.0.1-SNAPSHOT (Unreleased)
* Add immutable interfaces `ImmutableCollection` and `ImmutableList`
* Add persistent interfaces: `PersistentCollection` and `PersistentList`
* Add `+` and `-` operators
* Add `with` operator to replace elements
* Add `emptyPersistentList()` and `emptyPersistentSet()`
* Add `persistentListOf()`, `Iterable.toPersistentList()`, `Sequence.toPersistentList()`
* Provide default implementations
    * Backed by [Paguro](https://github.com/GlenKPeterson/Paguro) for JVM 6
    * Backed by [Dexx](https://github.com/andrewoma/dexx) for JVM 8
    * Backed by the kotlin standard library for Javascript
* Add `filter`, `filterNotNull`, `filterIsInstance`, `map`, `mapNotNull` and `flatMap` operators for immutable collections
