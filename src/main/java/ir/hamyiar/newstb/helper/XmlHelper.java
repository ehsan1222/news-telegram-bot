package ir.hamyiar.newstb.helper;

import ir.hamyiar.newstb.dao.News;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XmlHelper {

    public List<News> parse(String xmlData) {
        List<News> parsedNews = new ArrayList<>();

        try {
            Document document = loadXmlFromString(xmlData);

            NodeList channelNodeList = document.getElementsByTagName("channel");
            for (int i = 0; i < channelNodeList.getLength(); i++) {
                Node channelNode = channelNodeList.item(i);
                Element channelElement = (Element) channelNode;

                String siteTitle = channelElement.getElementsByTagName("title").item(0).getTextContent();
                String siteLink = channelElement.getElementsByTagName("link").item(0).getTextContent();

                NodeList itemNodeList = channelElement.getElementsByTagName("item");
                for (int j = 0; j < itemNodeList.getLength(); j++) {
                    Node itemNode = itemNodeList.item(j);
                    Element itemElement = (Element) itemNode;

                    News news = createNewsItem(itemElement);
                    news.setSiteTitle(siteTitle);
                    news.setSiteAddress(siteLink);

                    parsedNews.add(news);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
            e.printStackTrace();
        }
        return parsedNews;
    }

    private News createNewsItem(Element itemElement) throws ParseException {
        // Get news information from element
        String newsTitle = itemElement.getElementsByTagName("title").item(0).getTextContent();
        String newsLink = itemElement.getElementsByTagName("link").item(0).getTextContent();
        String newsDescription = itemElement.getElementsByTagName("description")
                .item(0).getTextContent();
        String newsImageUrl = itemElement.getElementsByTagName("enclosure")
                .item(0).getAttributes().getNamedItem("url").getTextContent();
        String newsTextPubDate = itemElement.getElementsByTagName("pubDate").item(0).getTextContent();

        Date publishDate = convertStringToDate(newsTextPubDate);
        String extractedId = extractIdFromLink(newsLink);

        News news = new News();
        news.setId(extractedId);
        news.setNewsTitle(newsTitle);
        news.setNewsLink(newsLink);
        news.setNewsDescription(newsDescription);
        news.setPublishDate(publishDate);
        return news;
    }

    private String extractIdFromLink(String newsLink) {
        String substring = newsLink.substring(newsLink.indexOf("/news/") + 6);
        return substring.substring(0, substring.indexOf("/"));
    }

    private Date convertStringToDate(String newsTextPubDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
        return dateFormat.parse(newsTextPubDate);
    }

    private Document loadXmlFromString(String xmlData) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xmlData));
        Document document = documentBuilder.parse(inputSource);
        document.getDocumentElement().normalize();
        return document;
    }

}
