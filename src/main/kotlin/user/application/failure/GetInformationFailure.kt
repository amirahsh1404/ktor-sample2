package user.application.failure

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure

sealed class GetInformationFailure(failure : MyFailure) : ResultFailure(failure) {
    class InvalidParams(failure: MyFailure) : GetInformationFailure(failure)
    class RunTimeError(failure: MyFailure) : CreateUserFailure(failure)

}