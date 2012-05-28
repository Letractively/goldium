package goldium;

import java.util.Map;

import org.openqa.selenium.WebDriver;

public interface ExtendedWebDriver extends WebDriver {

	Map<String, String> getResponseHeaders();

	String getResponseHeaderValue(String name);

	int getStatusCode();

	void addRequestHeader(String name, String value);

	void removeRequestHeader(String name);

}
