package user.domain.aggregate.user.entity

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult

data class Email private constructor(val value: String) {

    init {
        require(value.length in 5..100) {
            "Email should be at least 5 characters and at most 100 characters"
        }
        require(value.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[A-Za-z]+".toRegex())) {
            "Email format is invalid"
        }
    }


    companion object {
        fun makeNew(value: String): UserResult<Email, Failure> {
            return when {
                value.length !in 5..100 ->
                    UserResult.failure(Failure.InvalidEmailLengthFailure(email = value))

                !value.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[A-Za-z]+".toRegex()) ->
                    UserResult.failure(Failure.InvalidEmailFormatFailure(email = value))

                else -> UserResult.success(Email(value))
            }
        }

        fun constructFromPersisted(email: String): Email {
            return Email(email)
        }
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class InvalidEmailLengthFailure(email : String) :
            Failure(
                MyFailure("InvalidEmailLength",email))

        class InvalidEmailFormatFailure(email: String) : Failure(MyFailure("InvalidEmailFormat",email))
    }
}
