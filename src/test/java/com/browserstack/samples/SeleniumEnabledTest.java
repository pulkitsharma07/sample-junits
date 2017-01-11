package com.browserstack.samples;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Some simple selenium tests.
 *
 * @author Anirudha Khanna
 */
public class SeleniumEnabledTest {

    public static final String USERNAME = System.getProperty("browserstack.user", System.getenv("BROWSERSTACK_USER"));
    public static final String AUTOMATE_KEY = System.getProperty("browserstack.access_key", System.getenv("BROWSERSTACK_ACCESS_KEY"));
    public static final String HUB_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    private static final String OUTPUT_FORMAT = "browserstack:session:%s:test:%s";

    private RemoteWebDriver remoteWebDriver;

    @Before
    public void before() throws MalformedURLException {
	System.out.println("HOKA POKA SAKI NAKA");
        DesiredCapabilities caps = new DesiredCapabilities();

        System.out.println(USERNAME);
        System.out.println(AUTOMATE_KEY);
        caps.setCapability("project", "Maven Build Plugin");
        caps.setCapability("name", "Automate Maven Plugin Test - Sample JUnit Tests");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "50.0");
        caps.setCapability("os", "OS X");
        caps.setCapability("os_version", "El Capitan");
        caps.setCapability("resolution", "1024x768");

        this.remoteWebDriver = new RemoteWebDriver(new URL(HUB_URL), caps);
        String sessionInfo = String.format(OUTPUT_FORMAT, remoteWebDriver.getSessionId(), "testGoogleTitle");
        System.out.println(sessionInfo);
    }

    @After
    public void after() {
        if (this.remoteWebDriver != null) {
            this.remoteWebDriver.quit();
        }

        this.remoteWebDriver = null;
    }

    @Test
    public void testGoogleTitle() throws Exception {
        this.remoteWebDriver.get("http://www.google.com");
        String title = this.remoteWebDriver.getTitle();
        Assert.assertEquals("Title Should be Google.", "Google", title);
    }

    @Test
    public void testYahooTitle() throws Exception {
        this.remoteWebDriver.get("http://www.yahoo.com");
        String title = this.remoteWebDriver.getTitle();
        Assert.assertEquals("Title Should be Google.", "Yahoo", title);
    }
}
