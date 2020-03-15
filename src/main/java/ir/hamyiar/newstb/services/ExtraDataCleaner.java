package ir.hamyiar.newstb.services;

import ir.hamyiar.newstb.dao.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class ExtraDataCleaner {

    @Autowired
    private NewsService newsService;

    @PostConstruct
    @Scheduled(cron = "0 0 0 0/2 * *")
    public void cleanDB() {
        List<News> allNews = newsService.getAllNews();
        for (News news : allNews) {
            Date newsPublishDate = news.getPublishDate();
            LocalDate newsLocalDate = newsPublishDate.toInstant().atZone(ZoneId.of("GMT")).toLocalDate();

            LocalDate todayLocalDate = new Date().toInstant().atZone(ZoneId.of("GMT")).toLocalDate();

            Period differenceLocalDate = Period.between(newsLocalDate, todayLocalDate);
            if (differenceLocalDate.getDays() > 1) {
                // TODO: Test delete items correctly
                System.out.println("News Deleted: " + news.getId());
                newsService.deleteNews(news.getId());
            }
        }
    }
}
