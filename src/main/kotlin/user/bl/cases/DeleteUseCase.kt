package user.bl.cases

import user.bl.UserService
import user.bl.entity.Username

class DeleteUseCase(private val userService: UserService) {

    fun deleteUser(username: Username) {

        val userExists = userService.exists(username)
        if (!userExists) {
            throw Exception("User doesnt exists")
        }



        userService.deleteUser(username)

    }
}