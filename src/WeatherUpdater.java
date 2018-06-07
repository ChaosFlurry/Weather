import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherUpdater {
    private URL weatherFeed;
    private WeatherLogger weatherLogger;
    private WeatherReportRetriever weatherReportRetriever;
    private WeatherForecastRetriever weatherForecastRetriever;
    private WeatherReport weatherReport;
    private WeatherForecast weatherForecast;
    
    public WeatherUpdater(URL weatherFeed, WeatherLogger weatherLogger) {
        this.weatherFeed = weatherFeed;
        this.weatherLogger = weatherLogger;
        this.weatherReportRetriever = new WeatherReportRetriever();
        this.weatherForecastRetriever = new WeatherForecastRetriever();
    }
    
    public void updateEvery(int seconds) {
        Runnable updateWeather = () -> {
            if (weatherReport == null || !weatherReportRetriever.getWeatherReport(weatherFeed).getTime().equals(weatherReport.getTime())) {
                weatherReport = weatherReportRetriever.getWeatherReport(weatherFeed);
                System.out.println(weatherReport);
                try {
                    weatherLogger.log(weatherReport);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            if (weatherForecast == null || !weatherForecastRetriever.getWeatherForecast(weatherFeed).getIssuedTime().get(0).equals(weatherForecast.getIssuedTime().get(0))) {
                weatherForecast = weatherForecastRetriever.getWeatherForecast(weatherFeed);
                System.out.println(weatherForecast);
            }
        };
        
        ScheduledExecutorService weatherService = Executors.newSingleThreadScheduledExecutor();
        weatherService.scheduleAtFixedRate(updateWeather, 0, seconds, TimeUnit.SECONDS);
    }
}
