

public class MemoryFootprint {
    public static void main(String[] args){

        System.out.println("PART 1: SIMPLE DEMONSTRATION (3 Loggers)\n");
        System.out.println("___________________________________________\n");
        System.out.println("1. NON-SINGLETON Pattern:\n");

        RegLogger log1 = new RegLogger("app.log");
        RegLogger log2 = new RegLogger("app.log");
        RegLogger log3 = new RegLogger("app.log");

        log1.log("Order placed");
        log2.log("Order shipped");
        log3.log("Order delivered");

        System.out.println("\nIdentity Check:");
        System.out.println("log1 == log2? " + (log1 == log2) + " (DIFFERENT OBJECTS!)");
        System.out.println("3 separate Logger objects created in memory\n\n");

        System.out.println("___________________________________________\n");
        System.out.println("2. SINGLETON Pattern:\n");

        SingletonLogger slog1 = SingletonLogger.getLoggerInstance("app.log");
        SingletonLogger slog2 = SingletonLogger.getLoggerInstance("app.log");
        SingletonLogger slog3 = SingletonLogger.getLoggerInstance("app.log");

        slog1.log("Order placed");
        slog2.log("Order shipped");
        slog3.log("Order delivered");

        System.out.println("\nIdentity Check:");
        System.out.println("slog1 == slog2? " + (slog1 == slog2) + " (SAME OBJECT!)");
        System.out.println("Only 1 Logger object created in memory and shared\n\n\n");

        System.out.println("___________________________________________\n");
        System.out.println("___________________________________________\n");
        System.out.println("PART 2: LARGE-SCALE MEMORY TEST (1000 Loggers)");
        System.out.println("___________________________________________\n");

        Runtime runtime = Runtime.getRuntime();

        System.out.println("___________________________________________\n");
        System.out.println("1. NON-SINGLETON Pattern:\n");

        // Garbage collection
        System.gc();
        try {
            Thread.sleep(100); }
        catch (InterruptedException e) {

        }

        long beforeNonSingleton = runtime.totalMemory() - runtime.freeMemory();

        RegLogger[] regularLoggers = new RegLogger[1000];
        for (int i = 0; i < 1000; i++) {
            regularLoggers[i] = new RegLogger("app.log");
        }

        long afterNonSingleton = runtime.totalMemory() - runtime.freeMemory();
        long nonSingletonMemory = afterNonSingleton - beforeNonSingleton;

        System.out.println("Objects created: 1000");
        System.out.println("Memory used: " + formatBytes(nonSingletonMemory));
        System.out.println("Average per object: " + formatBytes(nonSingletonMemory / 1000));
        System.out.println("First object == Last object? " + (regularLoggers[0] == regularLoggers[999]));

        System.out.println("\n\n___________________________________________\n");
        System.out.println("2. SINGLETON Pattern:\n");

        System.gc();
        try {
            Thread.sleep(100); }
        catch (InterruptedException e) {

        }

        long beforeSingleton = runtime.totalMemory() - runtime.freeMemory();

        SingletonLogger[] singletonLoggers = new SingletonLogger[1000];
        for (int i = 0; i < 1000; i++) {
            singletonLoggers[i] = SingletonLogger.getLoggerInstance("app.log");
        }

        long afterSingleton = runtime.totalMemory() - runtime.freeMemory();
        long singletonMemory = afterSingleton - beforeSingleton;

        System.out.println("Objects created: 1");
        System.out.println("References created: 1000");
        System.out.println("Memory used: " + formatBytes(singletonMemory));
        System.out.println("Average per reference: " + formatBytes(singletonMemory / 1000));
        System.out.println("First reference == Last reference? " + (singletonLoggers[0] == singletonLoggers[999]));



        if (nonSingletonMemory > 0) {
            double percentSaved = ((double)(nonSingletonMemory - singletonMemory) / nonSingletonMemory) * 100;
            System.out.println("\nPercentage saved:               " + String.format("%.1f%%", percentSaved));
        }

    }

    private static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " bytes";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }
}
