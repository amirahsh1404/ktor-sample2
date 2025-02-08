package user.application

import user.application.failure.*
import user.application.params.*
import user.croscutting.ResultPackage.ResultFailure
import user.croscutting.ResultPackage.UserResult
import user.domain.aggregate.user.entity.*
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
import user.domain.services.UserService
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

        val failure = results.firstOrNull { it is UserResult.failure }
        if (failure != null) {
            return UserResult.failure(CreateUserFailure.InvalidParams((failure as UserResult.failure).failure.myFailure))
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
            return UserResult.failure(CreateUserFailure.RunTimeError(createResult.failure.myFailure))
        }
        return UserResult.success(Unit)

    }

    fun loginUser(params: LoginUserParams): UserResult<Unit, ResultFailure> {
        val usernameResult = Username.makeNew(params.username)
        val passwordResult = Password.makeNew(params.password)

        val results = listOf(usernameResult, passwordResult)

        val failure = results.firstOrNull { it is UserResult.failure }
        if (failure != null) {
            return UserResult.failure(LoginUserFailure.InvalidParams((failure as UserResult.failure).failure.myFailure))
        }

        val cmd = LoginUserCmd(
            (usernameResult as UserResult.success).value,
            (passwordResult as UserResult.success).value,
        )

        val loginUserUseCase = LoginUserUseCase(userService)
        val loginResult = loginUserUseCase.execute(cmd)

        if (loginResult is UserResult.failure) {
            return UserResult.failure(LoginUserFailure.RunTimeError(loginResult.failure.myFailure))
        }
        return UserResult.success(Unit)

    }

    fun changeInformation(params: ChangeInformationParams): UserResult<Unit, ResultFailure> {
        val usernameResult: UserResult<Username, Username.Failure> = Username.makeNew(params.username)
        val fullNameResult = FullName.makeNew(params.fullName)
        val emailResult = Email.makeNew(params.email)


        val results = listOf(usernameResult, fullNameResult, emailResult)

        val failure = results.firstOrNull { it is UserResult.failure }
        if (failure != null) {
            return UserResult.failure(ChangeInformationFailure.InvalidParams((failure as UserResult.failure).failure.myFailure))
        }

        val cmd = ChangeInformationCmd(
            (usernameResult as UserResult.success).value,
            (fullNameResult as UserResult.success).value,
            (emailResult as UserResult.success).value
        )

        val changeUserInformationUseCase = ChangeUserInformationUseCase(userService)
        val changeInfoResult = changeUserInformationUseCase.execute(cmd)
        if (changeInfoResult is UserResult.failure) {
            return UserResult.failure(ChangeInformationFailure.RunTimeError(changeInfoResult.failure.myFailure))
        }
        return UserResult.success(Unit)

    }

    fun deleteUser(params: DeleteUserParams): UserResult<Unit, ResultFailure> {
        val usernameResult = Username.makeNew(params.username)

        if (usernameResult is UserResult.failure) {
            val failure = usernameResult
            return UserResult.failure(DeleteUserFailure.InvalidParams((failure as UserResult.failure).failure.myFailure))
        }

        val cmd = DeleteUserCmd((usernameResult as UserResult.success).value)

        val deleteUserUseCase = DeleteUserUseCase(userService)
        val deleteResult = deleteUserUseCase.execute(cmd)

        if (deleteResult is UserResult.failure) {
            return UserResult.failure(DeleteUserFailure.RunTimeError(deleteResult.failure.myFailure))
        }
        return UserResult.success(Unit)

    }

    fun getInformation(params: GetInformationParams): UserResult<User, ResultFailure> {
        val usernameResult = Username.makeNew(params.username)
        val passwordResult = Password.makeNew(params.password)

        val results = listOf(usernameResult, passwordResult)

        val failure = results.firstOrNull { it is UserResult.failure }

        if (failure != null) {
            return UserResult.failure(GetInformationFailure.InvalidParams((failure as UserResult.failure).failure.myFailure))
        }

        val cmd = GetInformationCmd(
            (usernameResult as UserResult.success).value,
            (passwordResult as UserResult.success).value,
        )

        val getInformationUseCase = GetInformationUseCase(userService)

        val getInformationResult = getInformationUseCase.execute(cmd)
        if (getInformationResult is UserResult.failure) {
            return UserResult.failure(GetInformationFailure.RunTimeError(getInformationResult.failure.myFailure))
        }
        return UserResult.success((getInformationResult as UserResult.success).value)

    }

}