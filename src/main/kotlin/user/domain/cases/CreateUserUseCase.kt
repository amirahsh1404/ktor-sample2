package user.domain.cases

import user.domain.UserService
import user.domain.entity.*
import user.infr.httpserver.model.ResultPackage.UserExceptionType
import user.infr.httpserver.model.ResultPackage.UserExceptions

class CreateUserUseCase(private val userService: UserService) {

    fun execute(cmd: CreateUserCmd) {

        val userExists = userService.exists(cmd.username)
        if (userExists) {
            throw UserExceptions(
                UserExceptionType.USER_ALREADY_EXISTS,
                "username" to cmd.username.value
            )
        }

        val emailExists = userService.emailExists(cmd.email)
        if (emailExists) {
            throw UserExceptions(
                UserExceptionType.EMAIL_ALREADY_EXISTS,
                "email" to cmd.email.value
            )
        }

        userService.create(User(cmd.username, cmd.password, cmd.fullName, cmd.email))

    }
}

data class CreateUserCmd(
    val username: Username,
    val password: Password,
    val fullName: FullName,
    val email: Email,
)
