package user.infr.httpserver.model.ResultPackage

enum class UserExceptionType(val message: String) {
    USER_ALREADY_EXISTS("user with username '{username}' already exists"),
    EMAIL_ALREADY_EXISTS("User with email '{email}' already exists"),
    USER_DOES_NOT_EXIST("user with username '{username}' does not exist"),
    PASSWORD_IS_NOT_CORRECT("password is wrong"),
    ;




    fun formatMessage(vararg args: Pair<String, String>): String {
        var dynamicMessage = message
        args.forEach { (key, value) ->
            dynamicMessage = message.replace("{$key}", value)
        }
        return message
    }


}