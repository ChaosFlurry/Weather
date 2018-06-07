import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class Weather {
    public static void main(String[] args) throws MalformedURLException {
        URL weatherFeed = new URL("https://weather.gc.ca/rss/city/bc-74_e.xml");
        String weatherLogDirectoryString = System.getProperty("user.home") + "\\Desktop\\Weather";
        String weatherLogFileString = "weatherLog.txt";
        WeatherLogger weatherLogger = new WeatherLogger(Paths.get(weatherLogDirectoryString + "\\" + weatherLogFileString));
        WeatherUpdater weatherUpdater = new WeatherUpdater(weatherFeed, weatherLogger);
        weatherUpdater.updateEvery(300);
    }
}
