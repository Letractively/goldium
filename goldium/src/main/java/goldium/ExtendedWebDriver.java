package goldium;

import java.util.Map;

import org.openqa.selenium.WebDriver;

public interface ExtendedWebDriver extends WebDriver {

	Map<String, String> getResponseHeaders();

	String getResponseHeaderValue(String name);

}
