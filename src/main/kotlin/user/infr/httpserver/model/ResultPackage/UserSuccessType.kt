package user.infr.httpserver.model.ResultPackage

import io.ktor.http.*

enum class UserSuccessType(val message: String, val statusCode: HttpStatusCode) {
    CREATE_SUCCESS("user created successfully", HttpStatusCode.Created),
    DELETE_SUCCESS("user deleted successfully", HttpStatusCode.Accepted),
    LOGIN_SUCCESS("user logged successfully", HttpStatusCode.Accepted),
    CHANGE_INFO_SUCCESS("changed info successfully" , HttpStatusCode.Accepted),
}