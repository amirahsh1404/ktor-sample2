package user.domain.aggregate.user.entity

import io.ktor.http.*
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
                    UserResult.failure(Failure.InvalidFullNameLength())

                !value.matches("[a-zA-Z]+".toRegex()) ->
                    UserResult.failure(Failure.InvalidFullNameFormat())

                else -> UserResult.success(FullName(value))
            }
        }

        fun constructFromPersisted(fullName: String): FullName {
            return FullName(fullName)
        }
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class InvalidFullNameLength :
            Failure(MyFailure("FullName should be at least 3 characters and at most 20 characters", HttpStatusCode.NotAcceptable))

        class InvalidFullNameFormat : Failure(MyFailure("FullName format is invalid", HttpStatusCode.NotAcceptable))
    }
}