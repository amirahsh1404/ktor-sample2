package user.croscutting.ResultPackage.unused

import io.ktor.http.*

enum class UserFailureType(val message: String, val statusCode: HttpStatusCode) {
    USER_ALREADY_EXISTS("user with username '{username}' already exists", HttpStatusCode.Conflict),
    EMAIL_ALREADY_EXISTS("User with email '{email}' already exists", HttpStatusCode.Conflict),
    USER_DOES_NOT_EXIST("user with username '{username}' does not exist", HttpStatusCode.NotFound),
    PASSWORD_IS_NOT_CORRECT("password is wrong", HttpStatusCode.NotAcceptable),
    ;


    fun formatMessage(vararg args: Pair<String, String>): String {
        var dynamicMessage = message

        args.forEach { (key, value) ->
            dynamicMessage = message.replace("{$key}", value)
        }

        return dynamicMessage
    }


}