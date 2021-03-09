import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class WriteLogEntriesToLogFile {

    private Logger logger;

    public static void main(String[] args) throws Exception {
    }

    public void logEventError(String logEvent) throws IOException {
        boolean append = true;
        FileHandler handler = new FileHandler("serverLog.txt", append);

        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.addHandler(handler);
        logger.severe(logEvent);
    }
    public void logEventWarning(String logEvent) throws IOException {
        boolean append = true;
        FileHandler handler = new FileHandler("serverLog.txt", append);

        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.addHandler(handler);
        logger.warning(logEvent);
    }
    public void logEventInfo(String logEvent) throws IOException {
        boolean append = true;
        FileHandler handler = new FileHandler("serverLog.txt", append);

        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.addHandler(handler);
        logger.info(logEvent);
    }
    public void logEventConfig(String logEvent) throws IOException {
        boolean append = true;
        FileHandler handler = new FileHandler("serverLog.txt", append);

        Logger logger = Logger.getLogger("com.javacodegeeks.snippets.core");
        logger.addHandler(handler);
        logger.config(logEvent);
    }
}
