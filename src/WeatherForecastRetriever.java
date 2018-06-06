import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        
        
        // group 1 - day
        // group 2 - forecast summary
        // group 3 - negative or positive temperature (optional)
        // group 4 - peak temperature value
        // group 5 - POP (optional)
        Pattern forecastSummaryPattern = Pattern.compile("<title>((?:Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday)(?: night)?): (.+)\\. (?:High|Low)(?: (plus|minus))? (\\d+|zero)\\.(?: POP (\\d+)%)?\\n?</title>");
        Matcher forecastSummaryMatcher = forecastSummaryPattern.matcher(weatherForecastRawData);
        
        // group 1 - condition details
        // group 2 - humidex (optional)
        // group 3 - uv index (optional)
        // group 4 - issued time
        Pattern forecastDetailsPattern = Pattern.compile("<summary type=\"html\">(.+)\\. (?:High|Low) (?:\\d+|zero)\\.(?: Humidex (\\d+)\\.)?(?: UV index (\\d+) or (?:low|moderate|high|very high|extreme)\\.)? Forecast issued (\\d+:\\d+ (?:AM|PM) [A-Z]{3} (?:Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday) \\d{2} [A-z]+ \\d{4})</summary>");
        Matcher forecastDetailsMatcher = forecastDetailsPattern.matcher(weatherForecastRawData);
        
        WeatherForecast.WeatherForecastBuilder weatherForecastBuilder = new WeatherForecast.WeatherForecastBuilder();
        
        List<String> day = new ArrayList<>();
        List<String> forecastSummary = new ArrayList<>();
        List<String> peakTemperature = new ArrayList<>();
        List<String> POP = new ArrayList<>();
        List<String> forecastDetails = new ArrayList<>();
        List<String> humidex = new ArrayList<>();
        List<String> UVIndex = new ArrayList<>();
        List<String> issuedTime = new ArrayList<>();
        
        while (forecastSummaryMatcher.find() && forecastDetailsMatcher.find()) {
            day.add(forecastSummaryMatcher.group(1));
            forecastSummary.add(forecastSummaryMatcher.group(2));
            
            if (forecastSummaryMatcher.group(3) != null) {
                if (forecastSummaryMatcher.group(3).equals("minus")) {
                    peakTemperature.add("-" + forecastSummaryMatcher.group(4));
                } else {
                    peakTemperature.add(forecastSummaryMatcher.group(4));
                }
            } else {
                if (forecastSummaryMatcher.group(4).equals("zero")) {
                    peakTemperature.add("0");
                } else {
                    peakTemperature.add(forecastSummaryMatcher.group(4));
                }
            }
            
            if (forecastSummaryMatcher.group(5) != null) {
                POP.add(forecastSummaryMatcher.group(5));
            } else {
                POP.add(null);
            }
            
            forecastDetails.add(forecastDetailsMatcher.group(1));
            
            if (forecastDetailsMatcher.group(2) != null) {
                humidex.add(forecastDetailsMatcher.group(2));
            } else {
                humidex.add(null);
            }
    
            if (forecastDetailsMatcher.group(3) != null) {
                UVIndex.add(forecastDetailsMatcher.group(3));
            } else {
                UVIndex.add(null);
            }
            
            issuedTime.add(forecastDetailsMatcher.group(4));
        }
        
        return weatherForecastBuilder
                .setDay(day)
                .setForecastSummary(forecastSummary)
                .setPeakTemperature(peakTemperature)
                .setPOP(POP)
                .setForecastDetails(forecastDetails)
                .setHumidex(humidex)
                .setUVIndex(UVIndex)
                .setIssuedTime(issuedTime)
                .build();
    }
}
