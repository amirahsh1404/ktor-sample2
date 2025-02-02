package user.domain.cases

import user.domain.UserService
import user.domain.entity.Password
import user.domain.entity.Username
import user.infr.httpserver.model.ResultPackage.UserExceptionType
import user.infr.httpserver.model.ResultPackage.UserExceptions

class LoginUserUseCase(private val userService: UserService) {

    fun execute(cmd: LoginUserCmd) {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            throw UserExceptions(
                UserExceptionType.USER_DOES_NOT_EXIST,
                "username" to cmd.username.value
            )
        }

        if (!userService.passwordIsCorrect(cmd.username, cmd.password)) {
            throw UserExceptions(UserExceptionType.PASSWORD_IS_NOT_CORRECT)
        }

        //TODO : with any logic we have userService.login(username, password)

    }
}

data class LoginUserCmd(
    val username: Username,
    val password: Password
)