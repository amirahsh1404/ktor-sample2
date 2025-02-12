package user.domain.aggregate.user.usecase.command

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.User
import user.domain.aggregate.user.entity.valueObjects.Email
import user.domain.aggregate.user.entity.valueObjects.Username
import user.domain.aggregate.user.model.cmd.CreateUserCmd
import user.domain.aggregate.user.UserService

class CreateUserUseCase(private val userService: UserService) {

    fun execute(cmd: CreateUserCmd): UserResult<Unit, Failure> {

        val userExists = userService.exists(cmd.username)
        if (userExists) {
            return UserResult.failure(Failure.UserExistFailure(cmd.username))
        }

        val emailExists = userService.emailExists(cmd.email)
        if (emailExists) {
            return UserResult.failure(Failure.EmailExistFailure(cmd.email))
        }

        userService.create(User.makeNew(cmd.username, cmd.password, cmd.fullName, cmd.email))

        return UserResult.success(Unit)
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class UserExistFailure(username: Username) :
            Failure(MyFailure("UserExists", username.value))

        class EmailExistFailure(email: Email) :
            Failure(MyFailure("EmailExists", email.value))
    }

}

