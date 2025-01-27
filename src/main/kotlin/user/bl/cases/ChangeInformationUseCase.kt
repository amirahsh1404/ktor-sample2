package user.bl.cases

import user.bl.UserService
import user.bl.entity.Email
import user.bl.entity.FullName
import user.bl.entity.Username

class ChangeInformationUseCase(private val userService: UserService) {

    fun changeInformationUser(username : Username, fullName : FullName, email : Email) {

        val userExists = userService.exists(username)
        if (userExists) {
            throw Exception("User with username ${username.username} already exists")
        }

        val emailExists = userService.emailExists(email)
        if (emailExists) {
            throw Exception("User with email ${email.email} already exists")
        }

        userService.changeInformation(username, fullName, email)

    }
}