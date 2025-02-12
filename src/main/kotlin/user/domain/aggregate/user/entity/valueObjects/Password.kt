package user.domain.aggregate.user.entity.valueObjects

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult


data class Password private constructor(val value: String) {

    init {
        require(value.length in 7..25) {
            "Password should be at least 7 characters and at most 25 characters"
        }
        require(value.any { it.isDigit() } &&
                value.any { it.isUpperCase() } &&
                value.any { it.isLowerCase() } &&
                value.any { it.isLetterOrDigit()})
        {
            "Password format is wrong, your password most contains " +
                    "lower and upper case letters, number and special characters"
        }
    }

    companion object {
        fun makeNew(value: String): UserResult<Password, Failure> {
            return when {
                value.length !in 7..25 ->
                    UserResult.failure(Failure.InvalidPasswordLengthFailure(password = value))

                !value.any { it.isDigit() } ||
                        !value.any { it.isUpperCase() } ||
                        !value.any { it.isLowerCase() } ||
                        !value.any { it.isLetterOrDigit()} ->
                    UserResult.failure(Failure.InvalidPasswordFormatFailure(password = value))

                else -> UserResult.success(Password(value))
            }
        }

        fun constructFromPersisted(password: String): Password {
            return Password(password)
        }
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class InvalidPasswordLengthFailure(password: String) :
            Failure(MyFailure("InvalidPasswordLength",password))

        class InvalidPasswordFormatFailure(password: String) : Failure(
            MyFailure(
                "InvalidPasswordFormat",password)
        )
    }


}