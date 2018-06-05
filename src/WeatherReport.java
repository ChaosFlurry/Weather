public class WeatherReport {
    private String city;
    private String observedAt;
    private String time;
    private String condition;
    private String temperature;
    private String pressure;
    private String pressureTendency;
    private String visibility;
    private String humidity;
    private String windchill;
    private String dewPoint;
    private String windDirection;
    private String windSpeed;
    private String windGust;
    private String airQualityHealthIndex;
    
    private WeatherReport(WeatherReportBuilder weatherReportBuilder) {
        this.city = weatherReportBuilder.city;
        this.observedAt = weatherReportBuilder.observedAt;
        this.time = weatherReportBuilder.time;
        this.condition = weatherReportBuilder.condition;
        this.temperature = weatherReportBuilder.temperature;
        this.pressure = weatherReportBuilder.pressure;
        this.pressureTendency = weatherReportBuilder.pressureTendency;
        this.visibility = weatherReportBuilder.visibility;
        this.humidity = weatherReportBuilder.humidity;
        this.windchill = weatherReportBuilder.windchill;
        this.dewPoint = weatherReportBuilder.dewPoint;
        this.windDirection = weatherReportBuilder.windDirection;
        this.windSpeed = weatherReportBuilder.windSpeed;
        this.windGust = weatherReportBuilder.windGust;
        this.airQualityHealthIndex = weatherReportBuilder.airQualityHealthIndex;
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
    
    public String getPressureTendency() {
        return pressureTendency;
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
    
    public String getDewPoint() {
        return dewPoint;
    }
    
    public String getWindDirection() {
        return windDirection;
    }
    
    public String getWindSpeed() {
        return windSpeed;
    }
    
    public String getWindGust() {
        return windGust;
    }
    
    public String getAirQualityHealthIndex() {
        return airQualityHealthIndex;
    }
    
    @Override
    public String toString() {
        StringBuilder weatherForecastStringBuilder = new StringBuilder();
        
        weatherForecastStringBuilder
                .append("Weather Conditions for ").append(city).append(" as of ").append(time).append("\n")
                .append("Observed at: ").append(observedAt).append("\n")
                .append("Condition: ").append(condition).append("\n")
                .append("Temperature: ").append(temperature).append(" C");
        
        if (windchill != null) {
            weatherForecastStringBuilder
                    .append(" (Feels like ").append(windchill).append(" C").append("\n");
        } else {
            weatherForecastStringBuilder
                    .append("\n");
        }
        
        weatherForecastStringBuilder
                .append("Pressure: ").append(pressure).append(" kPa").append(" and ").append(pressureTendency).append("\n")
                .append("Visibility: ").append(visibility).append(" km").append("\n")
                .append("Humidity: ").append(humidity).append("%").append("\n")
                .append("Dew Point: ").append(dewPoint).append(" C").append("\n")
                .append("Wind: ").append(windSpeed).append(" km/h");
        
        if (windGust != null) {
            weatherForecastStringBuilder
                    .append(" with gusts up to ").append(windGust).append("km/h").append("\n");
        } else {
            weatherForecastStringBuilder
                    .append("\n");
        }
        
        weatherForecastStringBuilder
                .append("Air Quality Health Index: ").append(airQualityHealthIndex);
        
        if (Integer.parseInt(airQualityHealthIndex) < 4) {
            weatherForecastStringBuilder
                    .append(" (Low Risk)").append("\n");
        } else if (Integer.parseInt(airQualityHealthIndex) < 7) {
            weatherForecastStringBuilder
                    .append(" (Moderate Risk)").append("\n");
        } else if (Integer.parseInt(airQualityHealthIndex) < 11) {
            weatherForecastStringBuilder
                    .append(" (High Risk)").append("\n");
        } else {
            weatherForecastStringBuilder
                    .append(" (Very High Risk)").append("\n");
        }
        
        return weatherForecastStringBuilder.toString();
    }
    
    public static class WeatherReportBuilder {
        private String city;
        private String observedAt;
        private String time;
        private String condition;
        private String temperature;
        private String pressure;
        private String pressureTendency;
        private String visibility;
        private String humidity;
        private String windchill;
        private String dewPoint;
        private String windDirection;
        private String windSpeed;
        private String windGust;
        private String airQualityHealthIndex;
        
        public WeatherReportBuilder setCity(String city) {
            this.city = city;
            return this;
        }
        
        public WeatherReportBuilder setObservedAt(String observedAt) {
            this.observedAt = observedAt;
            return this;
        }
        
        public WeatherReportBuilder setTime(String time) {
            this.time = time;
            return this;
        }
        
        public WeatherReportBuilder setCondition(String condition) {
            this.condition = condition;
            return this;
        }
        
        public WeatherReportBuilder setTemperature(String temperature) {
            this.temperature = temperature;
            return this;
        }
        
        public WeatherReportBuilder setPressure(String pressure) {
            this.pressure = pressure;
            return this;
        }
        
        public WeatherReportBuilder setTendency(String pressureTendency) {
            this.pressureTendency = pressureTendency;
            return this;
        }
        
        public WeatherReportBuilder setVisibility(String visibility) {
            this.visibility = visibility;
            return this;
        }
        
        public WeatherReportBuilder setHumidity(String humidity) {
            this.humidity = humidity;
            return this;
        }
        
        public WeatherReportBuilder setWindchill(String windchill) {
            this.windchill = windchill;
            return this;
        }
        
        public WeatherReportBuilder setDewPoint(String dewPoint) {
            this.dewPoint = dewPoint;
            return this;
        }
        
        public WeatherReportBuilder setWindDirection(String windDirection) {
            this.windDirection = windDirection;
            return this;
        }
        
        public WeatherReportBuilder setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }
        
        public WeatherReportBuilder setWindGust(String windGust) {
            this.windGust = windGust;
            return this;
        }
        
        public WeatherReportBuilder setAirQualityHealthIndex(String airQualityHealthIndex) {
            this.airQualityHealthIndex = airQualityHealthIndex;
            return this;
        }
        
        public WeatherReport build() {
            return new WeatherReport(this);
        }
    }
}
