package user.domain.aggregate.user.usecase.command

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.valueObjects.Password
import user.domain.aggregate.user.entity.valueObjects.Username
import user.domain.aggregate.user.model.cmd.LoginUserCmd
import user.domain.aggregate.user.UserService

class LoginUserUseCase(private val userService: UserService) {

    fun execute(cmd: LoginUserCmd): UserResult<Unit, Failure> {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            return UserResult.failure(Failure.UserNotFoundFailure(cmd.username))
        }

        if (!userService.passwordIsCorrect(cmd.username, cmd.password)) {
            return UserResult.failure(Failure.PasswordWrongFailure(cmd.password))
        }

        //TODO : with any logic we have userService.login(username, password)

        return UserResult.success(Unit)

    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class UserNotFoundFailure(username: Username) :
            Failure(MyFailure("UserNotFound",username.value))

        class PasswordWrongFailure(password: Password) :
            Failure(MyFailure("PasswordWrong", password.value))
    }

}

