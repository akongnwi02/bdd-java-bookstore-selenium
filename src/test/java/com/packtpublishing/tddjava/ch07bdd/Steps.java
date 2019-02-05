package com.packtpublishing.tddjava.ch07bdd;

//import com.codeborne.selenide.Condition;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Steps {
    private WebDriver webDriver;
    private Properties properties = new Properties();
    @BeforeStory
    public void beforeStory() throws Exception {
        if(webDriver == null) {
            properties.load(new FileReader(new File("src/test/resources/test.properties")));
            System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
            webDriver = new ChromeDriver();
            webDriver.manage()
                    .window()
                    .setSize(new Dimension(
                            1024,
                            768)
                    );
        }
    }

    private String url = "http://localhost:9001";

    @Given("user is on the books screen")
    public void givenUserIsOnTheBooksScreen() {
        webDriver.get(url);
        webDriver.findElement(By.id("books")).click();
    }

    @Then("field $elementId exists")
    public void thenFieldExists(String elementId) {
        webDriver.findElement(By.id(elementId)).isDisplayed();
    }
}
