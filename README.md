# Collekt
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Project status](https://img.shields.io/badge/status-incubating-orange.svg)](https://gist.githubusercontent.com/jcornaz/46736c3d1f21b4c929bd97549b7406b2/raw/ProjectStatusFlow)
[![Build Status](https://travis-ci.org/jcornaz/collekt.svg?branch=master)](https://travis-ci.org/jcornaz/collekt)
[![Code quality](https://codebeat.co/badges/0f15406e-7cc2-4dfa-9b21-204d1653e558)](https://codebeat.co/projects/github-com-jcornaz-collekt-master)

Persistent collections for Kotlin

The goal of this library is to provide a kotlin API for using persistent (immutable) collections, backed by the fastest known 3rd party implementation.

### Use it in multiplatform project
Unlike [kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable) collekt is usable from common, javascript and jvm kotlin modules.

### Always get the fastest implementation available, without the need to refactor your code
collekt don't implement the persistent datastructure itself. It is always delegated to an open-source 3rd party.

In order to choose the actual implementation collekt do performance tests an choose the fastest implementation.

If performance tests show that an new implementation is faster, then the actual implementation will be delegated to the new faster one. That way, as a user of collekt, you only have to update the version of collekt to get the fastest state-of-the-art persistent collection. And as the api stay the same, swaping to a faster implementation do not incur any refactoring overhead.

Current challengers for the JVM implementation:
* [Paguro](https://github.com/GlenKPeterson/Paguro)
* [vavr.io](http://www.vavr.io/)
* [pcollections](https://pcollections.org/)
* [dexx](https://github.com/andrewoma/dexx)

Javascript implementation will be backed by [Immutable.js](https://facebook.github.io/immutable-js) for a start.

If you know another fast self-contained jvm or javascript implementation, please submit an issue.
