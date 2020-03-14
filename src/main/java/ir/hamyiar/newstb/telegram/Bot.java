package ir.hamyiar.newstb.telegram;

import ir.hamyiar.newstb.dao.News;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class Bot {

    @Value("${bot.token}")
    private String botToken;

    @Value("${channel.id}")
    private String channelId;

    public String sendImagePostToChannel(News news) {

        String photoPostUrl = "https://api.telegram.org/bot%s/sendPhoto?chat_id=%s&caption=%s&parse_mode=Markdown&photo=%s";

        String caption = "*" + news.getNewsTitle() + "*\n\n\n" +
                         "```" +
                         news.getNewsDescription() +
                         " ```\n\n" +
                         "[لینک خبر](" + news.getNewsLink() + ")\n\n" +
                         "[" + news.getSiteTitle() + "](" + news.getSiteAddress() + ")";

        System.out.println(botToken);
        System.out.println(channelId);
        System.out.println(news.getNewsImageUrl());
        String telegramUrl = String.format(
                photoPostUrl,
                botToken,
                channelId,
                URLEncoder.encode(caption, StandardCharsets.UTF_8),
                URLEncoder.encode(news.getNewsImageUrl(), StandardCharsets.UTF_8));

        return sendDataToTelegram(telegramUrl);
    }

    private String sendDataToTelegram(String telegramUrl) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(telegramUrl);


            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
