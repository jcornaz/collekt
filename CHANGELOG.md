# Change log

## 0.0.1-SNAPSHOT (Unreleased)
* Add persistent collection interfaces: `PersistentCollection` and `PersistentList`
* Add `+` and `-` operators
* add `emptyPersistentList()` and `emptyPersistentSet()`
* Add `persistentListOf()`, `Iterable.toPersistentList()`, `Sequence.toPersistentList()`
* Add adapter from persistent to standard collections: `PersistentCollection.asCollection()` and `PersistentList.asList()`
* Add `forEach`, `first`, `firstOrNull` and `joinToString` extensions functions on `PersistentCollection`
