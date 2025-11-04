package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    private static final String BROWSERSTACK_APP_URL = "bs://642ff606f26f9b11ee8e933187b2eaf0b139627a";
    private static AndroidDriver driver;

    public static AndroidDriver getInstance() {
        if (driver == null) {
            var options = setCapabilities();
            try {
                driver = new AndroidDriver(new URL("https://hub.browserstack.com/wd/hub"), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Wrong url");
            }
        }
        return driver;
    }

    public static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void quit() {
        driver = null;
    }

    private static UiAutomator2Options setCapabilities() {
        Map<String, Object> bstack = new HashMap<>();
        bstack.put("userName", System.getProperty("BROWSERSTACK_USERNAME", System.getenv("BROWSERSTACK_USERNAME")));
        bstack.put("accessKey", System.getProperty("BROWSERSTACK_ACCESS_KEY", System.getenv("BROWSERSTACK_ACCESS_KEY")));
        bstack.put("deviceName", "Google Pixel 7");
        bstack.put("buildName", "Appium Java Demo");
        bstack.put("sessionName", "Smoke - Android");
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setApp(BROWSERSTACK_APP_URL);
        options.setCapability("bstack:options", bstack);
        return options;
    }
}
