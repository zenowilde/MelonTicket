package com.github.melonticket.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CrawlerTask {
    @Autowired
    private MainCrawler mainCrawler;
    @Scheduled(cron="0/5 * *  * * ? ")
    public void crawler() {
        mainCrawler.run();
    }
}
