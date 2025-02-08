package user.croscutting.ResultPackage.unused

data object Regex {
    val username = "[a-zA-Z0-9-_]+".toRegex()
    val password = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])".toRegex()
    val email = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[A-Za-z]+".toRegex()

    val fullName = "[a-zA-Z]+".toRegex()

}