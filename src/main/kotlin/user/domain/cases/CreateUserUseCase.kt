package user.domain.cases

import user.domain.UserService
import user.domain.entity.*

class CreateUserUseCase(private val userService: UserService) {

    fun createUser(cmd: CreateUserCmd) {


        val userExists = userService.exists(cmd.username)
        if (userExists) {
            throw Exception("User already exists")
        }

        val emailExists = userService.emailExists(cmd.email)
        if (emailExists) {
            throw Exception("User email already exists")
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
