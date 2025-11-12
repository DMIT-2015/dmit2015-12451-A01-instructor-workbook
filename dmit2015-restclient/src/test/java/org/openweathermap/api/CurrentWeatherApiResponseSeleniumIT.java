package org.openweathermap.api;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurrentWeatherApiResponseSeleniumIT {

    private static WebDriver driver;

    @BeforeAll
    static void setupClass() {
        // You can download the latest version of Selenium Manager from https://github.com/titusfortner/selenium_manager_debug/releases
        // Use the command `./selenium-manager --browser chrome` to download the webdriver for Chrome browser
        // Use the command `./selenium-manager --browser firefox` to download the webdriver for Firefox browser
        // System.setProperty("webdriver.chrome.driver", "/home/user2015/.cache/selenium/chromedriver/linux64/122.0.6261.128/chromedriver");
        // System.setProperty("webdriver.gecko.driver", "/snap/bin/geckodriver");
        // WebDriverManager.chromedriver().setup();

        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);

    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();

//        driver = new FirefoxDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    private void setValue(String id, String value) {
        WebElement element = driver.findElement(By.id(id));
        element.clear();
        element.sendKeys(value);
    }

    @Test
    void shouldGetWeather() {
        driver.get("http://localhost:8080/index.xhtml");
        assertEquals("DMIT2015 REST Client Demo", driver.getTitle());

        setValue("city", "Edmonton");

        driver.manage().window().maximize();
        driver.findElement(By.id("submit")).click();

        var wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        var facesMessages = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("ui-messages-info-summary")));
        String feedbackMessage = facesMessages.getText();

        assertThat(feedbackMessage, containsString("The current weather in Colorado is 5.25"));
    }

}