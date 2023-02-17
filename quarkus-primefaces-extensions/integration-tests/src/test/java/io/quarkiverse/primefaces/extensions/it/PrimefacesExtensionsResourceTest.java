package io.quarkiverse.primefaces.extensions.it;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PrimefacesExtensionsResourceTest {

    @TestHTTPResource
    URL url;

    private static WebClient webClient;

    @BeforeAll
    public static void initWebClient() {
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getCookieManager().setCookiesEnabled(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    }

    @AfterAll
    public static void closeWebClient() {
        if (webClient != null) {
            webClient.close();
        }
    }

    @Test
    public void shouldOpenIndexPage() throws Exception {
        final HtmlPage page = webClient.getPage(url + "/index.xhtml");

        // escape selector
        final HtmlSpan selector = (HtmlSpan) page.getElementById("selector");
        assertThat(selector).isNotNull();
        assertThat(selector.getTextContent().trim()).isEqualTo("form\\\\:myPanel");

        // localized
        final HtmlSpan localized = (HtmlSpan) page.getElementById("localized");
        assertThat(localized).isNotNull();
        assertThat(localized.getTextContent().trim()).isEqualTo("Localized Test");

        // InputPhone
        final HtmlInput phone = (HtmlInput) page.getElementById("frmPhone:txtPhone_input");
        assertThat(phone).isNotNull();
        assertThat(phone.getValue()).isEqualTo("(610) 867-5309");
    }
}
