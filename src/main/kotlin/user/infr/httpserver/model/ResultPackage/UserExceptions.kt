package user.infr.httpserver.model.ResultPackage

import io.ktor.http.*

class UserExceptions(
    val userExceptionType: UserExceptionType,

    vararg args: Pair<String, String>
) :
    Exception(userExceptionType.formatMessage(*args)) {
    val statusCode: HttpStatusCode = userExceptionType.statusCode
}