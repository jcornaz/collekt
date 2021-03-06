# Change log

## 0.1-SNAPSHOT (Unreleased)


## 0.0.3 (2018-11-22)
`empty` member in persistent interface, factory functions for immutable types and end of support for Java 6 platform.

### API
* **[BREAKING]** Add `empty()` function to persistent interfaces.
    It allows to get an empty version of a collection without the need to have the related factory.
* Add factory functions for immutable (non-persistent) collection types

### Core
* Add `filterNot` operator

### Drop support of Java6 platform
(Java 8+ remains supported)

* **[BREAKING]** Delete module `collekt-dexx`
* **[BREAKING]** Delete module `collekt-core-jvm6`
* **[BREAKING]** Rename module `collekt-core-jvm8` to `collekt-core-jvm`

### Dependencies (potentially breaking)
* Kotlin `1.2.51` -> `1.3.10`

#### Implementations
* Paguro `3.0.19` -> `3.1.0`


## 0.0.2 (2018-07-18)
Map and Set with default implementations

### Added
* `ImmutableSet` and `PersistentSet` with default implementations
* `ImmutableMap` and `PersistentMap` with default implementations
* Naive set and map implementations for Javascript and Java 6 using Kotlin standard library
* Persistent set implementation for Java 8 backed by [Paguro](https://github.com/GlenKPeterson/Paguro)
* Persistent map implementation for Java 8 backed by [Paguro](https://github.com/GlenKPeterson/Paguro)


## 0.0.1 (2018-07-12)
Persistent lists with implementation backed by [Paguro](https://github.com/GlenKPeterson/Paguro) for Java 8+ and [Dexx](https://github.com/andrewoma/dexx) for Java 6/7.

### API
* Add immutable interfaces `ImmutableCollection` and `ImmutableList`
* Add persistent interfaces: `PersistentCollection` and `PersistentList`

### Core
* Add `emptyPersistentList()`, `persistentListOf()`, `Iterable.toPersistentList()` and `Sequence.toPersistentList()`
* Provide default implementations
    * Backed by [Paguro](https://github.com/GlenKPeterson/Paguro) for JVM 8
    * Backed by [Dexx](https://github.com/andrewoma/dexx) for JVM 6
    * Backed by the kotlin standard library for Javascript
* Add `filter`, `filterNotNull`, `filterIsInstance`, `map`, `mapNotNull` and `flatMap` operators for immutable collections
