package pandaApp.utils;

public final class AppConstants {

    private AppConstants() {
    }

    public static final String PERSISTENCE_UNIT = "pandaPU";

    public static final String STATUS = "status";
    public static final String USERNAME = "username";
    public static final String ID = "id";
    public static final String ROLE = "role";
    public static final String ADMIN = "Admin";
    public static final String USER = "User";
    public static final String PACKAGE_ID = "packageId";
    public static final String DATE_FORMATTER_PATTERN = "dd/MM/YYYY";
    public static final String NO_DATE = "N/A";
    public static final String DELIVERED_PACKAGE_DATE = "Delivered";

    //User register bean messages
    public static final String PASSWORDS_DOES_NOT_MATCH_FACES_MSG = "Passwords doesn't match! Please try again.";
    public static final String INVALID_USER_SERVICE_MODEL_FACES_MSG = "Incorrect data! Please try again.";

    //User login bean messages
    public static final String AUTHENTICATION_FAILED_FACES_MSG = "Authentication Failed. Invalid credentials.";

    //Create package bean messages
    public static final String INVALID_PACKAGE_SERVICE_MODEL_FACES_MSG = "Incorrect package data! Please try again.";
}
