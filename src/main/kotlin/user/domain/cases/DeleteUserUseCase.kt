package user.domain.cases

import user.domain.UserService
import user.domain.entity.Username

class DeleteUserUseCase(private val userService: UserService) {

    fun deleteUser(cmd : DeleteUserCmd) {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            throw Exception("User doesnt exists")
        }

        userService.deleteUser(cmd.username)

    }
}

data class DeleteUserCmd(
    val username: Username,
)