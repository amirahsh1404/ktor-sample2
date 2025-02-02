package user.infr.httpserver.model.ResultPackage

import user.domain.entity.User

sealed class UserResult {
    data class Success(val success: UserSuccess) : UserResult()
    data class SuccessQuery(val user: User) : UserResult()
    data class Error(val exception: Exception) : UserResult()
    data class UnknownError(val exception: Exception) : UserResult()
}
sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Error<T,E:Exception>(val exception: E) : Result<T>()
}