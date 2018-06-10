import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherReportRetriever {
    public WeatherReport getWeatherReport(URL weatherFeed) {
        StringBuilder weatherReportStringBuilder = new StringBuilder();
        try (BufferedReader weatherFeedReader = new BufferedReader(new InputStreamReader(weatherFeed.openStream()))) {
            String line;
            while ((line = weatherFeedReader.readLine()) != null) {
                weatherReportStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String weatherReportRawData = weatherReportStringBuilder.toString();
    
        // group 1 - matches city name
        Pattern cityPattern = Pattern.compile("<title>(.+) - Weather - Environment Canada</title>");
        Matcher cityMatcher = cityPattern.matcher(weatherReportRawData);
        
        // group 1 - matches observation location name
        // group 2 - matches time of day
        // group 3 - matches weather condition
        // group 4 - matches temperature
        // group 5 - matches pressure
        // group 6 - matches pressure tendency
        // group 7 - matches visibility
        // group 8 - matches humidity
        // group 9 - matches wind chill (optional)
        // group 10 - matches dew point
        // group 11 - matches wind direction
        // group 12 - matches wind speed
        // group 13 - matches wind gust speed (optional)
        // group 14 - matches air quality health index
        Pattern reportDataPattern = Pattern.compile("<summary type=\"html\"><!\\[CDATA\\[<b>Observed at:</b> (.+?) (\\d+:\\d+ (?:AM|PM) [A-Z]{3} (?:Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday) \\d{2} [A-z]+ \\d{4}) <br/>\\n<b>Condition:</b> (.+?) <br/>\\n<b>Temperature:</b> (-?\\d+\\.\\d)&deg;C <br/>\\n<b>Pressure / Tendency:</b> (\\d+\\.\\d) kPa (rising|falling)<br/>\\n<b>Visibility:</b> (\\d+\\.\\d) km<br/>\\n<b>Humidity:</b> (\\d+) %<br/>\\n(?:<b>Wind Chill:</b> (-?\\d+) <br/>\\n)?<b>Dewpoint:</b> (-?\\d+\\.\\d)&deg;C <br/>\\n<b>Wind:</b> ([A-Z]{1,3}) (\\d+) km/h(?: gust (\\d+) km/h)?<br/>\\n<b>Air Quality Health Index:</b> (\\d+) <br/>\\n]]></summary>");
        Matcher reportDataMatcher = reportDataPattern.matcher(weatherReportRawData);
        
        WeatherReport.WeatherReportBuilder weatherReportBuilder = new WeatherReport.WeatherReportBuilder();
        
        if (cityMatcher.find()) {
            weatherReportBuilder.setCity(cityMatcher.group(1));
        } else {
            // TODO Handle exception
        }
        
        if (reportDataMatcher.find()) {
            weatherReportBuilder
                    .setRawText(reportDataMatcher.group(0))
                    .setObservedAt(reportDataMatcher.group(1))
                    .setTime(reportDataMatcher.group(2))
                    .setCondition(reportDataMatcher.group(3))
                    .setTemperature(reportDataMatcher.group(4))
                    .setPressure(reportDataMatcher.group(5))
                    .setTendency(reportDataMatcher.group(6))
                    .setVisibility(reportDataMatcher.group(7))
                    .setHumidity(reportDataMatcher.group(8))
                    .setWindchill(reportDataMatcher.group(9))
                    .setDewPoint(reportDataMatcher.group(10))
                    .setWindDirection(reportDataMatcher.group(11))
                    .setWindSpeed(reportDataMatcher.group(12))
                    .setWindGust(reportDataMatcher.group(13))
                    .setAirQualityHealthIndex(reportDataMatcher.group(14));
        } else {
            // TODO Handle exceptions
        }
        return weatherReportBuilder.build();
    }
}
