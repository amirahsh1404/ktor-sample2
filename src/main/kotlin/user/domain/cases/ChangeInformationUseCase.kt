package user.domain.cases

import user.domain.UserService
import user.domain.entity.Email
import user.domain.entity.FullName
import user.domain.entity.Username

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