package com.github.melonticket.crawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CrawlerTask {
    @Autowired
    private MainCrawler mainCrawler;
    @Scheduled(fixedDelay = 5000)
    public void crawler() {
        mainCrawler.run();
    }
}
