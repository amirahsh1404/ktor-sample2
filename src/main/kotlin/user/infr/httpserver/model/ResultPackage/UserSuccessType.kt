package user.infr.httpserver.model.ResultPackage

enum class UserSuccessType(val message: String) {
    CREATE_SUCCESS("user created successfully"),
    DELETE_SUCCESS("user deleted successfully"),
    LOGIN_SUCCESS("user logged successfully"),
    CHANGE_INFO_SUCCESS("changed info successfully"),
}