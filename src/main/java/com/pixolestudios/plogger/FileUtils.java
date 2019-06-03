package main.java.com.pixolestudios.plogger;

import java.io.*;
import java.util.Properties;

public class FileUtils {
    private FileUtils() {
    }

    /**
     * Makes all the required directories up to the path provided
     *
     * @param path to which directories to be created
     * @return true if successfully creates directories
     */
    protected static boolean mkdirs(String path) {
        File file = new File(path);
        if (file.getParentFile() == null){
            return false;
        } else {
            return file.getParentFile().mkdirs();
        }
    }

    /**
     * Writes to the log file at location given
     * If append is false, will start a new log file when application is run
     *
     * @param msg     message to output
     * @param fileLoc path to log file
     * @param append  true if want to append file
     */
    protected static void writeToLogFile(String msg, String fileLoc, boolean append) {
        try (FileWriter fw = new FileWriter(fileLoc, append); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void loadValsFromPropsFile(String fileLoc){
        try (InputStream stream = new FileInputStream(fileLoc)) {
            Properties props = new Properties();
            props.load(stream);
            PLog.setLoggingLevel(props.getProperty("LOGGING_LEVEL"));
            PLog.setLogToStdOut(props.getProperty("WRITE_LOGS_TO_STDOUT"));
            PLog.setLogToFile(props.getProperty("WRITE_LOGS_TO_LOG_FILE"));
            PLog.setIncludeDateStamps(props.getProperty("INCLUDE_DATE_STAMPS"));
            PLog.setIncludeTimeStamps(props.getProperty("INCLUED_TIME_STAMPS"));
            PLog.setLogFileLoc(props.getProperty("LOG_FILE_LOCATION"));
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            File file = new File(fileLoc);
            PLog.warning("Could not find config file at " + file.getAbsolutePath() + " - Using default values instead.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
