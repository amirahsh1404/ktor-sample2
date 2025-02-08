package user.domain.aggregate.user.usecase.command

import io.ktor.http.*
import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.Password
import user.domain.aggregate.user.entity.Username
import user.domain.aggregate.user.model.cmd.LoginUserCmd
import user.domain.services.UserService

class LoginUserUseCase(private val userService: UserService) {

    fun execute(cmd: LoginUserCmd): UserResult<Unit, Failure> {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            return UserResult.failure(Failure.UserDoesNotExist(cmd.username))
        }

        if (!userService.passwordIsCorrect(cmd.username, cmd.password)) {
            return UserResult.failure(Failure.PasswordWrong(cmd.password))
        }

        //TODO : with any logic we have userService.login(username, password)

        return UserResult.success(Unit)

    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class UserDoesNotExist(username: Username) :
            Failure(MyFailure("User With This Username Does Not Exists", HttpStatusCode.BadRequest,username.value))

        class PasswordWrong(password: Password) :
            Failure(MyFailure("password is wrong", HttpStatusCode.BadRequest, password.value))
    }

}

