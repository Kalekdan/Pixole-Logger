package main.java.com.pixolestudios.plogger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class PLog {

    //DEFAULT VALUES
    //Will be overidden by propsfile if provided
    private static PLoggingLevel logLvl = PLoggingLevel.DEBUG;
    private static boolean logToStdOut = true;
    private static boolean logToFile = false;
    private static boolean includeDateStamps = true;
    private static boolean includeTimeStamps = true;
    private static String globalLogDir = "logs/";
    private static String defLogFileName = "default";

    private static String propsFile = "setup/plog_config.properties";

    //TRACKING VARIABLES
    private static boolean isFirstLogToStdOut = true;
    private static boolean isValuesLoaded = false;

    private static HashMap<String, LogFileData> logFileMap = new HashMap<String, LogFileData>();

    private PLog() {
    }

    /**
     * Write a message to log
     *
     * @param input       the message to log
     * @param level       the logging level of the message
     * @param logFileName name of the log file to write to
     */
    public static void log(String input, PLoggingLevel level, String logFileName) {
        if (!logFileMap.containsKey(logFileName)) {
            logFileMap.put(logFileName, new LogFileData(logFileName));
        }
        if (!isValuesLoaded) {
            loadValsFromFile();
        }
        if (level.isHigherOrEqualLevel(logLvl)) {
            output(composeMsg(input, level), logFileName);
        }
    }

    public static void log(String input, PLoggingLevel level) {
        log(input, level, defLogFileName);
    }

    private static void output(String outMsg, String logFileName) {
        if (logToStdOut) {
            if (isFirstLogToStdOut) {
                System.out.println(composeMsg("PLogger Started in StdOut\n", PLoggingLevel.ALL));
                isFirstLogToStdOut = false;
                listLoggingValuesToStdOut();
            }
            System.out.println(outMsg);
        }
        if (logToFile) {
            if (logFileMap.get(logFileName).getIsFirstLogToFile()) {
                outputToFile(composeMsg("Plogger Started in logFile\n", PLoggingLevel.ALL), false, logFileName);
                logFileMap.get(logFileName).setIsFirstLogToFile(false);
                listLoggingValuesToFile(logFileName);
            }
            outputToFile(outMsg, true, logFileName);
        }
    }

    private static void loadValsFromFile() {
        isValuesLoaded = true;
        FileUtils.loadValsFromPropsFile(propsFile);
    }

    private static void outputToFile(String outMsg, boolean appendFile, String name) {
        FileUtils.mkdirs(globalLogDir + name);
        FileUtils.writeToLogFile(outMsg, globalLogDir + name, appendFile);
    }

    private static String composeMsg(String msg, PLoggingLevel level) {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(level);
        if (includeDateStamps) {
            toReturn.append(LocalDate.now()).append(" : ");
        }
        if (includeTimeStamps) {
            toReturn.append(LocalTime.now()).append(" : ");
        }
        toReturn.append(msg);

        return toReturn.toString();
    }

    /**
     * Logs a debug message
     *
     * @param input message to log
     */
    public static void debug(String input) {
        debug(input, defLogFileName);
    }

    /**
     * Logs an info message
     *
     * @param input message to log
     */
    public static void info(String input) {
        info(input, defLogFileName);
    }

    /**
     * Logs a warning message
     *
     * @param input message to log
     */
    public static void warning(String input) {
        warning(input, defLogFileName);
    }

    /**
     * Logs an error message
     *
     * @param input message to log
     */
    public static void error(String input) {
        error(input, defLogFileName);
    }

    /**
     * Logs a debug message
     *
     * @param input   message to log
     * @param logName name of the log file to write to
     */
    public static void debug(String input, String logName) {
        log(input, PLoggingLevel.DEBUG, logName);
    }

    /**
     * Logs an info message
     *
     * @param input   message to log
     * @param logName name of the log file to write to
     */
    public static void info(String input, String logName) {
        log(input, PLoggingLevel.INFO, logName);
    }

    /**
     * Logs a warning message
     *
     * @param input   message to log
     * @param logName name of the log file to write to
     */
    public static void warning(String input, String logName) {
        log(input, PLoggingLevel.WARNING, logName);
    }

    /**
     * Logs an error message
     *
     * @param input   message to log
     * @param logName name of the log file to write to
     */
    public static void error(String input, String logName) {
        log(input, PLoggingLevel.ERROR, logName);
    }

    /**
     * If true, logger will now write logs to stdout
     *
     * @param doLogToStdOut if true will write to std out
     */
    public static void writeLogsToStdOut(boolean doLogToStdOut) {
        logToStdOut = doLogToStdOut;
    }

    /**
     * If true, logger will now write logs to file as given by logFileLoc
     * logFileLoc is set to default (as per config.ini) unless changed by setDefLogFile("logfilename")
     *
     * @param doLogToFile if true will write logs to file
     */
    public static void writeLogsToFile(boolean doLogToFile) {
        logToFile = doLogToFile;
    }

    /**
     * If true, logger will include date stamps before each logging message
     *
     * @param doIncludeDateStamps if true will include date stamps in logs
     */
    public static void includeDateStamps(boolean doIncludeDateStamps) {
        includeDateStamps = doIncludeDateStamps;
    }

    /**
     * If true, logger will include time stamps before each logging message
     *
     * @param doIncludeTimeStamps if true will include time stamps in logs
     */
    public static void includeTimeStamps(boolean doIncludeTimeStamps) {
        includeTimeStamps = doIncludeTimeStamps;
    }

    /**
     * Sets the name of the default log file to write to
     *
     * @param logName path to log to e.g. default
     */
    public static void setDefLogFile(String logName) {
        if (!logName.equals(defLogFileName)) {
            if (!logFileMap.containsKey(logName)) {
                logFileMap.put(logName, new LogFileData(logName));
                logFileMap.get(logName).setIsFirstLogToFile(true);
            }
            defLogFileName = logName;
        }
    }

    protected static void listLoggingValuesToStdOut() {
        System.out.println(composeMsg("Logging level = " + logLvl.name(), PLoggingLevel.DEBUG));
        System.out.println(composeMsg("Log to StdOut = " + String.valueOf(logToStdOut), PLoggingLevel.DEBUG));
        System.out.println(composeMsg("Log to file = " + String.valueOf(logToFile), PLoggingLevel.DEBUG));
        System.out.println(composeMsg("Include date stamps = " + String.valueOf(includeDateStamps), PLoggingLevel.DEBUG));
        System.out.println(composeMsg("Include time stamps = " + String.valueOf(includeTimeStamps), PLoggingLevel.DEBUG));
        System.out.println(composeMsg("Default log file location = " + globalLogDir + logFileMap.get(defLogFileName).getLogFileName() + "\n", PLoggingLevel.DEBUG));
    }

    protected static void listLoggingValuesToFile(String logName) {
        outputToFile("Logging level = " + logLvl.name(), true, logName);
        outputToFile("Log to StdOut = " + String.valueOf(logToStdOut), true, logName);
        outputToFile("Log to file = " + String.valueOf(logToFile), true, logName);
        outputToFile("Include date stamps = " + String.valueOf(includeDateStamps), true, logName);
        outputToFile("Include time stamps = " + String.valueOf(includeTimeStamps), true, logName);
        outputToFile("Log file location = " + globalLogDir + logFileMap.get(logName).getLogFileName() + "\n", true, logName);
    }

    public static void setLoggingLevel(PLoggingLevel loggingLevel) {
        logLvl = loggingLevel;
    }

    protected static void setLoggingLevel(String newVal) {
        logLvl = PLoggingLevel.valueOf(newVal);
    }

    protected static void setLogToStdOut(String newVal) {
        logToStdOut = Boolean.parseBoolean(newVal);
    }

    protected static void setLogToFile(String newVal) {
        logToFile = Boolean.parseBoolean(newVal);
    }

    protected static void setIncludeDateStamps(String newVal) {
        includeDateStamps = Boolean.parseBoolean(newVal);
    }

    protected static void setIncludeTimeStamps(String newVal) {
        includeTimeStamps = Boolean.parseBoolean(newVal);
    }

    /**
     * Sets the directory the log files will be written in
     *
     * @param log_dir the directory in the format "logs/"
     */
    public static void setLogDir(String log_dir) {
        globalLogDir = log_dir;
        for (Map.Entry<String, LogFileData> logFileEntry : logFileMap.entrySet()) {
            logFileEntry.getValue().setIsFirstLogToFile(true);
        }
    }
}
