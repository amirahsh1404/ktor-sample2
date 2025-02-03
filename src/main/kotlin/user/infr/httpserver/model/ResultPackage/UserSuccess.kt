package user.infr.httpserver.model.ResultPackage

import io.ktor.http.*

class UserSuccess(val userSuccessType: UserSuccessType) {
    val statusCode: HttpStatusCode = userSuccessType.statusCode

}