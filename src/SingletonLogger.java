public class SingletonLogger {

    private String logFile;
    private static SingletonLogger loggerInstance;

    private SingletonLogger(String filename) {
        this.logFile = filename;
        System.out.println("  [Created SingletonLogger for: " + filename + "]");
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
