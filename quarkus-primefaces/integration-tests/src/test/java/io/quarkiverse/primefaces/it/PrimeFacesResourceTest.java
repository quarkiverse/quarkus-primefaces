package io.quarkiverse.primefaces.it;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.net.URL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;

import io.quarkiverse.playwright.InjectPlaywright;
import io.quarkiverse.playwright.WithPlaywright;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@WithPlaywright
public class PrimeFacesResourceTest {

    @InjectPlaywright
    BrowserContext context;

    @TestHTTPResource("/index.xhtml")
    URL index;

    @TestHTTPResource("/accessibility.xhtml")
    URL accessibility;

    @Test
    public void shouldOpenIndexPage() throws Exception {
        final Page page = context.newPage();
        Response response = page.navigate(index.toString());
        Assertions.assertEquals("OK", response.statusText());

        page.waitForLoadState();

        // page title
        String title = page.title();
        Assertions.assertEquals("Quarkiverse PrimeFaces", title);

        // datatable
        final Locator datatable = page.locator("#form\\:carTable");
        assertThat(datatable).isNotNull();
        assertThat(datatable.locator("//tr[contains(@class,'ui-datatable-selectable')]").all()).hasSize(10);
    }

    @Test
    public void shouldAllowTemplate() throws Exception {
        final Page page = context.newPage();
        Response response = page.navigate(accessibility.toString());
        Assertions.assertEquals("OK", response.statusText());

        // page title
        String title = page.title();
        Assertions.assertEquals("Accessibility", title);
    }
}
