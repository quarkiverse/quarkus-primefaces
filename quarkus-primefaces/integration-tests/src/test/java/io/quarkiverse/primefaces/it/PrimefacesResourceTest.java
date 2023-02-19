package io.quarkiverse.primefaces.it;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PrimefacesResourceTest {

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
        final HtmlDivision datatable = (HtmlDivision) page.getElementById("form:carTable");
        assertThat(datatable).isNotNull();
        assertThat(datatable.getByXPath("//tr[contains(@class,'ui-datatable-selectable')]")).hasSize(10);
    }

    @Test
    public void shouldAllowTemplate() throws Exception {
        final HtmlPage page = webClient.getPage(url + "/accessibility.xhtml");
        final String title = page.getTitleText();
        assertThat(title).isEqualTo("Accessibility");
    }
}
