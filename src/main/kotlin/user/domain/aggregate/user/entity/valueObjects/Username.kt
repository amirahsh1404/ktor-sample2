package user.domain.aggregate.user.entity.valueObjects

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult

data class Username private constructor(val value: String) {


    init {
        require(value.length in 3..15) {
            "Username should be at least 3 characters and at most 15 characters"
        }

        require(value.matches("[a-zA-Z0-9-_]+".toRegex())) {
            "Username contains illegal characters, " +
                    "username can only contains words, numbers and _"
        }
    }

    companion object {
        fun makeNew(value: String): UserResult<Username, Failure> {
            return when {
                value.length !in 3..15 ->
                    UserResult.failure(Failure.InvalidUsernameLengthFailure(username = value))

                !value.matches("[a-zA-Z0-9-_]+".toRegex()) ->
                    UserResult.failure(Failure.InvalidUsernameFormatFailure(username = value))

                else -> UserResult.success(Username(value))
            }
        }

        fun constructFromPersisted(username: String): Username {
            return Username(username)
        }
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class InvalidUsernameLengthFailure(username: String) :
            Failure(
                MyFailure("InvalidUsernameLength", username)
            )


        class InvalidUsernameFormatFailure(username: String) : Failure(
            MyFailure("InvalidUsernameFormat", username)
        )

    }


}