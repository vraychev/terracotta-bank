package com.joshcummings.codeplay.terracotta.testng;

import com.joshcummings.codeplay.terracotta.security.SecurityScannerEventListener;
import com.joshcummings.codeplay.terracotta.security.ZapProxyScanner;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumSupport {

    private static String ZAP_HOST = "localhost";
    private static Integer ZAP_PORT = 8090;

    public WebDriver start() {
        /*WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();

//        profile.setPreference("network.proxy.type", 1);
//        profile.setPreference("network.proxy.http", ZAP_HOST);
//        profile.setPreference("network.proxy.http_port", ZAP_PORT);
//        profile.setPreference("network.proxy.ssl", ZAP_HOST);
//        profile.setPreference("network.proxy.ssl_port", ZAP_PORT);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        return new FirefoxDriver(options);*/
        return startChromeDriver();
    }

    public void stop(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    private WebDriver startChromeDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("disable-geolocation");
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("owasp-zap:8090");
        proxy.setFtpProxy("owasp-zap:8090");
        proxy.setSslProxy("owasp-zap:8090");
        capabilities.setCapability(CapabilityType.PROXY, proxy);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        try {
            EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
            SecurityScannerEventListener securityScannerEventListener = new SecurityScannerEventListener();
            securityScannerEventListener.setZapScanner(new ZapProxyScanner("127.0.0.1", 8090));
            eventFiringWebDriver.register(securityScannerEventListener);
            return eventFiringWebDriver;
        } catch (MalformedURLException e) {

        }
        return null;
    }
}
