# Singleton vs Non-Singleton Pattern Demo

A simple demonstration showing how the Singleton pattern saves memory by ensuring only one instance of a class exists.

## The Problem

When you create multiple instances of a regular class, each instance takes up separate memory space, even if they're doing the same job.

## Example: Logger Class

### ❌ Non-Singleton Approach

```java
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
```

**Usage:**
```java
RegLogger log1 = new RegLogger("app.log");
RegLogger log2 = new RegLogger("app.log");
RegLogger log3 = new RegLogger("app.log");

System.out.println(log1 == log2);  // false - different objects!
```

**Result:** 3 separate objects in memory

```
Memory:
┌──────────────┐
│ Logger #1    │ ← logger1
└──────────────┘
┌──────────────┐
│ Logger #2    │ ← logger2
└──────────────┘
┌──────────────┐
│ Logger #3    │ ← logger3
└──────────────┘
```

---

### ✅ Singleton Approach

```java
public class SingletonLogger {

    private static SingletonLogger loggerInstance;
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
```

**Usage:**
```java
SingletonLogger slog1 = SingletonLogger.getLoggerInstance("app.log");
SingletonLogger slog2 = SingletonLogger.getLoggerInstance("app.log");
SingletonLogger slog3 = SingletonLogger.getLoggerInstance("app.log");

System.out.println(slog1 == slog2);  // true - same object!
```

**Result:** Only 1 object in memory

```
Memory:
┌──────────────┐
│ Logger #1    │ ← logger1, logger2, logger3 (all point here!)
└──────────────┘
```

---

## Key Differences

| Feature | Non-Singleton | Singleton |
|---------|--------------|-----------|
| **Constructor** | `public` | `private` |
| **Creation** | `new ClassName()` | `ClassName.getInstance()` |
| **Objects created** | New object each time | One object shared |
| **Memory usage** | High | Low |
| **Identity test** | `obj1 == obj2` → `false` | `obj1 == obj2` → `true` |

## The Three Rules of Singleton

1. **Private constructor** - Prevents creating instances with `new`
2. **Static instance variable** - Holds the single instance
3. **Public getInstance() method** - Provides access to the instance

```java
public static ClassName getInstance() {
    if (instance == null) {           // ✅ Check if exists
        instance = new ClassName();   // Create only if needed
    }
    return instance;                   // Return the single instance
}
```

## When to Use Singleton

✅ **Use when you need exactly ONE instance:**
- Logger (single log file)
- Database connection pool
- Configuration manager
- Cache

❌ **Don't use when:**
- Each object represents different data (e.g., User, Product)
- You need multiple independent instances

## Memory Comparison

**Creating 5 loggers:**

| Pattern | Objects in Memory | Memory Used |
|---------|------------------|-------------|
| Non-Singleton | 5 objects | 5x |
| Singleton | 1 object | 1x |

**Savings:** 80% less memory with Singleton!

## Running This Demo

1. Clone the repository
2. Compile and run the Java files
3. Observe the memory differences in the output

## Summary

**Singleton = One Instance, Shared Everywhere**

- Saves memory by reusing the same object
- Ensures consistency (all parts of your app use the same instance)
- Perfect for resources that should only exist once