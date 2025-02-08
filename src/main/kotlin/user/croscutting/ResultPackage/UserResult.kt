package user.croscutting.ResultPackage

import io.ktor.http.*
import user.croscutting.ResultPackage.unused.UserFailures
import user.croscutting.ResultPackage.unused.ValidationExceptions

//sealed class UserResult<T> {
//    data class Success(val success: UserSuccess) : UserResult()
//    data class SuccessQuery(val user: User) : UserResult()
//    data class Error(val exception: Exception) : UserResult()
//    data class UnknownError(val exception: Exception) : UserResult()
//}

sealed class UserResult<T, E> {
    data class success<T, E>(val value: T) : UserResult<T, E>()
    data class failure<T, E : ResultFailure>(val failure: E) : UserResult<T, E>()



    fun getStatusCode(exception: Exception): HttpStatusCode {

        return when (exception) {

            is UserFailures -> {
                exception.statusCode
            }

            is ValidationExceptions -> {
                exception.statusCode
            }

            else -> {
                HttpStatusCode.NotImplemented
            }

        }
    }

    fun getMessage(exception: Exception): String? {
        return when (exception) {
            is UserFailures -> {
                exception.message
            }

            is ValidationExceptions -> {
                exception.message
            }

            else -> {
                "Unknown error"
            }

        }

    }
}

