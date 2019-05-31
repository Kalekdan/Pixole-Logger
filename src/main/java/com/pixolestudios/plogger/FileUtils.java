package main.java.com.pixolestudios.plogger;

import java.io.*;

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
        return file.getParentFile().mkdirs();
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
}
