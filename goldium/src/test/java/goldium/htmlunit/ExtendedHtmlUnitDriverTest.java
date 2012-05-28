package goldium.htmlunit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

import goldium.ExtendedWebDriver;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ExtendedHtmlUnitDriverTest {

	private ExtendedWebDriver driver;

	@Before
	public void init() {
		driver = new ExtendedHtmlUnitDriver();
	}

	@Test
	public void shouldGetResponseHeaders() {
		final Map<String, String> responseHeaders = driver.getResponseHeaders("http://www.google.com");
		assertThat(responseHeaders, hasEntry("Content-Type", "text/html; charset=UTF-8"));
	}

	@Test
	public void shouldGetResponseHeader() {
		final String headerValue = driver.getResponseHeader("http://www.google.com", "Content-Type");
		assertThat(headerValue, is(equalTo("text/html; charset=UTF-8")));
	}
}
