import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@SuppressWarnings("unused")
public class WeatherReportExporter {
    private static String directory = "C:\\Users\\John\\Desktop\\Weather\\Logs\\";
    
    public static String getDirectory() {
        return directory;
    }
    
    public static void setDirectory(String directory) {
        WeatherReportExporter.directory = directory;
    }
    
    public static void exportAll(WeatherReport weatherReport) {
        exportXML(weatherReport);
        exportCSV(weatherReport);
        exportTSV(weatherReport);
    }
    
    public static void exportXML(WeatherReport weatherReport) {
        String path = directory + weatherReport.getCity() + ".xml";
        File file = new File(path);
        if (file.isDirectory()) {
            throw new IllegalStateException("Path cannot be a directory");
        }
        
        if (!file.exists()) {
            createFile(path);
            String header = "<?xml version=\"1.0\"?>\n";
            appendFile(path, header);
        }
        
        String contents = weatherReport.generateXML();
        appendFile(path, contents);
    }
    
    public static void exportCSV(WeatherReport weatherReport) {
        String path = directory + weatherReport.getCity() + ".csv";
        File file = new File(path);
        if (file.isDirectory()) {
            throw new IllegalStateException("Path cannot be a directory");
        }
    
        if (!file.exists()) {
            createFile(path);
            String header = "City,Observed at,Time," +
                    "Condition,Temperature,Pressure,Tendency,Visibility," +
                    "Humidity,Windchill,Dewpoint,Wind,Air Quality Health Index\n";
            appendFile(path, header);
        }
        
        String contents = weatherReport.generateCSV();
        appendFile(path, contents);
    }
    
    public static void exportTSV(WeatherReport weatherReport) {
        String path = directory + weatherReport.getCity() + ".tsv";
        File file = new File(path);
        if (file.isDirectory()) {
            throw new IllegalStateException("Path cannot be a directory");
        }
    
        if (!file.exists()) {
            createFile(path);
            String header = "City\tObserved at\tTime\t" +
                    "Condition\ttemperature\tpressure\ttendency\tvisibility\t" +
                    "Humidity\tWindchill\tDewpoint\tWind\tAir Quality Health Index\n";
            appendFile(path, header);
        }
        
        String contents = weatherReport.generateTSV();
        appendFile(path, contents);
    }
    
    private static void createFile(String path) {
        try {
            Files.createDirectories(Paths.get(path).getParent());
            Files.createFile(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void appendFile(String path, String contents) {
        try {
            Files.write(Paths.get(path), contents.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
