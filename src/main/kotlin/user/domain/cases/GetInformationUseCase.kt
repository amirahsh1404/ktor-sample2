package user.domain.cases

import user.domain.UserService
import user.domain.entity.Password
import user.domain.entity.User
import user.domain.entity.Username

class GetInformationUseCase(val userService: UserService) {

    fun execute(cmd : GetInformationCmd) : User {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            throw Exception("User doesnt exist")
        }

        if (!userService.passwordIsCorrect(cmd.username,cmd.password) ) {
            throw Exception("Password not correct")
        }

        return userService.getUserInformation(cmd.username)
    }
}

data class GetInformationCmd(
    val username : Username,
    val password : Password,
)