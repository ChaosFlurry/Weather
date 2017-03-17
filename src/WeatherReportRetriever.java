import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class WeatherReportRetriever {
    public static WeatherReport getCurrentWeatherReport(URL rssFeed) {
        String rawWeatherData = getWeatherInformation(rssFeed);
        Map<String, String> parsedWeatherData = parseWeatherInformation(rawWeatherData);
        return generateWeatherReport(parsedWeatherData);
    }
    
    public static String getWeatherInformation(URL rssFeed) {
        String information = "";
        try {
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(rssFeed.openStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                information += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return information;
    }
    
    public static Map<String, String> parseWeatherInformation(String information) {
        // match <![CDATA[...]]>
        Pattern CDATA = Pattern.compile("<!\\[CDATA\\[(.+)]]>", Pattern.DOTALL);
        Matcher CDATAMatcher = CDATA.matcher(information);
        
        String unparsedInformation;
        if (CDATAMatcher.find()) {
            unparsedInformation = CDATAMatcher.group(1);
        } else {
            throw new IllegalArgumentException("Invalid URL (Temp. exception)");
        }
        unparsedInformation = unparsedInformation.replaceAll(" <br/>", "");
        unparsedInformation = unparsedInformation.replaceAll("<br/>", "");
        unparsedInformation = unparsedInformation.replaceAll("<b>", "");
        unparsedInformation = unparsedInformation.replaceAll("</b>", "");
        unparsedInformation = unparsedInformation.replaceAll("&deg;", "°");
        unparsedInformation = unparsedInformation.replaceAll(" %", "%");
        List<String> data = new ArrayList<>(Arrays.asList(unparsedInformation.split("\n")));
        
        // Get city name
        Pattern city = Pattern.compile("<title>(.+) - Weather - Environment Canada</title>");
        Matcher cityMatcher = city.matcher(information);
        if (cityMatcher.find()) {
            data.add("City: " + cityMatcher.group(1));
        } else {
            throw new IllegalArgumentException("Unable to retrieve city name");
        }
        
        Map<String, String> weather = new HashMap<>();
        
        for (int i = 0; i < data.size(); i++) {
            String key = data.get(i).split(": ")[0];
            String value = data.get(i).split(": ")[1];
            
            if (key.equals("Observed at")) {
                for (int i1 = 0; i1 < value.length(); i1++) {
                    if (Character.isDigit(value.charAt(i1))) {
                        String location = value.substring(0, i1 - 1);
                        String time = value.substring(i1);
                        weather.put("Observed at", location);
                        weather.put("Time", time);
                        break;
                    }
                }
            } else if (key.equals("Pressure / Tendency")) {
                String pressure = value.split(" kPa ")[0] + " kPa";
                String tendency = value.split(" kPa ")[1].substring(0, 1).toUpperCase() +
                        value.split(" kPa ")[1].substring(1);
                weather.put("Tendency", tendency);
                weather.put("Pressure", pressure);
            } else {
                weather.put(key, value);
            }
        }
        // Windchill data may not be available for some cities
        if (!weather.containsKey("Windchill")) {
            weather.put("Windchill", "-");
        }
        return weather;
    }
    
    public static WeatherReport generateWeatherReport(Map<String, String> weatherData) {
        return new
                WeatherReport.WeatherReportBuilder()
                .city(weatherData.get("City"))
                .observedAt(weatherData.get("Observed at"))
                .time(weatherData.get("Time"))
                .condition(weatherData.get("Condition"))
                .temperature(weatherData.get("Temperature"))
                .pressure(weatherData.get("Pressure"))
                .tendency(weatherData.get("Tendency"))
                .visibility(weatherData.get("Visibility"))
                .humidity(weatherData.get("Humidity"))
                .windchill(weatherData.get("Windchill"))
                .dewpoint(weatherData.get("Dewpoint"))
                .wind(weatherData.get("Wind"))
                .airQualityHealthIndex(weatherData.get("Air Quality Health Index"))
                .build();
    }
}
