package user.bl.cases

import user.bl.UserService
import user.bl.entity.*

class CreateUserUseCase(private val userService: UserService) {

    fun createUser(username: Username, fullName: FullName, password : Password, email: Email) {

        val userExists = userService.exists(username)
        if (userExists) {
            throw Exception("User already exists")
        }

        val emailExists = userService.emailExists(email)
        if (emailExists) {
            throw Exception("User email already exists")
        }

        userService.create(User(username, password,fullName, email))

    }
}