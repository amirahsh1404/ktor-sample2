package user.application.failure

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure

sealed class GetInformationFailure(failure : MyFailure) : ResultFailure(failure) {
    class InvalidParamsFailure : GetInformationFailure(MyFailure("InvalidParams"))
    class UserNotFound(cause : MyFailure) : GetInformationFailure(MyFailure("UserNotFound",cause))
    class PasswordWrongFailure(cause: MyFailure) : GetInformationFailure(MyFailure("PasswordWrong",cause))
}