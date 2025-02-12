package user.domain.aggregate.user.usecase.command

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
            return UserResult.failure(Failure.UserDoesNotExistFailure(cmd.username))
        }

        userService.deleteUser(cmd.username)

        return UserResult.success(Unit)
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class UserDoesNotExistFailure(username: Username) :
            Failure(MyFailure("UserNotFound", username.value))
    }

}
