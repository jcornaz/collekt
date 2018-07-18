# Collekt
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Project status](https://img.shields.io/badge/status-incubating-orange.svg)](https://gist.githubusercontent.com/jcornaz/46736c3d1f21b4c929bd97549b7406b2/raw/ProjectStatusFlow)
[![JitPack](https://jitpack.io/v/jcornaz/collekt.svg)](https://jitpack.io/#jcornaz/collekt)
[![Build Status](https://travis-ci.org/jcornaz/collekt.svg?branch=master)](https://travis-ci.org/jcornaz/collekt)
[![Code quality](https://codebeat.co/badges/0f15406e-7cc2-4dfa-9b21-204d1653e558)](https://codebeat.co/projects/github-com-jcornaz-collekt-master)

Persistent collections for Kotlin

The goal of this library is to provide a kotlin API for using persistent (immutable) collections, backed by the fastest known 3rd party implementation.

## Features
### Use it in multiplatofrm project
Unlike [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable) Collekt is usable from common, javascript and jvm kotlin modules.

### Always get the fastest implementation available, without the need to refactor your code
Collekt doesn't implement the persistent data-structure itself. It is always delegated to an open-source 3rd party.

In order to choose the actual implementation Collekt do performance tests an choose the fastest implementation.

If performance tests show that an new implementation is faster, then the actual implementation will be delegated to the new faster one. That way, as a user of Collekt, you only have to update the version of Collekt to get the fastest state-of-the-art persistent collection. And as the api stay the same, swapping to a faster implementation do not incur any refactoring overhead.

The current implementations are delegated to:

| Platform   | Library                                                                                               | Author                                               |
|------------|-------------------------------------------------------------------------------------------------------|------------------------------------------------------|
| JVM 6      | [Dexx](https://github.com/andrewoma/dexx)                                                             | [Andrew O'Malley](https://github.com/andrewoma)      |
| JVM 8      | [Paguro](https://github.com/GlenKPeterson/Paguro)                                                     | [Glen K. Peterson](https://github.com/GlenKPeterson) |
| JavaScript | [Kotlin standard library](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/index.html) | [JetBrains](https://jetbrains.com/)                  |

### Usage
The Persistent interfaces provide `+` and `-` operator aside other useful methods.

Usage is pretty straight forward if you're used to persistent data structure.
Mutation are provided in the form of operator/functions which return a new instance with the operation applied.
```kotlin
val list1 = persistentListOf("Hello", "world")
val list2 = list1 - "world"
val list3 = list2 + "everybody"

println(list1) // ["Hello", "world"]
println(list2) // ["Hello"]
println(list3) // ["Hello", "everybody"]
```

In most cases it will look exactly the same as if you'd use the standard library.
But unlike the `+` and `-` operator of the standard library, the collections are not copied, and most of the data is shared making them much faster and less memory consuming.  

### Current state of the project
Currently the effort is mainly put on the API and less on performance.
The JVM-8 module provide efficient persistent data structure for all collection types.
But other modules may provide less efficient implementation.

Here is the check list of persistent implementation to provide (sorted by implementation priority):
* [X] JVM 8
    * [X] List
    * [X] Set
    * [X] Map
* [ ] JVM 6
    * [X] List
    * [ ] Set
    * [ ] Map
* [ ] Javascript
    * [ ] List
    * [ ] Set
    * [ ] Map

## How to get it

If you want to test the project in its current state, you can get the artifacts for maven or gradle from [Jitpack](jitpack.io):

```groovy
repositories {
    jcenter()
    maven { url 'https://jitpack.io' }
}

dependencies {
    
    // For Java 8+
    compile 'com.github.jcornaz.collekt:collekt-core-jvm8:0.0.2'
    
    // For Java 6/7 (some implementations are not efficient yet)
    compile 'com.github.jcornaz.collekt:collekt-core-jvm6:0.0.2'
        
    // For javascript (implementations are not efficient yet)
    compile 'com.github.jcornaz.collekt:collekt-core-js:0.0.2'
        
    // For common module
    compile 'com.github.jcornaz.collekt:collekt-core-common:0.0.2'
}
```

## Implementation libraries
The current known challengers for a JVM implementation are:
* [Paguro](https://github.com/GlenKPeterson/Paguro) (currently used for all collection types in JVM 8)
* [vavr.io](http://www.vavr.io/) (currently used for list in JVM 6)
* [pcollections](https://pcollections.org/)
* [Dexx](https://github.com/andrewoma/dexx)
* [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable)

For Javascript the only known challenger is:
* [Immutable.js](https://facebook.github.io/immutable-js)

If you know another fast self-contained jvm or javascript implementation, please submit an issue.
