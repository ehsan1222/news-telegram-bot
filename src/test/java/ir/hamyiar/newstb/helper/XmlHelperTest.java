package ir.hamyiar.newstb.helper;

import ir.hamyiar.newstb.dao.News;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class XmlHelperTest {

    private XmlHelper xmlHelper;

    @Before
    public void startUp() {
        xmlHelper = new XmlHelper();
    }

    @Test
    public void createNewsItem() throws ParserConfigurationException, IOException, SAXException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String sample = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<item>\n" +
                "      <title>معاون استاندار تهران:\u200C هر نوع خبری مبنی بر کمبود کالا تکذیب می\u200Cشود</title>\n" +
                "      <link>https://www.irna.ir/news/83714160/معاون-استاندار-تهران-هر-نوع-خبری-مبنی-بر-کمبود-کالا-تکذیب-می-شود</link>\n" +
                "      <description>تهران- ایرنا- معاون امنیتی و انتظامی استاندار تهران اعلام کرد: هر نوع خبری مبنی بر کمبود کالا در فروشگاه ها و واحدهای صنفی این استان تکذیب می\u200Cشود و در این زمینه هیچ کمبودی وجود ندارد.</description>\n" +
                "      <content:encoded />\n" +
                "      <enclosure url=\"https://img9.irna.ir/d/r2/2019/12/21/3/156838207.jpg\" type=\"image/jpeg\" />\n" +
                "      <category domain=\"tehran\">استان\u200Cها &gt; تهران</category>\n" +
                "      <pubDate>Sat, 14 Mar 2020 15:42:09 GMT</pubDate>\n" +
                "      <guid>https://www.irna.ir/news/83714160/معاون-استاندار-تهران-هر-نوع-خبری-مبنی-بر-کمبود-کالا-تکذیب-می-شود</guid>\n" +
                "    </item>";
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(sample));
        Document document = documentBuilder.parse(inputSource);
        document.getDocumentElement().normalize();

        Element element = (Element) document.getElementsByTagName("item").item(0);

        Class<?> clazz = XmlHelper.class;
        Method createNewsItem = clazz.getDeclaredMethod("createNewsItem", Element.class);
        createNewsItem.setAccessible(true);
        News expected = (News) createNewsItem.invoke(xmlHelper, element);
        createNewsItem.setAccessible(false);

        News actual = new News();
        actual.setId("83714160");
        actual.setNewsTitle("معاون استاندار تهران:\u200C هر نوع خبری مبنی بر کمبود کالا تکذیب می\u200Cشود");
        actual.setNewsLink("https://www.irna.ir/news/83714160/معاون-استاندار-تهران-هر-نوع-خبری-مبنی-ب" +
                "ر-کمبود-کالا-تکذیب-می-شود");
        actual.setNewsDescription("تهران- ایرنا- معاون امنیتی و انتظامی استاندار تهران اعلام کرد: هر نوع خبری مبنی بر کمبود کالا در فروشگاه ها و واحدهای صنفی این استان تکذیب می\u200Cشود و در این زمینه هیچ کمبودی وجود ندارد.");
        actual.setPublishDate(new Date(1584200529000L));

        Assert.assertEquals(expected, actual);

    }


    @Test
    public void extractIdFromLink() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String link1 = "https://www.irna.ir/news/83714198/مقابله-با-کرونا-مستلزم-اقدامات-هماهنگ-و-مشترک-منطقه-ای-و-بین-المللی";
        String link2 = "https://www.irna.ir/news/83714160/معاون-استاندار-تهران-هر-نوع-خبری-مبنی-بر-کمبود-کالا-تکذیب-می-شود";

        Class<?> aClass = XmlHelper.class;
        Method extractIdFromLink = aClass.getDeclaredMethod("extractIdFromLink", String.class);
        extractIdFromLink.setAccessible(true);

        String expected1 = (String) extractIdFromLink.invoke(xmlHelper, link1);
        String expected2 = (String) extractIdFromLink.invoke(xmlHelper, link2);

        extractIdFromLink.setAccessible(false);

        String actual1 = "83714198";
        String actual2 = "83714160";
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void convertStringToDate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String newsTestPubDate = "Sat, 14 Mar 2020 15:42:09 GMT";
        Date actual = new Date(1584200529000L);

        Class<?> clazz = XmlHelper.class;
        Method convertStringToDate = clazz.getDeclaredMethod("convertStringToDate", String.class);
        convertStringToDate.setAccessible(true);
        Date expected = (Date) convertStringToDate.invoke(xmlHelper, newsTestPubDate);

        System.out.println(expected);

        Assert.assertEquals(expected, actual);
        Assert.assertEquals(expected.getTime(), actual.getTime());

    }
}