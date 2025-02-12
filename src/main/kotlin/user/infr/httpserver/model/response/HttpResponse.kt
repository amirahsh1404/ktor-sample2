package user.infr.httpserver.model.response

import kotlinx.serialization.Serializable

@Serializable
data class HttpResponse(
    var message: String,
    var invalidField: String?
) {
}