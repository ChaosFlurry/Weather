import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class Weather {
    public static void main(String[] args) throws MalformedURLException {
        URL METARFeed = new URL("https://www.aviationweather.gov/adds/dataserver_current/httpparam?dataSource=metars&requestType=retrieve&format=xml&stationString=CYVR&hoursBeforeNow=1");
        URL weatherReportFeed = new URL("https://weather.gc.ca/rss/city/bc-74_e.xml");
        URL weatherForecastFeed = new URL("https://weather.gc.ca/rss/city/bc-74_e.xml");
        String weatherLogDirectoryString = System.getProperty("user.home") + "\\Desktop\\Weather";
        String weatherLogFileString = "weatherLog.txt";
        WeatherLogger weatherLogger = new WeatherLogger(Paths.get(weatherLogDirectoryString + "\\" + weatherLogFileString));
        WeatherUpdater weatherUpdater = new WeatherUpdater(METARFeed, weatherReportFeed, weatherForecastFeed, weatherLogger);
        weatherUpdater.updateEvery(300);
        
        /*
        METAR Regex
        
         */
    }
}
