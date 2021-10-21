package bot.itemku.utility;

public class InputValidator {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InputValidator.class);
    public static boolean isInputNumeric(String input) {
        boolean status = false;
        try {
            Long.parseLong(input);
            status = true;
        } catch (Exception e) {
            logger.info("Error Input -> " +e.getMessage());
        }
        return status;
    }
}
