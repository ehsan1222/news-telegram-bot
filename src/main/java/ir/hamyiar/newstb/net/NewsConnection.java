package ir.hamyiar.newstb.net;

import java.io.*;
import java.net.URL;

public class NewsConnection {

    public static String getXmlRSS(String rssPath) {
        try {
            // Get stream data from site
            URL url = new URL(rssPath);
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Parse RSS Data
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
