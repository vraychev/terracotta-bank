package com.joshcummings.codeplay.terracotta.testng;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ZaleniumSupport {

    private static String ZAP_HOST = "127.0.0.1";
    private static Integer ZAP_PORT = 8090;

    public WebDriver start() throws MalformedURLException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=$WINDOW_WIDTH,$WINDOW_HEIGHT");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("disable-geolocation");

//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

//        String zaleniumUrl = !StringUtils.isEmpty(System.getProperty("app.zalenium.url")) ?
//            System.getProperty("app.zalenium.url") : "http://localhost:4444/wd/hub";
//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("owasp-zap:8090");
//        proxy.setFtpProxy("owasp-zap:8090");
//        proxy.setSslProxy("owasp-zap:8090");
//        capabilities.setCapability(CapabilityType.PROXY, proxy);
//        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(new RemoteWebDriver(new URL(zaleniumUrl), capabilities));
//        WebDriverEventListener securityScannerEventListener = new SecurityScannerEventListener();
//        ((SecurityScannerEventListener) securityScannerEventListener).setZapScanner(new ZapProxyScanner("127.0.0.1", 8090));
//        eventFiringWebDriver.register(securityScannerEventListener);
//        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(chromeOptions);
    }

    public void stop(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
