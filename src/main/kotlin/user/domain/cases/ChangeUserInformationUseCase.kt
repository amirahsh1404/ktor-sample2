package user.domain.cases

import user.domain.UserService
import user.domain.entity.Email
import user.domain.entity.FullName
import user.domain.entity.Username
import user.infr.httpserver.model.ResultPackage.UserExceptionType
import user.infr.httpserver.model.ResultPackage.UserExceptions

class ChangeUserInformationUseCase(private val userService: UserService) {

    fun execute(cmd: ChangeInformationCmd) {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            throw UserExceptions(
                UserExceptionType.USER_DOES_NOT_EXIST,
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

        userService.changeInformation(cmd.username, cmd.fullName, cmd.email)

    }
}

data class ChangeInformationCmd(
    val username: Username,
    val fullName: FullName,
    val email: Email,
)