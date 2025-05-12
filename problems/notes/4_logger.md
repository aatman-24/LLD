#### Design Problem: Logging Framework

---

[Problems](https://github.com/ashishps1/awesome-low-level-design/blob/main/problems/logging-framework.md)

#### Requirements

```tex
- The logging framework should support different log levels, such as DEBUG, INFO, WARNING, ERROR, and FATAL.
- It should allow logging messages with a timestamp, log level, and message content.
- The framework should support multiple output destinations, such as console, file, and database.
- It should provide a configuration mechanism to set the log level and output destination.
- The logging framework should be thread-safe to handle concurrent logging from multiple threads.
- It should be extensible to accommodate new log levels and output destinations in the future.
```

### Solution

<u>Identify Flow</u>

> There is nothing like flow in this problem. But based upon logLevel and logDestination set for logger, it will write logs to destinations based upon that. 

<u>Entity LookUp</u>

```tex
Logger
LogDestination
LogConfig
LogMessage
LogLevel - ENUM
```

<u>Relationship Lookup</u>

```tex
Logger has a LogConfig, which keep track of Logger configuration(logLevel, logDestination)
```

<u>Applying SOLID Principle | Design  Patterns</u>

```tex
Logger => Singleton

Design Pattern Bridge: We have two independent attribute/factor logLevel and logDestination, we cannot create 
subclasses for each combination. 

So separate out the logDestination from main class, and attach to main class 
with "has-a" relationship, so we can change that by runtime. And we keep logLevel
within the Logger class. 

ILogDestination which we renamed to "LogAppender"

LogAppender
    ConsoleAppender
    DatabaseAppender
    FileAppender
```

<u>Class Diagram</u>

```tex
Logger
+ logConfig: LogConfig
+ logAppender: LogAppender
--
- Logger(): void, by default set LogConfig
+ getInstance(): Logger
+ setlogAppender(LogAppender logAppender): void
+ setLogLevel(LogLevel logLevel): void
- log(Loglevel loglevel, String content): void
+ debug(String content): void
+ info(String content): void
+ warn(String content): void
+ error(String content): void
+ fatal(String content): void

LogAppender
+ append(LogMessage logMessage): void

ConsoleAppender
+ append(LogMessage logMessage): void

FileAppender
+ FileAppender(String filePath): void
+ append(LogMessage logMessage): void

DatabaseAppender
+ DatabaseAppender(String jdbcUrl, String username, String password)
+ append(LogMessage logMessage): void

LogConfig
+ logLevel: LogLevel
+ logAppender: LogAppender

LogMessage
+ logLevel: LogLevel
+  content: String

LogLevel
+ DEBUG
+ INFO
+ WARNING
+ ERROR
+ FATAL
```

<u>UML Diagram</u>

![](../../assets/2024-11-04-17-29-25-logger_solution.drawio.png)

<u>[Jump to Code](https://github.com/ashishps1/awesome-low-level-design/tree/main/solutions/java/src/loggingframework)</u>

**What I designed is this ?**

<img src="../../assets/2024-11-04-15-17-11-logger_practice.drawio.png" title="" alt="" width="760">
