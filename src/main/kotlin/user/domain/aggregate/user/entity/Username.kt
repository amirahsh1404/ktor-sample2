package user.domain.aggregate.user.entity

import io.ktor.http.*
import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult

data class Username private constructor(val value: String) {


    init {
        require(value.length in 3..15) {
            "Username should be at least 3 characters and at most 15 characters"
        }

        require(value.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[A-Za-z]+".toRegex())) {
            "Username contains illegal characters, " +
                    "username can only contains words, numbers and _"
        }
    }

    companion object {
        fun makeNew(value: String): UserResult<Username, Failure> {
            return when {
                value.length !in 3..15 ->
                    UserResult.failure(Failure.InvalidUsernameLength())

                !value.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[A-Za-z]+".toRegex()) ->
                    UserResult.failure(Failure.InvalidUsernameFormat())

                else -> UserResult.success(Username(value))
            }
        }

        fun constructFromPersisted(username: String): Username {
            return Username(username)
        }
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class InvalidUsernameLength :
            Failure(
                MyFailure(
                    "Username should be at least 3 characters and at most 15 characters",
                    HttpStatusCode.NotAcceptable
                )
            )

        class InvalidUsernameFormat : Failure(
            MyFailure(
                "Username contains illegal characters, " +
                        "username can only contains words, numbers and _", HttpStatusCode.NotAcceptable
            )
        )
    }


}