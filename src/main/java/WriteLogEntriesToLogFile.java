import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class WriteLogEntriesToLogFile {

    private Logger logger;

    public static void main(String[] args) throws Exception {
        boolean append = true;
        FileHandler handler = new FileHandler("serverLog.txt", append);

        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.addHandler(handler);
    }

    public void logEventError(String logEvent) {
        logger.severe(logEvent);
    }
    public void logEventWarning(String logEvent) {
        logger.warning(logEvent);
    }
    public void logEventInfo(String logEvent) {
        logger.info(logEvent);
    }
    public void logEventConfig(String logEvent) {
        logger.config(logEvent);
    }
}
