public class METAR {
    private String rawText;
    
    public METAR(String rawText) {
        this.rawText = rawText;
    }
    
    public String getRawText() {
        return rawText;
    }
    
    @Override
    public String toString() {
        return rawText + "\n";
    }
}
