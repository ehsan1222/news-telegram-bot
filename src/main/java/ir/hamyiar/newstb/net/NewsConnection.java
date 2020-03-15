package ir.hamyiar.newstb.net;

import java.io.*;
import java.net.URL;

public class NewsConnection {

    public static String getXmlRSS(String rssPath) {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            // Get stream data from site
            URL url = new URL(rssPath);
            inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Parse RSS Data
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
