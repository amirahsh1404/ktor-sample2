package user.domain.aggregate.user.usecase.command

import io.ktor.http.*
import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.Email
import user.domain.aggregate.user.User
import user.domain.aggregate.user.entity.Username
import user.domain.aggregate.user.model.cmd.CreateUserCmd
import user.domain.services.UserService

class CreateUserUseCase(private val userService: UserService) {

    fun execute(cmd: CreateUserCmd): UserResult<Unit, Failure> {

        val userExists = userService.exists(cmd.username)
        if (userExists) {
            return UserResult.failure(Failure.UserExist(cmd.username))
        }

        val emailExists = userService.emailExists(cmd.email)
        if (emailExists) {
            return UserResult.failure(Failure.EmailExist(cmd.email))
        }

        userService.create(User.makeNew(cmd.username, cmd.password, cmd.fullName, cmd.email))

        return UserResult.success(Unit)
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class UserExist(username: Username) :
            Failure(MyFailure("User With This Username Exists", HttpStatusCode.BadRequest, username.value))

        class EmailExist(email: Email) :
            Failure(MyFailure("User With This Email Exists", HttpStatusCode.BadRequest, email.value))
    }

}

