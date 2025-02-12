package user.infr.httpserver.libraryFiles

enum class Messages(val meesage : String) {
    INVALID_EMAIL_LENGTH("Email should be at least 5 characters and at most 100 characters"),
    INVALID_EMAIL_FORMAT("Email format is invalid"),
    INVALID_PASSWORD_LENGTH("Password should be at least 7 characters and at most 25 characters"),
    INVALID_PASSWORD_FORMAT("Password format is wrong, your password most contains " +
            "lower and upper case letters, number and special characters"),
    INVALID_FULLNAME_LENGTH("FullName should be at least 5 characters and at most 15 characters"),
    INVALID_FULLNAME_FORMAT("FullName format is wrong"),
    INVALID_USERNAME_LENGTH("Username should be at least 3 characters and at most 15 characters"),
    INVALID_USERNAME_FORMAT("Username contains illegal characters, " +
            "username can only contains words, numbers and _"),
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User already exists"),
    EMAIL_ALREADY_EXISTS("Email already exists"),
    PASSWORD_WRONG("Password does not match"),

    INTERNAL("Internal server error")

}