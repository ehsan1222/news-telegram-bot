package ir.hamyiar.newstb.services;

import ir.hamyiar.newstb.dao.News;
import ir.hamyiar.newstb.helper.XmlHelper;
import ir.hamyiar.newstb.net.NewsConnection;
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

    @PostConstruct
    public void process() {
        // Download RSS Data from site
        String xmlRSS = NewsConnection.getXmlRSS(IRNA_URL);
        // Parse downloaded file and convert to News Entity
        List<News> todayNews = xmlHelper.parse(xmlRSS);
        addPreIdToNewsId(todayNews);

    }

    public void addPreIdToNewsId(List<News> newsList) {
        for (int i = 0; i < newsList.size(); i++) {
            newsList.get(i).setPreId(PRE_IRNA_ID);
            newsList.get(i).setId(PRE_IRNA_ID + newsList.get(i).getId());
        }
    }

}
