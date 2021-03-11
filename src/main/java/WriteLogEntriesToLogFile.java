import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class WriteLogEntriesToLogFile {

    private Logger logger;

    public void initializeLogger() throws IOException {
        boolean append = true;
        FileHandler handler = new FileHandler("serverLog.txt", append);

        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.addHandler(handler);
    }

    public void logEventError(String logEvent) throws IOException {
        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.severe(logEvent);
    }
    public void logEventWarning(String logEvent) throws IOException {
        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.warning(logEvent);
    }
    public void logEventInfo(String logEvent) throws IOException {
        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.info(logEvent);
    }
    public void logEventConfig(String logEvent) throws IOException {
        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.config(logEvent);
    }
}
