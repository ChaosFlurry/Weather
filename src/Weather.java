import java.net.MalformedURLException;
import java.net.URL;

public class Weather {
    public static void main(String[] args) {
        URL city = null;
        try {
            city = new URL("https://weather.gc.ca/rss/city/bc-74_e.xml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        WeatherReportRetriever weatherReportRetriever = new WeatherReportRetriever();
        WeatherForecastRetriever weatherForecastRetriever = new WeatherForecastRetriever();
        
        WeatherReport weatherReport = weatherReportRetriever.getWeatherReport(city);
        WeatherForecast weatherForecast = weatherForecastRetriever.getWeatherForecast(city);
        
        System.out.println(weatherReport);
        System.out.println(weatherForecast);
    }
}
