# Collekt
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Project status](https://img.shields.io/badge/status-incubating-orange.svg)](https://gist.githubusercontent.com/jcornaz/46736c3d1f21b4c929bd97549b7406b2/raw/ProjectStatusFlow)
[![Build Status](https://travis-ci.org/jcornaz/collekt.svg?branch=master)](https://travis-ci.org/jcornaz/collekt)
[![Code quality](https://codebeat.co/badges/0f15406e-7cc2-4dfa-9b21-204d1653e558)](https://codebeat.co/projects/github-com-jcornaz-collekt-master)

Persistent collections for Kotlin

The goal of this library is to provide a kotlin API for using persistent (immutable) collections, backed by the fastest known 3rd party implementation.

### Use it in m multiplatofrm project
Unlike [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable) collekt is usable from common, javascript and jvm kotlin modules.

**IMPORTANT:** Currently the default javascript implementation is backed by the standard kotlin library which provide very poor mutation performance, as everything has to be copied each time. It is planned to use [Immutable.js](https://facebook.github.io/immutable-js) in the future.

### Always get the fastest implementation available, without the need to refactor your code
collekt don't implement the persistent data-structure itself. It is always delegated to an open-source 3rd party.

In order to choose the actual implementation collekt do performance tests an choose the fastest implementation.

If performance tests show that an new implementation is faster, then the actual implementation will be delegated to the new faster one. That way, as a user of collekt, you only have to update the version of collekt to get the fastest state-of-the-art persistent collection. And as the api stay the same, swapping to a faster implementation do not incur any refactoring overhead.

The current JVM implementations are backed by [Paguro](https://github.com/GlenKPeterson/Paguro) (author: [Glen K. Peterson](https://github.com/GlenKPeterson))

### State of development
Currently the main effort is put on the API, not on the performance. The current implementations will stay the same (paguro for JVM and kotlin stdlib for Javascript) until the API become stable. More performance tests will be performed and the backed implementation may be swapped then if it appears that others implementations are faster.

The current known challengers for a JVM implementation are:
* [Paguro](https://github.com/GlenKPeterson/Paguro)
* [vavr.io](http://www.vavr.io/)
* [pcollections](https://pcollections.org/)
* [dexx](https://github.com/andrewoma/dexx)
* [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable)

For Javascript the only known challenger is:
* [Immutable.js](https://facebook.github.io/immutable-js)

If you know another fast self-contained jvm or javascript implementation, please submit an issue.
