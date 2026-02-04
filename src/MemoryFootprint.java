import java.sql.SQLOutput;

public class MemoryFootprint {
    public static void main(String[] args){

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
        System.out.println("Only 1 Logger object created in memory and shared\n");
    }
}
