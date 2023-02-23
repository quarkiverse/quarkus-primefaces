# Quarkus PrimeFaces
[![Version](https://img.shields.io/maven-central/v/io.quarkiverse.primefaces/quarkus-primefaces?logo=apache-maven&style=flat-square)](https://search.maven.org/artifact/io.quarkiverse.primefaces/quarkus-primefaces)
[![All Contributors](https://img.shields.io/badge/all_contributors-2-orange.svg?style=flat-square)](#contributors-)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square)](https://opensource.org/licenses/Apache-2.0)
[![Build](https://github.com/quarkiverse/quarkus-primefaces/actions/workflows/build.yml/badge.svg)](https://github.com/quarkiverse/quarkus-primefaces/actions/workflows/build.yml)
[![Stackoverflow](https://img.shields.io/badge/StackOverflow-primefaces-chocolate.svg)](https://stackoverflow.com/questions/tagged/primefaces)

[![Quarkus Primefaces Logo](https://www.primefaces.org/wp-content/uploads/2016/10/prime_logo_new.png)](https://primefaces.org/)


## Overview

A Quarkus extension that lets you utilize [primefaces](https://www.primefaces.org/showcase/index.xhtml) and [primefaces-extensions](https://www.primefaces.org/showcase-ext/views/home.jsf) make JSF development so much easier!


## Getting started

* Create or use an existing Quarkus application
* Add the primefaces extension with the [Quarkus CLI](https://quarkus.io/guides/cli-tooling):
```bash
quarkus ext add io.quarkiverse.primefaces:quarkus-primefaces
quarkus ext add io.quarkiverse.primefaces:quarkus-primefaces-extensions
```

Or manually add to pom.xml:

```xml
<dependency>
    <groupId>io.quarkiverse.primefaces</groupId>
    <artifactId>quarkus-primefaces</artifactId>
    <version>${primefaces-quarkus.version}</version>
</dependency>
<dependency>
    <groupId>io.quarkiverse.primefaces</groupId>
    <artifactId>quarkus-primefaces-extensions</artifactId>
    <version>${primefaces-quarkus.version}</version>
</dependency>
```

## Versioning

The versioning of this extension will follow the pattern `[quarkus.version].[primefaces.version].[patch]`. For example:

| Version | Explanation |
| --- | --- |
| 2.12.1 | Quarkus 2 (EE8), PrimeFaces 12, Revision 1 |
| 3.12.0 | Quarkus 3 (EEE10), PrimeFaces 12, Revision 0 |

> **⚠️**
NOTE: DataTable/TreeTable PDF exporting and FeedReader component are not supported in GraalVM Native Image mode due to complexities with their third party libraries iText and Rome.

## Showcase

A showcase example using Quarkus Primefaces and Extensions and showing Faces running in the Quarkus environment can be found on
the [QuarkusFaces](https://github.com/melloware/quarkus-faces) GitHub repository "where Quarkus meets JSF!".

[![Quarkus Faces Logo](https://github.com/melloware/quarkus-faces/blob/main/src/site/QuarkusFaces.svg)](https://github.com/melloware/quarkus-faces)

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):
<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="http://melloware.com"><img src="https://avatars.githubusercontent.com/u/4399574?v=4?s=100" width="100px;" alt="Melloware"/><br /><sub><b>Melloware</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/commits?author=melloware" title="Code">💻</a> <a href="#maintenance-melloware" title="Maintenance">🚧</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://tandraschko.blogspot.de/"><img src="https://avatars.githubusercontent.com/u/2485545?v=4?s=100" width="100px;" alt="Thomas Andraschko"/><br /><sub><b>Thomas Andraschko</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/commits?author=tandraschko" title="Code">💻</a> <a href="#maintenance-tandraschko" title="Maintenance">🚧</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
