package com.joshcummings.codeplay.terracotta.testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumSupport {

    private static String ZAP_HOST = "localhost";
    private static Integer ZAP_PORT = 8090;

    public WebDriver start() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxProfile profile = new FirefoxProfile();

//        profile.setPreference("network.proxy.type", 1);
//        profile.setPreference("network.proxy.http", ZAP_HOST);
//        profile.setPreference("network.proxy.http_port", ZAP_PORT);
//        profile.setPreference("network.proxy.ssl", ZAP_HOST);
//        profile.setPreference("network.proxy.ssl_port", ZAP_PORT);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        return new FirefoxDriver(options);
    }

    public void stop(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
