package user.domain.aggregate.user.usecase.command

import io.ktor.http.*
import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.Username
import user.domain.aggregate.user.model.cmd.DeleteUserCmd
import user.domain.services.UserService

class DeleteUserUseCase(private val userService: UserService) {

    fun execute(cmd: DeleteUserCmd): UserResult<Unit, Failure> {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            return UserResult.failure(Failure.UserDoesNotExist(cmd.username))
        }

        userService.deleteUser(cmd.username)

        return UserResult.success(Unit)
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class UserDoesNotExist(username: Username) :
            Failure(MyFailure("User With This Username Does Not Exists", HttpStatusCode.BadRequest, username.value))
    }

}
