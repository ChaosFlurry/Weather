import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class METARRetriever {
    public METAR getMETAR(URL weatherFeed) {
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
        String METARRawData = weatherForecastRawDataBuilder.toString();
    
        Pattern METARPattern = Pattern.compile("<raw_text>(.+)</raw_text>");
        Matcher METARMatcher = METARPattern.matcher(METARRawData);
        
        if (METARMatcher.find()) {
            return new METAR(METARMatcher.group(1));
        } else {
            return null;
        }
    }
}
