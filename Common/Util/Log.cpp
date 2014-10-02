#include "../CommonJuceHeader.h"
#include "Log.h"

const char* Log::levelStrings[] = {"", "E", "W", "D", "V" };
const char* Log::logLevelExtendedStrings[] = {"", "Error", "Warning", "Debug", "Verbose" };

#if DEBUG
 Log::Level Log::level = Log::Level::Debug;
#else
 Log::Level Log::level = Log::Level::None;
#endif

void Log::verbose (const char* filename, int lineNumber, const String& message)
{
    log (Log::Level::Verbose, filename, lineNumber, message);
}

void Log::debug (const char* filename, int lineNumber, const String& message)
{
    log (Log::Level::Debug, filename, lineNumber, message);
}

void Log::warn (const char* filename, int lineNumber, const String& message)
{
    log (Log::Level::Warn, filename, lineNumber, message);
}

void Log::error (const char* filename, int lineNumber, const String& message)
{
    log (Log::Level::Error, filename, lineNumber, message);
}

void Log::log (Log::Level level, const char* filename, int lineNumber, const String& message)
{
    if (level <= Log::level)
    {
        String logLine;
#if !ANDROID
        String lev (levelStrings[static_cast<int> (level)]);
        Time time = Time::getCurrentTime();
        
        // The format of the milliseconds must be with 3 digits. For instance: 012
        String millisencondsStr{time.getMilliseconds()};
        int size = millisencondsStr.length();
        for (int i = 0; i < 3 - size; i++){
            millisencondsStr = "0" + millisencondsStr;
        }
        
        String timeStr = time.toString (false, true, true, true) + "." + millisencondsStr ;
        logLine += lev + "/" + timeStr + " - ";
#endif
        String file = juce::File (filename).getFileName();
        String line = String (lineNumber);
        logLine += "<" + file + ":" + line + "> " + message;
        
        juce::Logger::writeToLog (logLine);
    }
}

void Log::setLevel (Log::Level level)
{
    Log::level = level;
    LOG_V ("Logging level set to " + String (logLevelExtendedStrings[static_cast<int> (level)]));
}

Log::Level Log::getLevel()
{
    return level;
}
