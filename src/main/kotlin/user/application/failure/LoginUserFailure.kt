package user.application.failure

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure

sealed class LoginUserFailure(failure : MyFailure) : ResultFailure(failure) {
    class InvalidParamsFailure : LoginUserFailure(MyFailure("InvalidParams"))
    class UserNotFound(cause : MyFailure) : LoginUserFailure(MyFailure("UserNotFound",cause))
    class PasswordWrongFailure(cause: MyFailure) : LoginUserFailure(MyFailure("PasswordWrong",cause))
}