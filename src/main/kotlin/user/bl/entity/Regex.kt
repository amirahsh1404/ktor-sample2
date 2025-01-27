package user.bl.entity

data object Regex {
    val username = "[a-zA-Z0-9-_]+"
    val password = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])$)"
    val email = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}\$"
    val fullName = "[a-zA-Z0-9]+"
    val nationalCode = "\\d{10}\$"

}