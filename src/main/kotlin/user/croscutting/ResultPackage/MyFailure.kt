package user.croscutting.ResultPackage

import io.ktor.http.*

class MyFailure(
    val message: String = "Unknown error occurred",
    val statusCode : HttpStatusCode,
    val cause : String = ""
) {
    constructor(message: String , statusCode: HttpStatusCode) : this(
        message = message, statusCode = statusCode, cause = "")
}