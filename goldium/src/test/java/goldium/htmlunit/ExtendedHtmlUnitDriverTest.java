package goldium.htmlunit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import goldium.ExtendedWebDriver;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExtendedHtmlUnitDriverTest {

	private transient ExtendedWebDriver driver;

	@Before
	public void setUp() {
		driver = new ExtendedHtmlUnitDriver();
		driver.get("http://code.google.com/p/goldium/");
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void responseHeaderValueShouldBeNullWhenHeaderIsNotFound() {
		final String headerValue = driver.getResponseHeaderValue("FAKE-HEADER-NAME");
		assertThat(headerValue, is(nullValue()));
	}

	@Test
	public void shouldGetCurrentPageResponseHeaders() {
		final Map<String, String> responseHeaders = driver.getResponseHeaders();
		assertThat(responseHeaders, hasEntry("Server", "codesite"));
	}

	@Test
	public void shouldGetCurrentPageResponseHeader() {
		final String headerValue = driver.getResponseHeaderValue("Server");
		assertThat(headerValue, is(equalTo("codesite")));
	}

	@Test
	public void shouldGet404StatusCode() {
		driver.get("http://www.google.com/404");
		assertThat(driver.getStatusCode(), is(equalTo(HttpStatus.SC_NOT_FOUND)));
	}

	@Test
	public void shouldGet200StatusCode() {
		assertThat(driver.getStatusCode(), is(equalTo(HttpStatus.SC_OK)));
	}

	@Test
	public void shouldSetRequestHeader() {
		driver.addRequestHeader("Authorization", "Basic dXNlcjE6dXNlcjE=");
		driver.get("http://test.webdav.org/auth-basic/");
		assertThat(driver.getStatusCode(), is(not(equalTo(HttpStatus.SC_UNAUTHORIZED))));
	}

	@Test
	public void shouldRemoveRequestHeader() {
		driver.addRequestHeader("Authorization", "Basic dXNlcjE6dXNlcjE=");
		driver.removeRequestHeader("Authorization");
		driver.get("http://test.webdav.org/auth-basic/");
		assertThat(driver.getStatusCode(), is(equalTo(HttpStatus.SC_UNAUTHORIZED)));
	}

	@Test
	public void shouldSetJavascriptEnabledThroughConstructor() {
		final ExtendedHtmlUnitDriver javaScriptEnabledDriver = new ExtendedHtmlUnitDriver(true);
		assertThat(javaScriptEnabledDriver.isJavascriptEnabled(), is(true));
	}

	@Test
	public void shouldSetJavascriptEnabledThroughMethod() {
		final ExtendedHtmlUnitDriver javaScriptEnabledDriver = new ExtendedHtmlUnitDriver();
		javaScriptEnabledDriver.setJavascriptEnabled(true);
		assertThat(javaScriptEnabledDriver.isJavascriptEnabled(), is(true));
	}
}
