package main.java.com.pixolestudios.plogger;

/**
 * Temporary file to act as an entry point for logger to allow for self contained testing
 * DOES NOT GET BUILT INTO RELEASE JAR
 */
public class DemoPLogger {
    private DemoPLogger() {
    }

    public static void main(String[] args) {
        PLog.log("Output message goes here", PLoggingLevel.WARNING);
        PLog.debug("this is a debug");
        PLog.info("this is info");
        PLog.warning("this is a warning");
        PLog.error("this is an error");

        PLog.writeLogsToStdOut(true);

        PLog.error("logging to new file", "thisfile");
        PLog.setDefLogFile("testing");
        PLog.error("back to new default default");
        PLog.warning("continuing to log on the new file", "thisfile");
        PLog.debug("another log file, why not", "anotherfile");
        PLog.setDefLogFile("default");
        PLog.warning("this should append the default file");

        PLog.writeLogsToFile(false);
        PLog.error("This should not end up in log files");
        PLog.writeLogsToFile(true);

        PLog.setLogDir("logs/newDir/");
        PLog.info("This should be in the default file in a new dir");
        PLog.setDefLogFile("newDefInDir");
        PLog.info("This should be in a new default file in the new dir");

        PLog.includeDateStamps(false);
        PLog.error("should not have date stamps");
        PLog.includeTimeStamps(false);
        PLog.error("Should not have time stamps either");
        PLog.includeDateStamps(true);
        PLog.includeTimeStamps(true);
        PLog.debug("Should have both stamps");

        PLog.setLoggingLevel(PLoggingLevel.ERROR);
        PLog.info("THIS SHOULD NOT APPEAR ANYWHERE!!");
        PLog.setLoggingLevel(PLoggingLevel.ALL);
        PLog.info("this should be visible again");

        PLog.info("New dir with old file name", "thisfile");
    }
}
