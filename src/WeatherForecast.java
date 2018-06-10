import java.util.List;

public class WeatherForecast {
    private List<String> rawText;
    private List<String> day;
    private List<String> forecastSummary;
    private List<String> peakTemperature;
    private List<String> POP;
    private List<String> forecastDetails;
    private List<String> humidex;
    private List<String> UVIndex;
    private List<String> issuedTime;
    
    private WeatherForecast(WeatherForecastBuilder weatherForecastBuilder) {
        this.rawText = weatherForecastBuilder.rawText;
        this.day = weatherForecastBuilder.day;
        this.forecastSummary = weatherForecastBuilder.forecastSummary;
        this.peakTemperature = weatherForecastBuilder.peakTemperature;
        this.POP = weatherForecastBuilder.POP;
        this.forecastDetails = weatherForecastBuilder.forecastDetails;
        this.humidex = weatherForecastBuilder.humidex;
        this.UVIndex = weatherForecastBuilder.UVIndex;
        this.issuedTime = weatherForecastBuilder.issuedTime;
    }
    
    public List<String> getRawText() {
        return rawText;
    }
    
    public List<String> getDay() {
        return day;
    }
    
    public List<String> getForecastSummary() {
        return forecastSummary;
    }
    
    public List<String> getPeakTemperature() {
        return peakTemperature;
    }
    
    public List<String> getPOP() {
        return POP;
    }
    
    public List<String> getForecastDetails() {
        return forecastDetails;
    }
    
    public List<String> getHumidex() {
        return humidex;
    }
    
    public List<String> getUVIndex() {
        return UVIndex;
    }
    
    public List<String> getIssuedTime() {
        return issuedTime;
    }
    
    @Override
    public String toString() {
        StringBuilder weatherForecastStringBuilder = new StringBuilder();
        
        weatherForecastStringBuilder
                .append("Weather Forecast as of ").append(issuedTime.get(0)).append("\n");
        
        for (int i = 0; i < day.size(); i++) {
            if (!day.get(i).contains("night")) {
                weatherForecastStringBuilder
                        .append(day.get(i)).append(" afternoon: ").append(forecastSummary.get(i)).append("\n");
            } else {
                weatherForecastStringBuilder
                        .append(day.get(i)).append(": ").append(forecastSummary.get(i)).append("\n");
            }
            
            weatherForecastStringBuilder
                    .append(forecastDetails.get(i)).append(".").append("\n");
            
            if (!day.get(i).contains("night")) {
                weatherForecastStringBuilder
                        .append("Temperature: ").append("High of ").append(peakTemperature.get(i)).append(" C");
            } else {
                weatherForecastStringBuilder
                        .append("Temperature: ").append("Low of ").append(peakTemperature.get(i)).append(" C");
            }
            
            if (humidex.get(i) != null) {
                weatherForecastStringBuilder
                        .append(" (Feels like ").append(humidex.get(i)).append(" C)").append("\n");
            } else {
                weatherForecastStringBuilder
                        .append("\n");
            }
            
            if (POP.get(i) != null) {
                weatherForecastStringBuilder
                        .append("POP: ").append(POP.get(i)).append("%").append("\n");
            } else {
                weatherForecastStringBuilder
                        .append("POP: 0%").append("\n");
            }
            
            if (UVIndex.get(i) != null) {
                weatherForecastStringBuilder
                        .append("UV Index: ").append(UVIndex.get(i));
                if (Integer.parseInt(UVIndex.get(i)) < 3) {
                    weatherForecastStringBuilder
                            .append(" (Low risk)").append("\n");
                } else if (Integer.parseInt(UVIndex.get(i)) < 6) {
                    weatherForecastStringBuilder
                            .append(" (Moderate risk)").append("\n");
                } else if (Integer.parseInt(UVIndex.get(i)) < 8) {
                    weatherForecastStringBuilder
                            .append(" (High risk)").append("\n");
                } else if (Integer.parseInt(UVIndex.get(i)) < 11) {
                    weatherForecastStringBuilder
                            .append(" (Very High risk)").append("\n");
                } else {
                    weatherForecastStringBuilder
                            .append(" (Extreme risk)").append("\n");
                }
            }
            
            weatherForecastStringBuilder
                    .append("\n");
        }
        return weatherForecastStringBuilder.toString();
    }
    
    public static class WeatherForecastBuilder {
        private List<String> rawText;
        private List<String> day;
        private List<String> forecastSummary;
        private List<String> peakTemperature;
        private List<String> POP;
        private List<String> forecastDetails;
        private List<String> humidex;
        private List<String> UVIndex;
        private List<String> issuedTime;
        
        public WeatherForecastBuilder setRawText(List<String> rawText) {
            this.rawText = rawText;
            return this;
        }
        
        public WeatherForecastBuilder setDay(List<String> day) {
            this.day = day;
            return this;
        }
        
        public WeatherForecastBuilder setForecastSummary(List<String> forecastSummary) {
            this.forecastSummary = forecastSummary;
            return this;
        }
        
        public WeatherForecastBuilder setPeakTemperature(List<String> peakTemperature) {
            this.peakTemperature = peakTemperature;
            return this;
        }
        
        public WeatherForecastBuilder setPOP(List<String> POP) {
            this.POP = POP;
            return this;
        }
        
        public WeatherForecastBuilder setForecastDetails(List<String> forecastDetails) {
            this.forecastDetails = forecastDetails;
            return this;
        }
        
        public WeatherForecastBuilder setHumidex(List<String> humidex) {
            this.humidex = humidex;
            return this;
        }
        
        public WeatherForecastBuilder setUVIndex(List<String> UVIndex) {
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
}