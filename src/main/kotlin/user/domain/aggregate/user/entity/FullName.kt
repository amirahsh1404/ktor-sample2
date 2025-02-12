package user.domain.aggregate.user.entity

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult

data class FullName private constructor(val value: String) {

    init {
        require(value.length in 3..20 ) { "FullName should be at least 5 characters and at most 15 characters" }
        require(value.matches("[a-zA-Z]+".toRegex())) { "FullName format is wrong" }
    }

    companion object {
        fun makeNew(value: String): UserResult<FullName, Failure> {
            return when {
                value.length !in 3..20 ->
                    UserResult.failure(Failure.InvalidFullNameLengthFailure(fullName = value))

                !value.matches("[a-zA-Z]+".toRegex()) ->
                    UserResult.failure(Failure.InvalidFullNameFormatFailure(fullName = value))

                else -> UserResult.success(FullName(value))
            }
        }

        fun constructFromPersisted(fullName: String): FullName {
            return FullName(fullName)
        }
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class InvalidFullNameLengthFailure(fullName: String) :
            Failure(MyFailure("InvalidFullNameLength",fullName))

        class InvalidFullNameFormatFailure(fullName: String) : Failure(MyFailure("InvalidFullNameFormat",fullName))
    }
}