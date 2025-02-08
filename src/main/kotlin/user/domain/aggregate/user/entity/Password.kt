package user.domain.aggregate.user.entity

import io.ktor.http.HttpStatusCode.Companion.NotAcceptable
import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult


data class Password private constructor(val value: String) {

    init {
        require(value.length in 7..25) {
            "Password should be at least 7 characters and at most 25 characters"
        }
        require(value.any {
            it.isDigit() &&
                    it.isLowerCase() &&
                    it.isUpperCase() &&
                    it.isLetterOrDigit()
        })
        {
            "Password format is wrong, your password most contains " +
                    "lower and upper case letters, number and special characters"
        }
    }

    companion object {
        fun makeNew(value: String): UserResult<Password, Failure> {
            return when {
                value.length !in 7..25 ->
                    UserResult.failure(Failure.InvalidPasswordLength())

                !value.any {
                    it.isDigit() &&
                            it.isLowerCase() &&
                            it.isUpperCase() &&
                            it.isLetterOrDigit()
                } ->
                    UserResult.failure(Failure.InvalidPasswordFormat())

                else -> UserResult.success(Password(value))
            }
        }

        fun constructFromPersisted(password: String): Password {
            return Password(password)
        }
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class InvalidPasswordLength :
            Failure(MyFailure("Password should be at least 7 characters", NotAcceptable))

        class InvalidPasswordFormat : Failure(
            MyFailure(
                "Password format is wrong, your password most contains " +
                        "lower and upper case letters, number and special characters"
            , NotAcceptable)
        )
    }


}