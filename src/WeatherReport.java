@SuppressWarnings("unused")
public class WeatherReport {
    private String city;
    private String observedAt;
    private String time;
    private String condition;
    private String temperature;
    private String pressure;
    private String tendency;
    private String visibility;
    private String humidity;
    private String windchill;
    private String dewpoint;
    private String wind;
    private String airQualityHealthIndex;
    
    private WeatherReport(WeatherReportBuilder builder) {
        this.city = builder.city;
        this.observedAt = builder.observedAt;
        this.time = builder.time;
        this.condition = builder.condition;
        this.temperature = builder.temperature;
        this.pressure = builder.pressure;
        this.tendency = builder.tendency;
        this.visibility = builder.visibility;
        this.humidity = builder.humidity;
        this.windchill = builder.windchill;
        this.dewpoint = builder.dewpoint;
        this.wind = builder.wind;
        this.airQualityHealthIndex = builder.airQualityHealthIndex;
    }
    
    public String getCity() {
        return city;
    }
    public String getObservedAt() {
        return observedAt;
    }
    public String getTime() {
        return time;
    }
    public String getCondition() {
        return condition;
    }
    public String getTemperature() {
        return temperature;
    }
    public String getPressure() {
        return pressure;
    }
    public String getTendency() {
        return tendency;
    }
    public String getVisibility() {
        return visibility;
    }
    public String getHumidity() {
        return humidity;
    }
    public String getWindchill() {
        return windchill;
    }
    public String getDewpoint() {
        return dewpoint;
    }
    public String getWind() {
        return wind;
    }
    public String getAirQualityHealthIndex() {
        return airQualityHealthIndex;
    }
    
    @Override
    public String toString() {
        return "Weather forecast for " + city + " as of " + time + "\n" +
                "Observed at: " + observedAt + "\n" +
                "Condition: " + condition + "\n" +
                "Temperature: " + temperature + "\n" +
                "Pressure: " + pressure + "\n" +
                "Tendency: " + tendency + "\n" +
                "Visibility: " + visibility + "\n" +
                "Humidity: " + humidity + "\n" +
                "Windchill: " + windchill + "\n" +
                "Dew Point: " + dewpoint + "\n" +
                "Wind: " + wind + "\n" +
                "Air Quality Health Index: " + airQualityHealthIndex + "\n";
    }
    
    public String generateXML() {
        return
                "<city>" + city + "</city>\n" +
                "<observedAt>" + observedAt + "</observedAt>\n" +
                "<time>" + time + "</time>\n" +
                "<condition>" + condition + "</condition>\n" +
                "<temperature>" + temperature + "</temperature>\n" +
                "<pressure>" + pressure + "</pressure>\n" +
                "<tendency>" + tendency + "</tendency>\n" +
                "<visibility>" + visibility + "</visibility>\n" +
                "<humidity>" + humidity + "</humidity>\n" +
                "<windchill>" + windchill + "</windchill>\n" +
                "<dewpoint>" + dewpoint + "</dewpoint>\n" +
                "<wind>" + wind + "</wind>\n" +
                "<airQualityHealthIndex>" + airQualityHealthIndex + "</airQualityHealthIndex>\n";
    }
    
    public String generateCSV() {
        return
                city + "," +
                observedAt + "," +
                time + "," +
                condition + "," +
                temperature + "," +
                pressure + "," +
                tendency + "," +
                visibility + "," +
                humidity + "," +
                windchill + "," +
                dewpoint + "," +
                wind + "," +
                airQualityHealthIndex + "\n";
    }
    
    public String generateTSV() {
        return
                city + "\t" +
                observedAt + "\t" +
                time + "\t" +
                condition + "\t" +
                temperature + "\t" +
                pressure + "\t" +
                tendency + "\t" +
                visibility + "\t" +
                humidity + "\t" +
                windchill + "\t" +
                dewpoint + "\t" +
                wind + "\t" +
                airQualityHealthIndex + "\n";
    }
    
    public static class WeatherReportBuilder {
        private String city;
        private String observedAt;
        private String time;
        private String condition;
        private String temperature;
        private String pressure;
        private String tendency;
        private String visibility;
        private String humidity;
        private String windchill;
        private String dewpoint;
        private String wind;
        private String airQualityHealthIndex;
        
        public WeatherReportBuilder() {
            this.city = "-";
            this.observedAt = "-";
            this.time = "-";
            this.condition = "-";
            this.temperature = "-";
            this.pressure = "-";
            this.tendency = "-";
            this.visibility = "-";
            this.humidity = "-";
            this.windchill = "-";
            this.dewpoint = "-";
            this.wind = "-";
            this.airQualityHealthIndex = "-";
        }
        
        public WeatherReportBuilder city(String city) {
            this.city = city;
            return this;
        }
    
        public WeatherReportBuilder observedAt(String observedAt) {
            this.observedAt = observedAt;
            return this;
        }
        
        public WeatherReportBuilder time(String time) {
            this.time = time;
            return this;
        }
        
        public WeatherReportBuilder condition(String condition) {
            this.condition = condition;
            return this;
        }
        
        public WeatherReportBuilder temperature(String temperature) {
            this.temperature = temperature;
            return this;
        }
        
        public WeatherReportBuilder pressure(String pressure) {
            this.pressure = pressure;
            return this;
        }
        
        public WeatherReportBuilder tendency(String tendency) {
            this.tendency = tendency;
            return this;
        }
        
        public WeatherReportBuilder visibility(String visibility) {
            this.visibility = visibility;
            return this;
        }
        
        public WeatherReportBuilder humidity(String humidity) {
            this.humidity = humidity;
            return this;
        }
        
        public WeatherReportBuilder windchill(String windchill) {
            this.windchill = windchill;
            return this;
        }
        
        public WeatherReportBuilder dewpoint(String dewpoint) {
            this.dewpoint = dewpoint;
            return this;
        }
        
        public WeatherReportBuilder wind(String wind) {
            this.wind = wind;
            return this;
        }
        
        public WeatherReportBuilder airQualityHealthIndex(String airQualityHealthIndex) {
            this.airQualityHealthIndex = airQualityHealthIndex;
            return this;
        }
        
        public WeatherReport build() {
            return new WeatherReport(this);
        }
    }
}
