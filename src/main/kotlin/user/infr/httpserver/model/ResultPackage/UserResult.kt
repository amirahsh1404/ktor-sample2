package user.infr.httpserver.model.ResultPackage

import user.domain.entity.User

sealed class UserResult {
    data class Success(val success: UserSuccess) : UserResult()
    data class SuccessQuery(val user: User) : UserResult()
    data class Error(val exception: UserExceptions) : UserResult()
    data class UnknownError(val exception: Exception) : UserResult()
}