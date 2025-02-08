package user.domain.aggregate.user.usecase.command

import io.ktor.http.*
import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.Email
import user.domain.aggregate.user.entity.Username
import user.domain.aggregate.user.model.cmd.ChangeInformationCmd
import user.domain.services.UserService

class ChangeUserInformationUseCase(private val userService: UserService) {

    fun execute(cmd: ChangeInformationCmd) : UserResult<Unit, Failure> {

        val userExists = userService.exists(cmd.username)
        if (!userExists) {
            return UserResult.failure(Failure.UserDoesNotExist(cmd.username))
        }

        val emailExists = userService.emailExists(cmd.email)
        if (emailExists) {
           return UserResult.failure(Failure.EmailExist(cmd.email))
        }

        userService.changeInformation(cmd.username, cmd.fullName, cmd.email)

        return UserResult.success(Unit)
    }

    sealed class Failure(failure: MyFailure) : ResultFailure(failure) {
        class UserDoesNotExist(username : Username) : Failure(MyFailure("User Does Not Exist", HttpStatusCode.BadRequest,username.value))
        class EmailExist(email : Email) : Failure(MyFailure("Email Already Exist", HttpStatusCode.BadRequest,email.value))
    }

}

