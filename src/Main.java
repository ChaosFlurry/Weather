import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("unused")
public class Main {
    public static void main(String[] args) {
        // Vancouver RSS - https://weather.gc.ca/rss/city/bc-74_e.xml
        // French - http://www.meteo.gc.ca/rss/city/bc-74_f.xml
        // Vancouver alerts - https://weather.gc.ca/rss/warning/bc-74_e.xml
        // French - http://meteo.gc.ca/rss/warning/bc-74_f.xml
        
        /*
        way of mapping city names to codes
        e.g. Vancouver - bc-84
        Toronto - on-143
         */
        
        URL vancouver = null;
        try {
            vancouver = new URL("https://weather.gc.ca/rss/city/bc-74_e.xml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        WeatherReportUpdater weather = new WeatherReportUpdater(vancouver);
        weather.update();
        System.out.println("M U L T I T H R E A D I N G");
    }
}
