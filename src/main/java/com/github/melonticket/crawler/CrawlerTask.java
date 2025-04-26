package com.github.melonticket.crawler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CrawlerTask {
    @Scheduled(cron="0/5 * *  * * ? ")
    public void crawler() {
        MainCrawler mainCrawler = new MainCrawler();
        mainCrawler.run();
        mainCrawler.close();
    }
}
