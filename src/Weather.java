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
        WeatherReport weatherReport = weatherReportRetriever.getWeatherReport(city);
        System.out.println(weatherReport);
        
        /*
        city
        <title>(.+) - Weather - Environment Canada<\/title>
        
        warnings
        <title>(.+), (?:.+)<\/title>\n<link type="text\/html" href=\"https:\/\/www.weather.gc.ca\/warnings\/report_e.html(?:\?\w+)?\"\/>
        
        current weather
        <!\[CDATA\[\n<b>Observed at:<\/b> (.+?) (\d+:\d+) (AM|PM) ([A-Z]{3}) (Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday) (\d{2} [A-z]+ \d{4}) <br\/> <b>Condition:<\/b> (.+?) <br\/> <b>Temperature:<\/b> (-?\d+\.\d)&deg;C <br\/> <b>Pressure \/ Tendency:<\/b> (\d+\.\d) kPa (rising|falling)<br\/> <b>Visibility:<\/b> (\d+\.\d) km<br\/> <b>Humidity:<\/b> (\d+) %<br\/> (?:<b>Wind Chill:<\/b> (-?\d+) <br\/> )?<b>Dewpoint:<\/b> (-?\d+\.\d)&deg;C <br\/> <b>Wind:<\/b> ([A-Z]{1,3}) (\d+) km\/h(?: gust (\d+) km\/h)?<br\/> <b>Air Quality Health Index:<\/b> (\d+) <br\/>\n\]\]>
        
        future weather (snapshot)
        <title>\n?(Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)(?: (night))?: (.+)\. (High|Low)(?: (plus|minus))? (\d+).(?: POP (\d+)%)?\n?<\/title>
        
        future weather (detailed: often contains more information than the snapshot (e.g. extended description, humidex, uv))
        <summary type\=\"html\">\n(.+)\. (High|Low) (\d+).(?: Humidex (\d+).)?(?: UV index (\d+) or (low|moderate|high|very high|extreme).)? Forecast issued (\d+:\d+) (AM|PM) ([A-Z]{3}) (Sunday|Monday|Tuesday|Wednesday|Thursday|Friday|Saturday) (\d{2} [A-z]+ \d{4})\n<\/summary>
        */
        
        /*
        	Increasing cloudiness.
        	A few showers beginning near noon.
        	Risk of a thunderstorm in the afternoon.
        	Wind becoming southeast 20 km/h in the afternoon.
        	High 22.
        	Humidex 25.
        	UV index 7 or high.
         */
    }
}
