package user.application.failure

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure

sealed class CreateUserFailure(failure : MyFailure) : ResultFailure(failure) {
    class InvalidParamsFailure : CreateUserFailure(MyFailure("InvalidParams", ))
    class UserExistsFailure(cause : MyFailure) : CreateUserFailure(MyFailure("UserExists",cause))
    class EmailExistsFailure(cause: MyFailure) : CreateUserFailure(MyFailure("EmailExists",cause))

}