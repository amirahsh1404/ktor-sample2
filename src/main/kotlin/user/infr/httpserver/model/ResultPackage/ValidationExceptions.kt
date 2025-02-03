package user.infr.httpserver.model.ResultPackage

import io.ktor.http.*


class ValidationExceptions(val validationExceptionsType: ValidationExceptionsType) :
    Exception(validationExceptionsType.message) {
        val statusCode: HttpStatusCode = validationExceptionsType.statusCode
}