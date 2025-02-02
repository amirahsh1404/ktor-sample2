package user.infr.httpserver.model.ResultPackage

class UserExceptions(val userExceptionType : UserExceptionType, vararg args: Pair<String,String> ) : Exception(userExceptionType.formatMessage(*args)){

}