package com.github.melonticket;


import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumTest {
    public static void main(String[] args) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("127.0.0.1:7890");
        proxy.setSslProxy("127.0.0.1:7890");
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        chromeOptions.merge(capabilities);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://read.douban.com/reader/column/4639817/chapter/24562734/?dcs=column&dcm=chapter-list");

        // 显式等待标题元素加载
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement titleContentElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='chapter-header']/h1"))
        );
        String titleContent = titleContentElement.getText();
        System.out.println(titleContent);

        //获取文章内容（此div包含标题）
        WebElement articleContentElement = driver.findElement(By.xpath("//div[@class='article']"));
        String articleContent = articleContentElement.getText();
        System.out.println(articleContent);
    }
}
