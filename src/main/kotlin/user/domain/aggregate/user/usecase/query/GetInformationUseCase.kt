package user.domain.aggregate.user.usecase.query

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.User
import user.domain.aggregate.user.entity.Password
import user.domain.aggregate.user.entity.Username
import user.domain.aggregate.user.model.qry.GetInformationCmd
import user.domain.services.UserService

class GetInformationUseCase(val userService: UserService) {

    fun execute(cmd: GetInformationCmd): UserResult<User, Failure> {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            return UserResult.failure(Failure.UserNotFoundFailure(cmd.username))
        }

        if (!userService.passwordIsCorrect(cmd.username, cmd.password)) {
            return UserResult.failure(Failure.PasswordWrongFailure(cmd.password))
        }

        return UserResult.success(userService.getUserInformation(cmd.username))
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class UserNotFoundFailure(username: Username) :
            Failure(MyFailure("UserNotFound", username.value))

        class PasswordWrongFailure(password: Password) : Failure(MyFailure("PasswordWrong", password.value))
    }

}

