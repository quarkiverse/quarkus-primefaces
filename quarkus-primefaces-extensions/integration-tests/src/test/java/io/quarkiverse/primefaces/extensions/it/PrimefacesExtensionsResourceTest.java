package io.quarkiverse.primefaces.extensions.it;

import static org.assertj.core.api.Assertions.assertThat;

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
public class PrimefacesExtensionsResourceTest {
    @InjectPlaywright
    BrowserContext context;

    @TestHTTPResource("/index.xhtml")
    URL index;

    @Test
    public void shouldOpenIndexPage() {
        final Page page = context.newPage();
        Response response = page.navigate(index.toString());
        Assertions.assertEquals("OK", response.statusText());

        page.waitForLoadState();

        // page title
        String title = page.title();
        Assertions.assertEquals("Quarkiverse PFE", title);

        // escape selector
        final Locator message = page.locator("#selector");
        assertThat(message).isNotNull();
        assertThat(message.innerText()).isEqualTo("form\\\\:myPanel");

        // localized
        final Locator localized = page.locator("#localized");
        assertThat(localized).isNotNull();
        assertThat(localized.innerText().trim()).isEqualTo("Localized Test");

        // InputPhone
        final Locator phone = page.locator("#frmPhone\\:txtPhone_input");
        assertThat(phone).isNotNull();
        assertThat(phone.getAttribute("value")).isEqualTo("610-867-5309");
    }
}
