import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherUpdater {
    private URL METARFeed;
    private URL weatherReportFeed;
    private URL weatherForecastFeed;
    private WeatherLogger weatherLogger;
    private METARRetriever METARRetriever;
    private WeatherReportRetriever weatherReportRetriever;
    private WeatherForecastRetriever weatherForecastRetriever;
    private METAR METAR;
    private WeatherReport weatherReport;
    private WeatherForecast weatherForecast;
    
    public WeatherUpdater(URL METARFeed, URL weatherReportFeed, URL weatherForecastFeed, WeatherLogger weatherLogger) {
        this.METARFeed = METARFeed;
        this.weatherReportFeed = weatherReportFeed;
        this.weatherForecastFeed = weatherForecastFeed;
        this.weatherLogger = weatherLogger;
        this.METARRetriever = new METARRetriever();
        this.weatherReportRetriever = new WeatherReportRetriever();
        this.weatherForecastRetriever = new WeatherForecastRetriever();
    }
    
    public void updateEvery(int seconds) {
        Runnable updateWeather = () -> {
            if (METAR == null || !METARRetriever.getMETAR(METARFeed).toString().equals(METAR.toString())) {
                METAR = METARRetriever.getMETAR(METARFeed);
                System.out.println(METAR);
            }
            
            if (weatherReport == null || !weatherReportRetriever.getWeatherReport(weatherReportFeed).getTime().equals(weatherReport.getTime())) {
                weatherReport = weatherReportRetriever.getWeatherReport(weatherReportFeed);
                System.out.println(weatherReport);
                try {
                    weatherLogger.log(weatherReport);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            if (weatherForecast == null || !weatherForecastRetriever.getWeatherForecast(weatherForecastFeed).getIssuedTime().get(0).equals(weatherForecast.getIssuedTime().get(0))) {
                weatherForecast = weatherForecastRetriever.getWeatherForecast(weatherForecastFeed);
                System.out.println(weatherForecast);
            }
        };
        
        ScheduledExecutorService weatherService = Executors.newSingleThreadScheduledExecutor();
        weatherService.scheduleAtFixedRate(updateWeather, 0, seconds, TimeUnit.SECONDS);
    }
}
