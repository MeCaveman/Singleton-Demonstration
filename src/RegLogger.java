public class RegLogger {

    private String logFile;
    private String timestamp;

    public RegLogger(String filename) {
        this.logFile = filename;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public void log(String message) {
        System.out.println("  Logging to " + logFile + ": " + message);
    }
}
