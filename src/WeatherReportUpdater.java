import java.net.URL;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class WeatherReportUpdater {
    private URL rssFeed;
    private WeatherReport previousReport;
    private WeatherReport currentReport;
    
    public WeatherReportUpdater(URL rssFeed) {
        this.rssFeed = rssFeed;
    }
    
    public void update() {
        ZonedDateTime currentTime = ZonedDateTime.now();
        int nextMinuteValue = currentTime.getMinute() + (5 - (currentTime.getMinute() % 5));
        ZonedDateTime nextUpdate = currentTime.withMinute(nextMinuteValue).withSecond(0);
        if (currentTime.compareTo(nextUpdate) > 0) {
            nextUpdate.plusDays(1);
        }
        
        Duration duration = Duration.between(currentTime, nextUpdate);
        long delay = duration.toMillis();
        long fiveMinutes = 1000 * 60 * 5;
        
        Runnable getWeather = () -> {
            currentReport = WeatherReportRetriever.getCurrentWeatherReport(rssFeed);
            if (previousReport == null || !currentReport.getTime().equals(previousReport.getTime())) {
                WeatherReportExporter.exportAll(currentReport);
            }
            previousReport = currentReport;
        };
        
        currentReport = WeatherReportRetriever.getCurrentWeatherReport(rssFeed);
        WeatherReportExporter.exportAll(currentReport);
        previousReport = currentReport;
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(getWeather, delay, fiveMinutes, TimeUnit.MILLISECONDS);
    }
}