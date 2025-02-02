package user.domain.cases

import user.domain.UserService
import user.domain.entity.Password
import user.domain.entity.Username

class LoginUserUseCase(private val userService: UserService) {

    fun loginUser(cmd : LoginUserCmd) {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            throw Exception("User doesnt exist")
        }

        if (!userService.passwordIsCorrect(cmd.username,cmd.password) ) {
            throw Exception("Password not correct")
        }

        //TODO : with any logic we have userService.login(username, password)

    }
}

data class LoginUserCmd(
    val username: Username,
    val password: Password)