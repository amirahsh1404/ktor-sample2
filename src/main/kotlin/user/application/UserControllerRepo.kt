package user.application

interface UserControllerRepo {
    fun createUser(username: String?, password: String?, fullName: String?, email: String?): Boolean
    fun loginUser(username: String?, password: String?): Boolean
    fun changeInformation(username: String?,fullName: String?,email: String?) : Boolean
    fun deleteUser(username: String?): Boolean
}