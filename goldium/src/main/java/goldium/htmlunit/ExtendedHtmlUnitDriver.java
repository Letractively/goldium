package goldium.htmlunit;

import goldium.ExtendedWebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public final class ExtendedHtmlUnitDriver extends HtmlUnitDriver implements ExtendedWebDriver {

	public Map<String, String> getResponseHeaders(final String url) {
		final List<NameValuePair> nameValuePairs = getWebResponse(url).getResponseHeaders();
		final Map<String, String> responseHeaders = new HashMap<String, String>();
		for (final NameValuePair nameValuePair : nameValuePairs) {
			responseHeaders.put(nameValuePair.getName(), nameValuePair.getValue());
		}
		return responseHeaders;
	}

	public String getResponseHeader(final String url, final String name) {
		return getWebResponse(url).getResponseHeaderValue(name);
	}

	private WebResponse getWebResponse(final String url) {
		try {
			final Page page = getWebClient().getPage(url);
			return page.getWebResponse();
		} catch (final Exception exception) {
			throw new WebDriverException(exception);
		}
	}

}
