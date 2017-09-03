import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("unused")
public class Weather {
    public static void main(String[] args) {
        URL city = null;
        try {
            city = new URL("https://weather.gc.ca/rss/city/bc-74_e.xml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        WeatherReportUpdater weather = new WeatherReportUpdater(city);
        weather.update();
    }
}
