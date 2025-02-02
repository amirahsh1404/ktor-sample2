package user.domain.cases

import user.domain.UserService
import user.domain.entity.Email
import user.domain.entity.FullName
import user.domain.entity.Username

class ChangeUserInformationUseCase(private val userService: UserService) {

    fun changeInformationUser(cmd : ChangeInformationCmd) {

        val userExists = userService.exists(cmd.username)
        if (userExists) {
            throw Exception("User with username ${cmd.username.username} already exists")
        }

        val emailExists = userService.emailExists(cmd.email)
        if (emailExists) {
            throw Exception("User with email ${cmd.email.email} already exists")
        }

        userService.changeInformation(cmd.username, cmd.fullName, cmd.email)

    }
}

data class ChangeInformationCmd(
    val username: Username,
    val fullName: FullName,
    val email: Email,
)