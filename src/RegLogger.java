public class RegLogger {

    private String logFile;
    public RegLogger(String filename) {
        this.logFile = filename;
        System.out.println("  [Created new RegularLogger for: " + filename + "]");
    }

    public void log(String message) {
        System.out.println("  Logging to " + logFile + ": " + message);
    }
}
