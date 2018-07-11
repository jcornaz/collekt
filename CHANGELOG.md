# Change log

## 0.0.1-SNAPSHOT (Unreleased)
* Add immutable interfaces `ImmutableCollection` and `ImmutableList`
* Add persistent interfaces: `PersistentCollection` and `PersistentList`
* Add `+` and `-` operators
* Add `with` to replace elements
* Add `emptyPersistentList()` and `emptyPersistentSet()`
* Add `persistentListOf()`, `Iterable.toPersistentList()`, `Sequence.toPersistentList()`
* Provide default implementations
    * Backed by [Paguro](https://github.com/GlenKPeterson/Paguro)) for the JVM
    * Backed by the kotlin standard library for Javascript
