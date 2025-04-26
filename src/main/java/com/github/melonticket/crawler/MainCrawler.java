package com.github.melonticket.crawler;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainCrawler implements Runnable{
    private static final String URL = "https://tkglobal.melon.com/main/index.htm?langCd=EN";
    private WebDriver driver;

    public MainCrawler() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");

        // 添加自定义请求头
        chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromeOptions.setExperimentalOption("useAutomationExtension", false);

        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        chromeOptions.setExperimentalOption("mobileEmulation", headers);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("127.0.0.1:7890");
        proxy.setSslProxy("127.0.0.1:7890");
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        chromeOptions.merge(capabilities);
        driver = new ChromeDriver(chromeOptions);
    }

    @Override
    public void run() {
        driver.get(URL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement titleContentElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='header']"))
        );

        WebElement AVAILABLENOW = driver.findElement(By.xpath("//*[@id=\"conts\"]/div/div[1]/ul"));
        List<WebElement> li = AVAILABLENOW.findElements(By.tagName("li"));
        WebElement PASTEVENT = driver.findElement(By.xpath("//*[@id=\"conts\"]/div/div[2]/ul"));
        List<WebElement> li2 = PASTEVENT.findElements(By.tagName("li"));
        parseLi(li);
        parseLi(li2);
    }

    public void parseLi(List<WebElement> lis) {
        for (WebElement element : lis) {
            List<WebElement> img = element.findElements(By.tagName("img"));
            WebElement webElement = img.get(0);
            String src = webElement.getDomAttribute("src");
            WebElement element1 = element.findElement(By.xpath("//div[@class='article']"));
            WebElement h2 = element1.findElement(By.tagName("h2"));
            String title = h2.getText();
            System.out.println("title " + title);
            WebElement element2 = element1.findElement(By.xpath("//dl[@class='main_concert_info']"));
            List<WebElement> elements = element2.findElements(By.tagName("dt"));
            List<WebElement> elements1 = element2.findElements(By.tagName("dd"));
            for (int i = 0; i < elements.size(); i++) {
                WebElement dt = elements.get(i);
                WebElement dd = elements1.get(i);
                String text =  dt.getAttribute("innerHTML");
                String text1 = dd.getText();
                System.out.println(text + ":" + text1);
            }
        }
    }
    
    public void close() {
        driver.quit();
    }
}
