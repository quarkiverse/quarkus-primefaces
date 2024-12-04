<div align="center">
    <a href="https://primefaces.org/" alt="PrimeFaces">
        <img src="https://github.com/quarkiverse/quarkus-quinoa/blob/main/docs/modules/ROOT/assets/images/quarkus.svg" width="67" height="70" >
        <img src="https://github.com/quarkiverse/quarkus-omnifaces/blob/main/docs/modules/ROOT/assets/images/plus-sign.svg" height="70" >
        <img src="https://www.primefaces.org/wp-content/uploads/2016/10/prime_logo_new.png" height="70" />
    </a>
 
# Quarkus PrimeFaces
</div>
<br>

[![Version](https://img.shields.io/maven-central/v/io.quarkiverse.primefaces/quarkus-primefaces?logo=apache-maven&style=flat-square)](https://search.maven.org/artifact/io.quarkiverse.primefaces/quarkus-primefaces)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat-square)](https://opensource.org/licenses/Apache-2.0)
[![Build](https://github.com/quarkiverse/quarkus-primefaces/actions/workflows/build.yml/badge.svg)](https://github.com/quarkiverse/quarkus-primefaces/actions/workflows/build.yml)
[![Stackoverflow](https://img.shields.io/badge/StackOverflow-primefaces-chocolate.svg)](https://stackoverflow.com/questions/tagged/primefaces)

## Overview

A Quarkus extension that lets you utilize [primefaces](https://www.primefaces.org/showcase/index.xhtml) and [primefaces-extensions](https://www.primefaces.org/showcase-ext/views/home.jsf) make JSF development so much easier!


## Getting started


Read the full [PrimeFaces documentation](https://docs.quarkiverse.io/quarkus-primefaces/dev/index.html). 

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

The versioning of this extension will follow the pattern `[quarkus.version].[primefaces.version].[patch]`. 
The `patch` is not the PrimeFaces version patch it is the patch version of this extension as we may make Quarkus
fixes in between PF releases.  However we strive to stay on top of PrimeFaces minor releases such as 13.0.2 and 
publish a new version here right after a PrimeFaces release.

For example:

| Version | Explanation |
| --- | --- |
| ![2.12.x](https://img.shields.io/maven-central/v/io.quarkiverse.primefaces/quarkus-primefaces?versionPrefix=2.&color=cyan)   | Quarkus 2 (EE08), PrimeFaces 12 |
| ![3.13.x](https://img.shields.io/maven-central/v/io.quarkiverse.primefaces/quarkus-primefaces?versionPrefix=3.13&color=cyan) | Quarkus 3 (EE10), PrimeFaces 13 |
| ![3.14.x](https://img.shields.io/maven-central/v/io.quarkiverse.primefaces/quarkus-primefaces?versionPrefix=3.14&color=cyan) | Quarkus 3 (EE10), PrimeFaces 14 |
| ![3.15.x](https://img.shields.io/maven-central/v/io.quarkiverse.primefaces/quarkus-primefaces?versionPrefix=3.15&color=cyan) | Quarkus 3 (EE10), PrimeFaces 15 |

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
      <td align="center" valign="top" width="14.28%"><a href="http://tandraschko.blogspot.de/"><img src="https://avatars.githubusercontent.com/u/2485545?v=4?s=100" width="100px;" alt="Thomas Andraschko"/><br /><sub><b>Thomas Andraschko</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/commits?author=tandraschko" title="Code">💻</a> <a href="#maintenance-tandraschko" title="Maintenance">🚧</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://melloware.com"><img src="https://avatars.githubusercontent.com/u/4399574?v=4?s=100" width="100px;" alt="Melloware"/><br /><sub><b>Melloware</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/commits?author=melloware" title="Code">💻</a> <a href="#maintenance-melloware" title="Maintenance">🚧</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://karms.biz"><img src="https://avatars.githubusercontent.com/u/691097?v=4?s=100" width="100px;" alt="Michal Karm Babacek"/><br /><sub><b>Michal Karm Babacek</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/issues?q=author%3AKarm" title="Bug reports">🐛</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://dennis.gesker.com"><img src="https://avatars.githubusercontent.com/u/6843294?v=4?s=100" width="100px;" alt="Dennis Gesker"/><br /><sub><b>Dennis Gesker</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/issues?q=author%3Agesker" title="Bug reports">🐛</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://gastaldi.wordpress.com"><img src="https://avatars.githubusercontent.com/u/54133?v=4?s=100" width="100px;" alt="George Gastaldi"/><br /><sub><b>George Gastaldi</b></sub></a><br /><a href="#infra-gastaldi" title="Infrastructure (Hosting, Build-Tools, etc)">🚇</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/omasseau"><img src="https://avatars.githubusercontent.com/u/11772429?v=4?s=100" width="100px;" alt="Olivier Masseau"/><br /><sub><b>Olivier Masseau</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/issues?q=author%3Aomasseau" title="Bug reports">🐛</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/nyko29"><img src="https://avatars.githubusercontent.com/u/12033038?v=4?s=100" width="100px;" alt="nyko29"/><br /><sub><b>nyko29</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/commits?author=nyko29" title="Tests">⚠️</a></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="https://swailem.org"><img src="https://avatars.githubusercontent.com/u/26059392?v=4?s=100" width="100px;" alt="Rami Swailem"/><br /><sub><b>Rami Swailem</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/issues?q=author%3Aramiswailem" title="Bug reports">🐛</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/leandro-costa"><img src="https://avatars.githubusercontent.com/u/6432053?v=4?s=100" width="100px;" alt="leandro-costa"/><br /><sub><b>leandro-costa</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/issues?q=author%3Aleandro-costa" title="Bug reports">🐛</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/nimo23"><img src="https://avatars.githubusercontent.com/u/3045549?v=4?s=100" width="100px;" alt="nimo23"/><br /><sub><b>nimo23</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/issues?q=author%3Animo23" title="Bug reports">🐛</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://martinelli.ch"><img src="https://avatars.githubusercontent.com/u/593352?v=4?s=100" width="100px;" alt="Simon Martinelli"/><br /><sub><b>Simon Martinelli</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/issues?q=author%3Asimasch" title="Bug reports">🐛</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/redddcyclone"><img src="https://avatars.githubusercontent.com/u/58712628?v=4?s=100" width="100px;" alt="Leonardo Bernardes"/><br /><sub><b>Leonardo Bernardes</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/issues?q=author%3Aredddcyclone" title="Bug reports">🐛</a> <a href="https://github.com/quarkiverse/quarkus-primefaces/commits?author=redddcyclone" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://github.com/tmulle"><img src="https://avatars.githubusercontent.com/u/5183186?v=4?s=100" width="100px;" alt="tmulle"/><br /><sub><b>tmulle</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-primefaces/commits?author=tmulle" title="Tests">⚠️</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
