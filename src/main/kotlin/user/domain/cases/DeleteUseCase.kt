package user.domain.cases

import user.domain.UserService
import user.domain.entity.Username

class DeleteUseCase(private val userService: UserService) {

    fun deleteUser(username: Username) {

        val userExists = userService.exists(username)
        if (!userExists) {
            throw Exception("User doesnt exists")
        }



        userService.deleteUser(username)

    }
}