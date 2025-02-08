package user.application.failure

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure

sealed class DeleteUserFailure(failure : MyFailure) : ResultFailure(failure) {
    class InvalidParams(failure: MyFailure) : DeleteUserFailure(failure)
    class RunTimeError(failure: MyFailure) : CreateUserFailure(failure)

}