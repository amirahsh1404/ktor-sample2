package user.domain.aggregate.user.usecase.command

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.valueObjects.Email
import user.domain.aggregate.user.entity.valueObjects.Username
import user.domain.aggregate.user.model.cmd.ChangeInformationCmd
import user.domain.aggregate.user.UserService

class ChangeUserInformationUseCase(private val userService: UserService) {

    fun execute(cmd: ChangeInformationCmd): UserResult<Unit, Failure> {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            return UserResult.failure(Failure.UserNotFoundFailure(cmd.username))
        }

        val emailExists = userService.emailExists(cmd.email)
        if (emailExists) {
            return UserResult.failure(Failure.EmailExistFailure(cmd.email))
        }

        userService.changeInformation(cmd.username, cmd.fullName, cmd.email)

        return UserResult.success(Unit)
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class UserNotFoundFailure(username: Username) : Failure(MyFailure("UserNotFound", username.value))
        class EmailExistFailure(email: Email) : Failure(MyFailure("EmailExists", email.value))
    }

}

