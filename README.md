# Singleton-Demonstration
Demonstration showing the memory difference between singleton and non-singleton patterns in Java

# Memory Footprint: Singleton vs Non-Singleton

---

## The Problem: Multiple Log Files

A developer needs a Logger to write all messages to ONE log file.
But her code creates MULTIPLE Logger objects = MULTIPLE log files!

**Why?** Because she used regular classes instead of Singleton.

---

## Example 1: NON-SINGLETON (The Problem)

```java
class RegularLogger {
    private String logFile;
    
    public RegularLogger(String filename) {
        this.logFile = filename;
    }
    
    public void log(String message) {
        // writes to file
    }
}
```

### Using it:
```java
RegularLogger log1 = new RegularLogger("app.log");
RegularLogger log2 = new RegularLogger("app.log");
RegularLogger log3 = new RegularLogger("app.log");

log1.log("Order placed");
log2.log("Order shipped");
```

### What happens in memory:

```
MEMORY:
┌──────────────────┐
│ RegularLogger #1 │ ← log1
│ logFile="app.log"│
└──────────────────┘

┌──────────────────┐
│ RegularLogger #2 │ ← log2
│ logFile="app.log"│
└──────────────────┘

┌──────────────────┐
│ RegularLogger #3 │ ← log3
│ logFile="app.log"│
└──────────────────┘

Result: 3 objects in memory
Test: log1 == log2 → false (different objects!)
```

### **Problem:**
- 3 separate Logger objects
- All trying to write to the SAME file
- Wastes memory
- Can cause file conflicts

---

## Example 2: SINGLETON (The Solution)

```java
class SingletonLogger {
    private static SingletonLogger instance = null;
    private String logFile;
    
    // Private constructor - can't use "new" from outside!
    private SingletonLogger(String filename) {
        this.logFile = filename;
    }
    
    // Public method to get the instance
    public static SingletonLogger getInstance(String filename) {
        if (instance == null) {
            instance = new SingletonLogger(filename);
        }
        return instance;  // Always returns the SAME object
    }
    
    public void log(String message) {
        // writes to file
    }
}
```

### Using it:
```java
SingletonLogger log1 = SingletonLogger.getInstance("app.log");
SingletonLogger log2 = SingletonLogger.getInstance("app.log");
SingletonLogger log3 = SingletonLogger.getInstance("app.log");

log1.log("Order placed");
log2.log("Order shipped");
```

### What happens in memory:

```
MEMORY:
┌──────────────────┐
│ SingletonLogger  │ ← log1, log2, log3 ALL point here!
│ logFile="app.log"│
└──────────────────┘

Result: 1 object in memory
Test: log1 == log2 → true (SAME object!)
```

### **Solution:**
- Only 1 Logger object
- All variables share it
- Saves memory
- One log file

---

## Key Differences

| Feature | Non-Singleton | Singleton |
|---------|--------------|-----------|
| **Constructor** | `public` | `private` |
| **How to create** | `new RegularLogger()` | `SingletonLogger.getInstance()` |
| **Each call creates** | New object | Same object |
| **Memory usage** | High (1 object per call) | Low (1 object total) |
| **Identity test** | `obj1 == obj2` → false | `obj1 == obj2` → true |

---

## Simple Test

```java
// Non-Singleton test
RegularLogger r1 = new RegularLogger("app.log");
RegularLogger r2 = new RegularLogger("app.log");
System.out.println(r1 == r2);  // false (different objects)

// Singleton test
SingletonLogger s1 = SingletonLogger.getInstance("app.log");
SingletonLogger s2 = SingletonLogger.getInstance("app.log");
System.out.println(s1 == s2);  // true (same object!)
```

---

## Visual Comparison

### Creating 3 Loggers:

**Non-Singleton:**
```
new RegularLogger()  →  Creates Object #1 in memory
new RegularLogger()  →  Creates Object #2 in memory
new RegularLogger()  →  Creates Object #3 in memory

Total memory: 3 objects
```

**Singleton:**
```
getInstance()  →  Creates Object #1 (first time only)
getInstance()  →  Returns Object #1 (same one!)
getInstance()  →  Returns Object #1 (same one!)

Total memory: 1 object
```

---

## When to Use Singleton?

✅ Use Singleton when you need EXACTLY ONE instance:
- Logger (one log file)
- Database connection
- Configuration settings
- Print spooler

❌ Don't use Singleton when:
- You need different objects (e.g., User objects)
- Each object represents different data

---

## Common Mistakes

### Mistake 1: Forgetting private constructor
```java
// WRONG - constructor is public!
class BadSingleton {
    private static BadSingleton instance;
    
    public BadSingleton() { }  // ❌ Anyone can call "new"!
}
```

### Mistake 2: Not checking if instance exists
```java
// WRONG - always creates new object!
public static BadSingleton getInstance() {
    instance = new BadSingleton();  // ❌ Overwrites every time!
    return instance;
}
```

### Correct Way:
```java
public static Singleton getInstance() {
    if (instance == null) {           // ✅ Check first!
        instance = new Singleton();
    }
    return instance;                   // ✅ Return existing one
}
```

---

## Summary

**Singleton Pattern = One Instance Only**

1. Make constructor `private`
2. Create `static` instance variable
3. Create `public static getInstance()` method
4. Check if instance exists before creating

**Why?** Saves memory + ensures only one object exists!
