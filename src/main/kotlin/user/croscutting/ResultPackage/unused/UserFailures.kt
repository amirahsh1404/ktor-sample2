package user.croscutting.ResultPackage.unused

import io.ktor.http.*

open class UserFailures(
    val userFailureType: UserFailureType,

    vararg args: Pair<String, String>
) :
    Exception(userFailureType.formatMessage(*args)) {
    val statusCode: HttpStatusCode = userFailureType.statusCode
}