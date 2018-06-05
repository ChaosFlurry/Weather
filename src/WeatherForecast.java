import java.util.List;

public class WeatherForecast {
    private List<String> day;
    private List<String> conditionSummary;
    private List<Integer> peakTemperature;
    private List<Integer> POP;
    private List<String> conditionDetails;
    private List<Integer> humidex;
    private List<Integer> UVIndex;
    private List<String> issuedTime;
    
    public static class WeatherForecastBuilder {
        private List<String> day;
        private List<String> conditionSummary;
        private List<Integer> peakTemperature;
        private List<Integer> POP;
        private List<String> conditionDetails;
        private List<Integer> humidex;
        private List<Integer> UVIndex;
        private List<String> issuedTime;
        
        public WeatherForecastBuilder setDay(List<String> day) {
            this.day = day;
            return this;
        }
        
        public WeatherForecastBuilder setConditionSummary(List<String> conditionSummary) {
            this.conditionSummary = conditionSummary;
            return this;
        }
        
        public WeatherForecastBuilder setPeakTemperature(List<Integer> peakTemperature) {
            this.peakTemperature = peakTemperature;
            return this;
        }
        
        public WeatherForecastBuilder setPOP(List<Integer> POP) {
            this.POP = POP;
            return this;
        }
        
        public WeatherForecastBuilder setCondtionDetails(List<String> conditionDetails) {
            this.conditionDetails = conditionDetails;
            return this;
        }
        
        public WeatherForecastBuilder setHumidex(List<Integer> humidex) {
            this.humidex = humidex;
            return this;
        }
        
        public WeatherForecastBuilder setUVIndex(List<Integer> UVIndex) {
            this.UVIndex = UVIndex;
            return this;
        }
        
        public WeatherForecastBuilder setIssuedTime(List<String> issuedTime) {
            this.issuedTime = issuedTime;
            return this;
        }
        
        public WeatherForecast build() {
            return new WeatherForecast(this);
        }
    }
    
    private WeatherForecast(WeatherForecastBuilder weatherForecastBuilder) {
        this.day = weatherForecastBuilder.day;
        this.conditionSummary = weatherForecastBuilder.conditionSummary;
        this.peakTemperature = weatherForecastBuilder.peakTemperature;
        this.POP = weatherForecastBuilder.POP;
        this.conditionDetails = weatherForecastBuilder.conditionDetails;
        this.humidex = weatherForecastBuilder.humidex;
        this.UVIndex = weatherForecastBuilder.UVIndex;
        this.issuedTime = weatherForecastBuilder.issuedTime;
    }
    
    public List<String> getDay() {
        return day;
    }
    
    public List<String> getConditionSummary() {
        return conditionSummary;
    }
    
    public List<Integer> getPeakTemperature() {
        return peakTemperature;
    }
    
    public List<Integer> getPOP() {
        return POP;
    }
    
    public List<String> getConditionDetails() {
        return conditionDetails;
    }
    
    public List<Integer> getHumidex() {
        return humidex;
    }
    
    public List<Integer> getUVIndex() {
        return UVIndex;
    }
    
    public List<String> getIssuedTime() {
        return issuedTime;
    }
}