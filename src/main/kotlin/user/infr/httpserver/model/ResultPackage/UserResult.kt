package user.infr.httpserver.model.ResultPackage

//sealed class UserResult<T> {
//    data class Success(val success: UserSuccess) : UserResult()
//    data class SuccessQuery(val user: User) : UserResult()
//    data class Error(val exception: Exception) : UserResult()
//    data class UnknownError(val exception: Exception) : UserResult()
//}

sealed class UserResult<T> {
    data class Success<T>(val value: T) : UserResult<T>()
    data class Error<T,E:Exception>(val exception: E) : UserResult<T>()
}