package user.application

import user.application.failure.*
import user.application.params.*
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.User
import user.domain.aggregate.user.entity.valueObjects.Email
import user.domain.aggregate.user.entity.valueObjects.FullName
import user.domain.aggregate.user.entity.valueObjects.Password
import user.domain.aggregate.user.entity.valueObjects.Username
import user.domain.aggregate.user.model.cmd.ChangeInformationCmd
import user.domain.aggregate.user.model.cmd.CreateUserCmd
import user.domain.aggregate.user.model.cmd.DeleteUserCmd
import user.domain.aggregate.user.model.cmd.LoginUserCmd
import user.domain.aggregate.user.model.qry.GetInformationCmd
import user.domain.aggregate.user.usecase.command.ChangeUserInformationUseCase
import user.domain.aggregate.user.usecase.command.CreateUserUseCase
import user.domain.aggregate.user.usecase.command.DeleteUserUseCase
import user.domain.aggregate.user.usecase.command.LoginUserUseCase
import user.domain.aggregate.user.usecase.query.GetInformationUseCase
import user.domain.repository.UserRepo
import user.domain.aggregate.user.UserService
import user.infr.repo.UserRepositoryImpl


class UserController {

    private val userRepo: UserRepo = UserRepositoryImpl()
    private val userService: UserService = UserService(userRepo)


    suspend fun createUser(params: CreateUserParams): UserResult<Unit, CreateUserFailure> {

        val usernameResult = Username.makeNew(params.username)
        val passwordResult = Password.makeNew(params.password)
        val fullNameResult = FullName.makeNew(params.fullName)
        val emailResult = Email.makeNew(params.email)

        val results = listOf(
            usernameResult,
            passwordResult,
            fullNameResult,
            emailResult
        )

        val firstFailure = results.firstOrNull { it is UserResult.failure }
        if (firstFailure != null) {
            val failure = CreateUserFailure.InvalidParamsFailure()
            failure.myFailure.addCause((firstFailure as UserResult.failure).failure.myFailure)
            return UserResult.failure(failure)
        }


        val cmd = CreateUserCmd(
            (usernameResult as UserResult.success).value,
            (passwordResult as UserResult.success).value,
            (fullNameResult as UserResult.success).value,
            (emailResult as UserResult.success).value
        )


        val createUserUseCase = CreateUserUseCase(userService)
        val createResult = createUserUseCase.execute(cmd)

        if (createResult is UserResult.failure) {
            val failure: CreateUserFailure
            when (val cause = createResult.failure) {
                is CreateUserUseCase.Failure.UserExistFailure -> failure = CreateUserFailure.UserExistsFailure(cause.myFailure)
                else -> failure = CreateUserFailure.EmailExistsFailure(cause.myFailure)
            }
            return UserResult.failure(failure)
        }
        return UserResult.success(Unit)
    }

    fun loginUser(params: LoginUserParams): UserResult<Unit, CreateUserFailure> {
        val usernameResult = Username.makeNew(params.username)
        val passwordResult = Password.makeNew(params.password)

        val results = listOf(usernameResult, passwordResult)

        val firstFailure = results.firstOrNull { it is UserResult.failure }
        if (firstFailure != null) {
            val failure = CreateUserFailure.InvalidParamsFailure()
            failure.myFailure.addCause((firstFailure as UserResult.failure).failure.myFailure)
            return UserResult.failure(failure)
        }

        val cmd = LoginUserCmd(
            (usernameResult as UserResult.success).value,
            (passwordResult as UserResult.success).value,
        )

        val loginUserUseCase = LoginUserUseCase(userService)
        val loginResult = loginUserUseCase.execute(cmd)

        if (loginResult is UserResult.failure) {
            val failure: LoginUserFailure
            when (val cause = loginResult.failure) {
                is LoginUserUseCase.Failure.UserNotFoundFailure -> failure = LoginUserFailure.UserNotFound(cause.myFailure)
                else -> failure = LoginUserFailure.PasswordWrongFailure(cause.myFailure)
            }
            return UserResult.failure(failure)
        }
        return UserResult.success(Unit)

    }



    fun changeInformation(params: ChangeInformationParams): UserResult<Unit, ResultFailure> {
        val usernameResult: UserResult<Username, Username.Failure> = Username.makeNew(params.username)
        val fullNameResult = FullName.makeNew(params.fullName)
        val emailResult = Email.makeNew(params.email)


        val results = listOf(usernameResult, fullNameResult, emailResult)

        val firstFailure = results.firstOrNull { it is UserResult.failure }
        if (firstFailure != null) {
            val failure = CreateUserFailure.InvalidParamsFailure()
            failure.myFailure.addCause((firstFailure as UserResult.failure).failure.myFailure)
            return UserResult.failure(failure)
        }

        val cmd = ChangeInformationCmd(
            (usernameResult as UserResult.success).value,
            (fullNameResult as UserResult.success).value,
            (emailResult as UserResult.success).value
        )

        val changeUserInformationUseCase = ChangeUserInformationUseCase(userService)
        val changeInfoResult = changeUserInformationUseCase.execute(cmd)
        if (changeInfoResult is UserResult.failure) {
            val failure: ChangeInformationFailure
            when (val cause = changeInfoResult.failure) {
                is ChangeUserInformationUseCase.Failure.UserNotFoundFailure -> failure =
                    ChangeInformationFailure.UserNotFoundFailure(cause.myFailure)
                else -> failure =
                    ChangeInformationFailure.EmailExistsFailure(cause.myFailure)

            }
            return UserResult.failure(failure)
        }
        return UserResult.success(Unit)

    }

    fun deleteUser(params: DeleteUserParams): UserResult<Unit, DeleteUserFailure> {
        val usernameResult = Username.makeNew(params.username)

        if (usernameResult is UserResult.failure) {
            val failure = DeleteUserFailure.InvalidParamsFailure()
            failure.myFailure.addCause(usernameResult.failure.myFailure)
            return UserResult.failure(failure)
        }

        val cmd = DeleteUserCmd((usernameResult as UserResult.success).value)

        val deleteUserUseCase = DeleteUserUseCase(userService)
        val deleteResult = deleteUserUseCase.execute(cmd)

        if (deleteResult is UserResult.failure) {
            val failure: DeleteUserFailure
            when (val cause = deleteResult.failure) {
                else -> failure = DeleteUserFailure.UserNotFoundFailure(cause.myFailure)
            }
            return UserResult.failure(failure)
        }
        return UserResult.success(Unit)

    }

    fun getInformation(params: GetInformationParams): UserResult<User, ResultFailure> {
        val usernameResult = Username.makeNew(params.username)
        val passwordResult = Password.makeNew(params.password)

        val results = listOf(usernameResult, passwordResult)

        val firstFailure = results.firstOrNull { it is UserResult.failure }
        if (firstFailure != null) {
            val failure = CreateUserFailure.InvalidParamsFailure()
            failure.myFailure.addCause((firstFailure as UserResult.failure).failure.myFailure)
            return UserResult.failure(failure)
        }

        val cmd = GetInformationCmd(
            (usernameResult as UserResult.success).value,
            (passwordResult as UserResult.success).value,
        )

        val getInformationUseCase = GetInformationUseCase(userService)

        val getInformationResult = getInformationUseCase.execute(cmd)
        if (getInformationResult is UserResult.failure) {
            val failure: GetInformationFailure
            when (val cause = getInformationResult.failure) {
                is GetInformationUseCase.Failure.UserNotFoundFailure -> failure = GetInformationFailure.UserNotFound(cause.myFailure)
                else -> failure = GetInformationFailure.PasswordWrongFailure(cause.myFailure)
            }
            return UserResult.failure(failure)        }
        return UserResult.success((getInformationResult as UserResult.success).value)

    }

}