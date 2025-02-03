package user.domain.entity

import user.infr.httpserver.model.ResultPackage.ValidationExceptions
import user.infr.httpserver.model.ResultPackage.ValidationExceptionsType

data class Username(val value: String) {


    init {
        require(value.length >= 3) {
            throw ValidationExceptions(ValidationExceptionsType.USERNAME_IS_SHORT)
        }
        require(value.length <= 15) {
            throw ValidationExceptions(ValidationExceptionsType.USERNAME_IS_LONG)
        }
        require(value.matches(Regex.username)) {
            throw ValidationExceptions(ValidationExceptionsType.USERNAME_FORMAT_WRONG)
        }
    }

}