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
        PLog.writeLogsToFile(true);
        PLog.warning("this is a warning");
        PLog.error("this is an error");
    }
}
