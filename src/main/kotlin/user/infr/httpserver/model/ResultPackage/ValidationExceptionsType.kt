package user.infr.httpserver.model.ResultPackage

enum class ValidationExceptionsType(val message: String) {
    USERNAME_IS_SHORT("Username should be at least 3 characters"),
    USERNAME_IS_LONG("Username should be at most 15 characters"),
    USERNAME_FORMAT_WRONG("Username contains illegal characters, " +
            "username can only contains words, numbers and _"),
    PASSWORD_IS_SHORT("Password should be at least 7 characters"),
    PASSWORD_IS_LONG("Password should be at most 20 characters"),
    PASSWORD_FORMAT_WRONG("Password format is wrong, your password most contains" +
            "lower and upper case letters, number and special characters"),
    FULLNAME_IS_SHORT("Fullname should be at least 5 characters"),
    FULLNAME_IS_LONG("Fullname should be at most 15 characters"),
    FULLNAME_FORMAT_WRONG("Fullname format is wrong"),
    EMAIL_IS_SHORT("Email should be at least 5 characters"),
    EMAIL_FORMAT_WRONG("Email format is wrong"),
}