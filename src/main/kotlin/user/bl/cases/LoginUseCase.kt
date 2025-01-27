package user.bl.cases

import user.bl.UserService
import user.bl.entity.Password
import user.bl.entity.Username

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