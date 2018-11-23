# Collekt
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Project status](https://img.shields.io/badge/status-incubating-orange.svg)](https://gist.githubusercontent.com/jcornaz/46736c3d1f21b4c929bd97549b7406b2/raw/ProjectStatusFlow)
[![JitPack](https://jitpack.io/v/jcornaz/collekt.svg)](https://jitpack.io/#jcornaz/collekt)
[![Build Status](https://travis-ci.com/jcornaz/collekt.svg?branch=master)](https://travis-ci.com/jcornaz/collekt)
[![Quality gate](https://sonarcloud.io/api/project_badges/measure?project=jcornaz_collekt&metric=alert_status)](https://sonarcloud.io/dashboard?id=jcornaz_collekt)

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
| Java (8+)  | [Paguro](https://github.com/GlenKPeterson/Paguro)                                                     | [Glen K. Peterson](https://github.com/GlenKPeterson) |
| JavaScript | [Kotlin standard library](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/index.html) | [JetBrains](https://jetbrains.com/)                  |

### Usage
The Persistent interfaces provide `+` and `-` operator aside other useful methods.

Usage is pretty straight forward if you're used to persistent data structure.
Mutation are provided in the form of operator/functions which return a new instance with the operation applied.
```kotlin
val list1 = persistentListOf("Hello", "world")
val list2 = list1 - "world"
val list3 = list2 + "everybody"
val list4 = list3.with(1, "you")

println(list1) // ["Hello", "world"]
println(list2) // ["Hello"]
println(list3) // ["Hello", "everybody"]
println(list4) // ["Hello", "you"]
```

In most cases it will look exactly the same as if you'd use the standard library.
But unlike the `+` and `-` operator of the standard library, the collections are not copied, and most of the data is shared making them much faster and less memory consuming.  

### Current state of the project
This library is still incubating. The JVM module already provide efficient persistent data structure for all collection types,
but the javascript module do not (yet).

Here is the check list of persistent implementation to provide (sorted by implementation priority):
* [X] Java
    * [X] List
    * [X] Set
    * [X] Map
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
    
    // For Java (JDK 8+)
    compile 'com.github.jcornaz.collekt:collekt-core-jvm:0.0.3'
            
    // For javascript (implementations are not efficient yet)
    compile 'com.github.jcornaz.collekt:collekt-core-js:0.0.3'
        
    // For common module
    compile 'com.github.jcornaz.collekt:collekt-core-common:0.0.3'
}
```

## Implementation libraries
The current known challengers for a JVM implementation are:
* [Paguro](https://github.com/GlenKPeterson/Paguro) (currently used for all collection types)
* [vavr.io](http://www.vavr.io/)
* [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable)

The following libraries are not eligible because they are discontinued and/or don't support null values: 
* [Dexx](https://github.com/andrewoma/dexx)
* [pcollections](https://pcollections.org/)
* [Collider](https://github.com/rschmitt/collider)

For Javascript the only known challenger is:
* [Immutable.js](https://facebook.github.io/immutable-js)

If you know another fast self-contained jvm or javascript implementation, please submit an issue.
