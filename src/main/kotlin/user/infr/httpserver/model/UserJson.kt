package user.infr.httpserver.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import user.domain.aggregate.user.entity.User

@Serializable
class UserJson (
    val username: String,
    val password: String,
    val fullName: String,
    val email : String,

){
    companion object {
        fun createJson(user: User): String {
            val userJson = UserJson(
                user.username.value,
                user.password.value,
                user.fullName.value,
                user.email.value
            )
            val json = Json { prettyPrint = true }
            return json.encodeToString(userJson)
        }
    }
}