public class SingletonLogger {

    private static SingletonLogger loggerInstance = null;
    private String logFile;
    private String timestamp;

    // Private constructor prevents external instantiation
    private SingletonLogger(String filename) {
        this.logFile = filename;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public static SingletonLogger getLoggerInstance(String filename) {
        if (loggerInstance == null) {
            loggerInstance = new SingletonLogger(filename);
        }
        return loggerInstance;
    }

    public void log(String message) {
        System.out.println("  Logging to " + logFile + ": " + message);
    }
}
