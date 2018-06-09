# Change log

## 0.0.1-SNAPSHOT (Unreleased)
* Add immutable interfaces `Traversable`, `SizedTraversable`, `ImmutableCollection` and `ImmutableList`
* Add persistent interfaces: `PersistentCollection` and `PersistentList`
* Add `+` and `-` operators
* add `emptyPersistentList()` and `emptyPersistentSet()`
* Add `persistentListOf()`, `Iterable.toPersistentList()`, `Sequence.toPersistentList()`
* Add adapter from persistent to standard collections: `Traversable.asIterable()` and `ImmutableList.asList()`
* Add extensions functions on `Traversable`
    * `forEach` and `forEachIndexed`
    * `any`, `none` and `all`
    * `fold`
    * `first` and `firstOrNull`
    * `joinToString` 

