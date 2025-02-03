package user.domain.entity

import user.infr.httpserver.model.ResultPackage.ValidationExceptions
import user.infr.httpserver.model.ResultPackage.ValidationExceptionsType

data class FullName(val value: String) {

    init {
        require(value.length >= 5) { throw ValidationExceptions(ValidationExceptionsType.FULLNAME_IS_SHORT) }
        require(value.length <= 15) { throw ValidationExceptions(ValidationExceptionsType.FULLNAME_IS_LONG) }
        require(value.matches(Regex.fullName)) { throw ValidationExceptions(ValidationExceptionsType.FULLNAME_FORMAT_WRONG) }
    }

}