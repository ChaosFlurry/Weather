import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Weather {
    // TODO improve this
    private static WeatherReport weatherReport = null;
    private static WeatherForecast weatherForecast = null;
    
    public static void main(String[] args) throws MalformedURLException {
        URL weatherFeed = new URL("https://weather.gc.ca/rss/city/bc-74_e.xml");
        
        WeatherReportRetriever weatherReportRetriever = new WeatherReportRetriever();
        WeatherForecastRetriever weatherForecastRetriever = new WeatherForecastRetriever();
        
        Runnable updateWeather = () -> {
            if (weatherReport == null || !weatherReportRetriever.getWeatherReport(weatherFeed).getTime().equals(weatherReport.getTime())) {
                weatherReport = weatherReportRetriever.getWeatherReport(weatherFeed);
                System.out.println(weatherReport);
            }
            
            if (weatherForecast == null || !weatherForecastRetriever.getWeatherForecast(weatherFeed).getIssuedTime().get(0).equals(weatherForecast.getIssuedTime().get(0))) {
                weatherForecast = weatherForecastRetriever.getWeatherForecast(weatherFeed);
                System.out.println(weatherForecast);
            }
        };
        
        // Updates the weather every 5 minutes only if there has been an update
        ScheduledExecutorService weatherService = Executors.newSingleThreadScheduledExecutor();
        weatherService.scheduleAtFixedRate(updateWeather, 0, 300, TimeUnit.SECONDS);
        
    }
}
