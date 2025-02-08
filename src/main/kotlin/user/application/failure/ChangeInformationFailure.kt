package user.application.failure

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure

class ChangeInformationFailure(failure : MyFailure) : ResultFailure(failure) {
    class InvalidParams(failure: MyFailure) : CreateUserFailure(failure)
    class RunTimeError(failure: MyFailure) : CreateUserFailure(failure)
}