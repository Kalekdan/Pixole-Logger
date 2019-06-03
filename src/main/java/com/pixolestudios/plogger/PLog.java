package main.java.com.pixolestudios.plogger;

import java.time.LocalDate;
import java.time.LocalTime;

public class PLog {

    //DEFAULT VALUES
    //Will be overidden by config.ini if provided
    private static PLoggingLevel logLvl = PLoggingLevel.DEBUG;
    private static boolean logToStdOut = true;
    private static boolean logToFile = false;
    private static boolean includeDateStamps = true;
    private static boolean includeTimeStamps = true;
    private static String logFileLoc = "logs/default.plog";

    //TRACKING VARIABLES
    private static boolean isFirstLogToFile = true;
    private static boolean isFirstLogToStdOut = true;

    private PLog() {
    }

    /**
     * Write a message to log
     *
     * @param input the message to log
     * @param level the logging level of the message
     */
    public static void log(String input, PLoggingLevel level) {
        if (level.isHigherOrEqualLevel(logLvl)) {
            output(composeMsg(input, level));
        }
    }

    private static void output(String outMsg) {
        if (logToStdOut) {
            if (isFirstLogToStdOut) {
                System.out.println(composeMsg("PLogger Started in StdOut\n", PLoggingLevel.ALL));
                isFirstLogToStdOut = false;
            }
            System.out.println(outMsg);
        }
        if (logToFile) {
            if (isFirstLogToFile) {
                outputToFile(composeMsg("Plogger Started in logFile\n", PLoggingLevel.ALL), false);
                isFirstLogToFile = false;
            }
            outputToFile(outMsg, true);
        }
    }

    private static void outputToFile(String outMsg, boolean appendFile) {
        FileUtils.mkdirs(logFileLoc);
        FileUtils.writeToLogFile(outMsg, logFileLoc, appendFile);
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
        log(input, PLoggingLevel.DEBUG);
    }

    /**
     * Logs an info message
     *
     * @param input message to log
     */
    public static void info(String input) {
        log(input, PLoggingLevel.INFO);
    }

    /**
     * Logs a warning message
     *
     * @param input message to log
     */
    public static void warning(String input) {
        log(input, PLoggingLevel.WARNING);
    }

    /**
     * Logs an error message
     *
     * @param input message to log
     */
    public static void error(String input) {
        log(input, PLoggingLevel.ERROR);
    }

    /**
     * If true, logger will now write logs to stdout
     * @param doLogToStdOut if true will write to std out
     */
    public static void writeLogsToStdOut(boolean doLogToStdOut){
        logToStdOut = doLogToStdOut;
    }

    /**
     * If true, logger will now write logs to file as given by logFileLoc
     * logFileLoc is set do default (as per config.ini) unless changed by setLogFileLoc("path/logfile.plog")
     * @param doLogToFile if true will write logs to file
     */
    public static void writeLogsToFile(boolean doLogToFile){
        logToFile = doLogToFile;
    }

    /**
     * Sets the location of the log file to write to
     * @param pathToLogFile path to log to e.g. path/logfile.plog
     */
    public static void setLogFileLoc(String pathToLogFile){
        if (!pathToLogFile.equals(logFileLoc)){
            isFirstLogToFile = true;
            logFileLoc = pathToLogFile;
        }
    }
}
