import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WeatherForecastRetriever {
    public WeatherForecast getWeatherForecast(URL weatherFeed) {
        StringBuilder weatherForecastRawDataBuilder = new StringBuilder();
        try {
            BufferedReader weatherFeedReader = new BufferedReader(new InputStreamReader(weatherFeed.openStream()));
            String line;
            while ((line = weatherFeedReader.readLine()) != null) {
                weatherForecastRawDataBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String weatherForecastRawData = weatherForecastRawDataBuilder.toString();
        
        return null;
    }
}
