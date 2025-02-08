package user.croscutting.ResultPackage.unused

import io.ktor.http.*


class ValidationExceptions(val validationExceptionsType: ValidationExceptionsType) :
    Exception(validationExceptionsType.message) {
        val statusCode: HttpStatusCode = validationExceptionsType.statusCode
}