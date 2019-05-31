package main.java.com.pixolestudios.plogger;

public enum PLoggingLevel {
    ALL,
    DEBUG,
    INFO,
    WARNING,
    ERROR,
    NOLOGS;

    @Override
    public String toString() {
        if (!equals(ALL)){
            return super.toString() + " : ";
        } else {
            return "";
        }
    }
}
