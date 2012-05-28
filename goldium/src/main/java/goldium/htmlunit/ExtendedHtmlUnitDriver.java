package goldium.htmlunit;

import goldium.ExtendedWebDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

/**
 * 
 * @see <a
 *      href="http://code.google.com/p/selenium/issues/detail?id=141#c47">WebDriver_lacks
 *      HTTP response header and status code methods</a>
 */
public class ExtendedHtmlUnitDriver extends HtmlUnitDriver implements ExtendedWebDriver {

	public Map<String, String> getResponseHeaders() {
		final List<NameValuePair> nameValuePairs = getLastPageWebResponse().getResponseHeaders();
		return nameValuePairListToMap(nameValuePairs);
	}

	public String getResponseHeaderValue(final String name) {
		return getLastPageWebResponse().getResponseHeaderValue(name);
	}

	private WebResponse getLastPageWebResponse() {
		return lastPage().getWebResponse();
	}

	private Map<String, String> nameValuePairListToMap(final List<NameValuePair> nameValuePairs) {
		final Map<String, String> responseHeaders = new HashMap<String, String>();
		for (final NameValuePair nameValuePair : nameValuePairs) {
			responseHeaders.put(nameValuePair.getName(), nameValuePair.getValue());
		}
		return responseHeaders;
	}

}
