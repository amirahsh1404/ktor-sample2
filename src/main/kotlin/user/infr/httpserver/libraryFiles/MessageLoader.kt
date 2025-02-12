package user.infr.httpserver.libraryFiles

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import user.croscutting.ResultPackage.MyFailure
import user.infr.httpserver.model.HttpResponse

object MessageLoader {

    suspend fun createMessage(failure: MyFailure): String {
        val httpResponse: HttpResponse = HttpResponse("", "")
        httpResponse.message = when (failure.code) {
            "InvalidParams" -> {
                when(failure.cause!!.code) {
                    "InvalidEmailFormat" -> Messages.INVALID_EMAIL_FORMAT.meesage
                    "InvalidEmailLength" -> Messages.INVALID_EMAIL_LENGTH.meesage

                    "InvalidUsernameLength" -> Messages.INVALID_USERNAME_LENGTH.meesage
                    "InvalidUsernameFormat" -> Messages.INVALID_USERNAME_FORMAT.meesage

                    "InvalidPasswordLength" -> Messages.INVALID_PASSWORD_LENGTH.meesage
                    "InvalidPasswordFormat" -> Messages.INVALID_PASSWORD_FORMAT.meesage

                    "InvalidFullNameLength" -> Messages.INVALID_FULLNAME_LENGTH.meesage
                    "InvalidFullNameFormat" -> Messages.INVALID_FULLNAME_FORMAT.meesage
                    else -> {
                        Messages.INTERNAL.meesage
                    }
                }
            }

            "UserNotFound" -> Messages.USER_NOT_FOUND.meesage
            "EmailExists" -> Messages.EMAIL_ALREADY_EXISTS.meesage
            "UserExists" -> Messages.USER_ALREADY_EXISTS.meesage
            "PasswordWrong" -> Messages.PASSWORD_WRONG.meesage

            else -> {
                Messages.INTERNAL.meesage
            }
        }
        httpResponse.invalidField = failure.cause!!.value


        val json = Json { prettyPrint = true }
        return json.encodeToString(httpResponse)
    }
}