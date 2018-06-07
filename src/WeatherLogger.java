import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherLogger {
    private Path weatherLogFile;
    
    public WeatherLogger(Path weatherLogFile) {
        this.weatherLogFile = weatherLogFile;
        System.out.println(weatherLogFile);
    }
    
    public void log(WeatherReport weatherReport) throws IOException {
        if (!directoryExists(weatherLogFile.getParent())) {
            createDirectory(weatherLogFile.getParent());
        }
        
        if (!fileExists(weatherLogFile)) {
            createFile(weatherLogFile);
        }
        
        List<String> lines = new ArrayList<>(Arrays.asList(weatherReport.toString().split("\n")));
        lines.add("\n");
        Files.write(weatherLogFile, lines, StandardOpenOption.APPEND);
    }
    
    private boolean directoryExists(Path directoryPath) {
        return Files.exists(weatherLogFile.getParent());
    }
    
    private boolean fileExists(Path filePath) {
        return Files.exists(filePath);
    }
    
    private void createDirectory(Path directoryPath) throws IOException {
        Files.createDirectories(directoryPath);
    }
    
    private void createFile(Path filePath) throws IOException {
        Files.createFile(filePath);
    }
}
