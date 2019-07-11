package main.java.com.pixolestudios.plogger;

public enum PLoggingLevel {
    ALL(0),
    DEBUG(1),
    INFO(2),
    WARNING(3),
    ERROR(4),
    NOLOGS(5);

    private final int levelCode;

    PLoggingLevel(int levelCode){
        this.levelCode = levelCode;
    }

    /**
     * Is the logging level higher or equal to the one passed
     * @param level logging level to compare to
     * @return true if level is higher or equal to level passed
     */
    boolean isHigherOrEqualLevel(PLoggingLevel level){
        return (levelCode >= level.levelCode);
    }

    @Override
    public String toString() {
        if (!equals(ALL)) {
            return super.toString() + " : ";
        } else {
            return "";
        }
    }
}
