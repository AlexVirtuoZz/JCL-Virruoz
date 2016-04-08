package CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alex on 05.02.2016.
 */
public class IPAddress {

    public static void currentIP() {
        String result = null;
        try {
            BufferedReader reader = null;
            try {
                //Get an IP with myip.by service
                URL url = new URL("http://myip.by/");
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder allText = new StringBuilder();

                char[] buff = new char[1024];
                int count = 0;
                //Append all available info to search through
                while ((count = reader.read(buff)) != -1) {
                    allText.append(buff, 0, count);
                }
                // String , which contains required IP looks like
                // <a href="whois.php?127.0.0.1">whois 127.0.0.1</a>
                Integer indStart = allText.indexOf("\">whois ");
                Integer indEnd = allText.indexOf("</a>", indStart);

                String ipAddress = new String(allText.substring(indStart + 8, indEnd));
                if (ipAddress.split("\\.").length == 4) {
                    //Verification that given string is an IP
                    result = ipAddress;
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                //Close bufferedReader
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CommandLine.getPrintWriter().println(result);
    }
}
