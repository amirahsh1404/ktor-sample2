package user.application.failure

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure

sealed class LoginUserFailure(failure : MyFailure) : ResultFailure(failure) {
    class InvalidParams(failure: MyFailure) : LoginUserFailure(failure)
    class RunTimeError(failure: MyFailure) : CreateUserFailure(failure)

}