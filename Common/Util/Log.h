#ifndef LOG_H_INCLUDED
#define LOG_H_INCLUDED

/** Macro that prints a debug message in the console */
#define LOG_V(x) Log::verbose (__FILE__, __LINE__, x)

/** Macro that prints a debug message in the console */
#define LOG_D(x) Log::debug (__FILE__, __LINE__, x)

/** Macro that prints a warn message in the console */
#define LOG_W(x) Log::warn (__FILE__, __LINE__, x)

/** Macro that prints a error message in the console */
#define LOG_E(x) Log::error (__FILE__, __LINE__, x)


/** Class providing console logging capabilities */
class  Log
{
public:
    
    enum class Level
    {
        None = 0,   /** No logging */
        Error = 1,  /** Used for errors */
        Warn = 2,   /** Used for potential problems */
        Debug = 3,  /** Used to notify major changes changes or actions */
        Verbose = 4 /** Used to notify minor changes and actions */
    };
    
    /** Prints a verbose message in the console */
    static void verbose (const char* filename, int lineNumber, const String& message);
    
    /** Prints a debug message in the console */
    static void debug (const char* filename, int lineNumber, const String& message);
    
    /** Prints a warn message in the console */
    static void warn (const char* filename, int lineNumber, const String& message);
    
    /** Prints a error message in the console */
    static void error (const char* filename, int lineNumber, const String& message);
    
    /** Changes the log level */
    static void setLevel (Log::Level level);
    
    /** Returns the log level */
    static Level getLevel();

private:
    static void log (Log::Level level, const char* filename, int lineNumber, const String& message);
    static Level level;
    static const char* levelStrings[];
    static const char* logLevelExtendedStrings[];
};

#endif  // LOG_H_INCLUDED
