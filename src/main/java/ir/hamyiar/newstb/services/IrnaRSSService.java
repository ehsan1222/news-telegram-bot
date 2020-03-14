package ir.hamyiar.newstb.services;

import ir.hamyiar.newstb.dao.News;
import ir.hamyiar.newstb.helper.XmlHelper;
import ir.hamyiar.newstb.net.NewsConnection;
import ir.hamyiar.newstb.repository.NewsRepository;
import ir.hamyiar.newstb.telegram.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class IrnaRSSService {
    private final String IRNA_URL = "https://www.irna.ir/rss-homepage";

    public static final String PRE_IRNA_ID = "111111";

    @Autowired
    private XmlHelper xmlHelper;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private Bot bot;

    @PostConstruct
    public void process() {
        // Download RSS Data from site
        String xmlRSS = NewsConnection.getXmlRSS(IRNA_URL);
        // Parse downloaded file and convert to News Entity
        List<News> todayNews = xmlHelper.parse(xmlRSS);
        addPreIdToNewsId(todayNews);
        // TODO: Check this news id is not exist in data base then store it
        // TODO: if news is is not in database then send to telegram channel
        // TODO: Remove yesterday news in database
        checkNewsInDatabase(todayNews);
    }

    private void checkNewsInDatabase(List<News> todayNews) {
        for (News news : todayNews) {
            if (!newsRepository.findById(news.getId()).isPresent()) {
                // Store News Entity in Database
                newsRepository.save(news);
                // TODO: Send data to telegram channel
                String response = bot.sendImagePostToChannel(news);
                System.out.println(response);
            }
        }
    }

    public void addPreIdToNewsId(List<News> newsList) {
        for (int i = 0; i < newsList.size(); i++) {
            newsList.get(i).setPreId(PRE_IRNA_ID);
            newsList.get(i).setId(PRE_IRNA_ID + newsList.get(i).getId());
        }
    }

}
