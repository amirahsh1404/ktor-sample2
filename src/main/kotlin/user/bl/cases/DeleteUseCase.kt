package user.bl.cases

import user.bl.UserService
import user.bl.entity.Email
import user.bl.entity.FullName
import user.bl.entity.Password
import user.bl.entity.Username

class DeleteUseCase(private val userService: UserService) {

    fun deleteUser(username: Username, fullName: FullName, password : Password, email: Email) {

        val userExists = userService.exists(username)
        if (!userExists) {
            throw Exception("User doesnt exists")
        }



        userService.deleteUser(username)

    }
}