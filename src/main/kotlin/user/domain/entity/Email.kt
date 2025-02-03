package user.domain.entity

import user.infr.httpserver.model.ResultPackage.ValidationExceptions
import user.infr.httpserver.model.ResultPackage.ValidationExceptionsType

data class Email(val value: String) {


    init {
        require(value.length >= 5) { throw ValidationExceptions(ValidationExceptionsType.EMAIL_IS_SHORT) }
        require(value.matches(Regex.email)) { throw ValidationExceptions(ValidationExceptionsType.EMAIL_FORMAT_WRONG) }
    }
}

