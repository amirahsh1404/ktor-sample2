package user.domain.cases

import user.domain.UserService
import user.domain.entity.Username
import user.infr.httpserver.model.ResultPackage.UserExceptionType
import user.infr.httpserver.model.ResultPackage.UserExceptions

class DeleteUserUseCase(private val userService: UserService) {

    fun execute(cmd: DeleteUserCmd) {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            throw UserExceptions(
                UserExceptionType.USER_DOES_NOT_EXIST,
                "username" to cmd.username.value
            )
        }

        userService.deleteUser(cmd.username)

    }
}

data class DeleteUserCmd(
    val username: Username,
)