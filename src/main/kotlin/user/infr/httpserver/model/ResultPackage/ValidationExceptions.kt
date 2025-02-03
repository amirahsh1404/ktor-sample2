package user.infr.httpserver.model.ResultPackage


class ValidationExceptions(val validationExceptionsType: ValidationExceptionsType) : Exception(validationExceptionsType.message) {
}