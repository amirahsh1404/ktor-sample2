package user.croscutting.ResultPackage.unused

import io.ktor.http.*

enum class ValidationExceptionsType(val message: String, val statusCode: HttpStatusCode) {
    USERNAME_IS_SHORT("Username should be at least 3 characters", HttpStatusCode.BadRequest),
    USERNAME_IS_LONG("Username should be at most 15 characters", HttpStatusCode.BadRequest),
    USERNAME_FORMAT_WRONG("Username contains illegal characters, " +
            "username can only contains words, numbers and _", HttpStatusCode.NotAcceptable),
    PASSWORD_IS_SHORT("Password should be at least 7 characters", HttpStatusCode.BadRequest),
    PASSWORD_IS_LONG("Password should be at most 20 characters", HttpStatusCode.BadRequest),
    PASSWORD_FORMAT_WRONG("Password format is wrong, your password most contains" +
            "lower and upper case letters, number and special characters" , HttpStatusCode.NotAcceptable),
    FULLNAME_IS_SHORT("Fullname should be at least 5 characters" , HttpStatusCode.BadRequest),
    FULLNAME_IS_LONG("Fullname should be at most 15 characters" , HttpStatusCode.BadRequest),
    FULLNAME_FORMAT_WRONG("Fullname format is wrong" ,  HttpStatusCode.NotAcceptable),
    EMAIL_IS_SHORT("Email should be at least 5 characters", HttpStatusCode.BadRequest),
    EMAIL_FORMAT_WRONG("Email format is wrong" ,  HttpStatusCode.NotAcceptable),
}