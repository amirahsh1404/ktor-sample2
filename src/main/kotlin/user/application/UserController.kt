package user.application

import user.domain.UserRepo
import user.domain.UserService
import user.domain.cases.*
import user.domain.entity.Email
import user.domain.entity.FullName
import user.domain.entity.Password
import user.domain.entity.Username
import user.infr.repo.UserRepositoryImpl


class UserController {

    private val userRepo: UserRepo = UserRepositoryImpl()
    private val userService: UserService = UserService(userRepo)


    fun createUser(username: String?, password: String?, fullName: String?, email: String?): String {
        try {
            val usernameCheck =Username(username)
            val passwordCheck=Password(password)
            val fullNameCheck =FullName(fullName)
            val emailCheck =Email(email)

            val cmd = CreateUserCmd(usernameCheck, passwordCheck, fullNameCheck, emailCheck)

            val createUserUseCase = CreateUserUseCase(userService)
            createUserUseCase.createUser(cmd)

        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
        return "created"
    }

    fun loginUser(username: String?, password: String?): String {
        try {
            val usernameCheck = Username(username)
            val passwordCheck = Password(password)

            val cmd = LoginUserCmd(usernameCheck, passwordCheck)

            val loginUserUseCase = LoginUserUseCase(userService)
            loginUserUseCase.loginUser(cmd)

        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
        return "logged in"
    }

    fun changeInformation(username: String?, fullName: String?, email: String?): String {
        try {
            val usernameCheck = Username(username)
            val fullNameCheck = FullName(fullName)
            val emailCheck = Email(email)

            val cmd = ChangeInformationCmd(usernameCheck, fullNameCheck, emailCheck)

            val changeUserInformationUseCase = ChangeUserInformationUseCase(userService)
            changeUserInformationUseCase.changeInformationUser(cmd)

        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
        return "logged in"
    }

    fun deleteUser(username: String?): String {
        try {
            val usernameCheck = Username(username)

            val cmd = DeleteUserCmd(usernameCheck)

            val deleteUserUseCase = DeleteUserUseCase(userService)
            deleteUserUseCase.deleteUser(cmd)

        } catch (e: Exception) {
            return "Error: ${e.message}"
        }
        return "logged in"    }


}