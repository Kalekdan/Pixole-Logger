package main.java.com.pixolestudios.plogger;

public class LogFileData {
    private String logFileName;
    private boolean isFirstLogToFile;

    protected LogFileData(String name){
        logFileName = name + ".plog";
        isFirstLogToFile = true;
    }

    protected boolean getIsFirstLogToFile(){
        return isFirstLogToFile;
    }

    protected void setIsFirstLogToFile(boolean val){
        isFirstLogToFile = val;
    }

    protected String getLogFileName() {
        return logFileName;
    }
}
