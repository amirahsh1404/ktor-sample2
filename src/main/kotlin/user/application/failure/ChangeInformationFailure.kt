package user.application.failure

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure

sealed class ChangeInformationFailure(failure : MyFailure) : ResultFailure(failure) {
    class InvalidParamsFailure : ChangeInformationFailure(MyFailure("InvalidParams"))
    class UserNotFoundFailure(cause : MyFailure) : ChangeInformationFailure(MyFailure("UserNotFound", cause))
    class EmailExistsFailure(cause: MyFailure) : ChangeInformationFailure(MyFailure("EmailExists",cause))
}