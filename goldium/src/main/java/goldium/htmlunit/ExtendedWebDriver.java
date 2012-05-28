package goldium.htmlunit;

import java.util.Map;

import org.openqa.selenium.WebDriver;

public interface ExtendedWebDriver extends WebDriver {

	Map<String, String> getResponseHeaders(String url);

}
