package user.application.failure

import user.croscutting.ResultPackage.MyFailure
import user.croscutting.ResultPackage.ResultFailure

sealed class DeleteUserFailure(failure : MyFailure) : ResultFailure(failure) {
    class InvalidParamsFailure : DeleteUserFailure(MyFailure("InvalidParams"))
    class UserNotFoundFailure(cause : MyFailure) : DeleteUserFailure(MyFailure("UserNotFound",cause))


    // la lalala la laaa
    // babeiiiiiiiii
    //sdafdfa
    //asdfsdf
    ////rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
// r

    //asdfasdf
    //adsfasdf

    //asdfasdf
    ////rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    //eeeeeeee
    //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
}