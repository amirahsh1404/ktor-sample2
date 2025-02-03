package user.domain.entity

import user.infr.httpserver.model.ResultPackage.ValidationExceptions
import user.infr.httpserver.model.ResultPackage.ValidationExceptionsType

data class Password(val value: String) {

    init {
        require(value.length >= 7) { throw ValidationExceptions(ValidationExceptionsType.PASSWORD_IS_SHORT) }
        require(value.length <= 20) { throw ValidationExceptions(ValidationExceptionsType.PASSWORD_IS_LONG) }
        require(value.any { it.isDigit()} &&
                value.any { it.isLowerCase() } &&
                value.any {it.isUpperCase()} &&
                value.any {!it.isLetterOrDigit()})
        { throw ValidationExceptions(ValidationExceptionsType.PASSWORD_FORMAT_WRONG) }
    }


}