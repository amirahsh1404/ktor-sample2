package user.domain.cases

import user.domain.UserService
import user.domain.entity.Password
import user.domain.entity.Username

class LoginUseCase(private val userService: UserService) {

    fun loginUser(username: Username,  password : Password) {

        val userExists = userService.exists(username)
        if (!userExists) {
            throw Exception("User doesnt exist")
        }

        if (!userService.passwordIsCorrect(username,password) ) {
            throw Exception("Password not correct")
        }

        //TODO : with any logic we have userService.login(username, password)

    }
}