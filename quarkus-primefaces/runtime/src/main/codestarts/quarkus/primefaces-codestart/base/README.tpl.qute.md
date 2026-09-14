{#include readme-header /}

The page is rendered by `src/main/resources/META-INF/resources/index.xhtml` and backed by the
`GreetingBean` CDI bean. Faces is configured through two descriptors:

- `src/main/resources/META-INF/web.xml` — servlet mapping, error pages and the PrimeFaces/MyFaces
  context parameters, including `primefaces.THEME`.
- `src/main/resources/META-INF/faces-config.xml` — the PrimeFaces Dialog Framework, exception
  handler and client window factory.

Both descriptors live under `META-INF` rather than `WEB-INF`, because a Quarkus application is a
jar and not a war.
