package user.application

import user.domain.UserRepo
import user.domain.UserService
import user.domain.cases.*
import user.domain.entity.*
import user.infr.httpserver.model.ResultPackage.UserResult
import user.infr.httpserver.model.ResultPackage.UserSuccess
import user.infr.httpserver.model.ResultPackage.UserSuccessType
import user.infr.repo.UserRepositoryImpl


class UserController {

    private val userRepo: UserRepo = UserRepositoryImpl()
    private val userService: UserService = UserService(userRepo)


    fun createUser(username: String, password: String, fullName: String, email: String): UserResult<UserSuccess> {
        try {
            val usernameCheck = Username(username)
            val passwordCheck = Password(password)
            val fullNameCheck = FullName(fullName)
            val emailCheck = Email(email)

            val cmd = CreateUserCmd(usernameCheck, passwordCheck, fullNameCheck, emailCheck)

            val createUserUseCase = CreateUserUseCase(userService)
            createUserUseCase.execute(cmd)
            return UserResult.Success(UserSuccess(UserSuccessType.CREATE_SUCCESS))
        } catch (e: Exception) {
            return UserResult.Error(e)
        }
    }

    fun loginUser(username: String, password: String): UserResult<UserSuccess> {
        try {
            val usernameCheck = Username(username)
            val passwordCheck = Password(password)

            val cmd = LoginUserCmd(usernameCheck, passwordCheck)

            val loginUserUseCase = LoginUserUseCase(userService)
            loginUserUseCase.execute(cmd)

            return UserResult.Success(UserSuccess(UserSuccessType.LOGIN_SUCCESS))
        } catch (e: Exception) {
            return UserResult.Error(e)
        }
    }

    fun changeInformation(username: String, fullName: String, email: String): UserResult<UserSuccess> {
        try {
            val usernameCheck = Username(username)
            val fullNameCheck = FullName(fullName)
            val emailCheck = Email(email)

            val cmd = ChangeInformationCmd(usernameCheck, fullNameCheck, emailCheck)

            val changeUserInformationUseCase = ChangeUserInformationUseCase(userService)
            changeUserInformationUseCase.execute(cmd)
            return UserResult.Success(UserSuccess(UserSuccessType.CHANGE_INFO_SUCCESS))
        } catch (e: Exception) {
            return UserResult.Error(e)
        }
    }

    fun deleteUser(username: String): UserResult<UserSuccess> {
        try {
            val usernameCheck = Username(username)

            val cmd = DeleteUserCmd(usernameCheck)

            val deleteUserUseCase = DeleteUserUseCase(userService)
            deleteUserUseCase.execute(cmd)


            return UserResult.Success(UserSuccess(UserSuccessType.DELETE_SUCCESS))
        } catch (e: Exception) {
            return UserResult.Error(e)
        }
    }

    fun getInformation(username: String, password: String ): UserResult<User> {
        try {
            val usernameCheck = Username(username)
            val passwordCheck = Password(password)

            val cmd = GetInformationCmd(usernameCheck, passwordCheck)

            val getInformationUseCase = GetInformationUseCase(userService)
            return UserResult.Success(getInformationUseCase.execute(cmd))


        } catch (e: Exception) {
            return UserResult.Error(e)
        }
    }

    fun getInformationEx(username: String, password: String ): User {
            val usernameCheck = Username(username)
            val passwordCheck = Password(password)

            val cmd = GetInformationCmd(usernameCheck, passwordCheck)

            val getInformationUseCase = GetInformationUseCase(userService)
            return getInformationUseCase.execute(cmd)

    }

}