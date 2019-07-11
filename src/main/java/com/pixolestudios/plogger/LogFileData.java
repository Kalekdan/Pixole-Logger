package main.java.com.pixolestudios.plogger;

class LogFileData {
    private String logFileName;
    private boolean isFirstLogToFile;

    LogFileData(String name){
        logFileName = name + ".plog";
        isFirstLogToFile = true;
    }

    boolean getIsFirstLogToFile(){
        return isFirstLogToFile;
    }

    void setIsFirstLogToFile(boolean val){
        isFirstLogToFile = val;
    }

    String getLogFileName() {
        return logFileName;
    }
}
