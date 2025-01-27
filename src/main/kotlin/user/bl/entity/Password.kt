package user.bl.entity

class Password (var password: String) {

    fun createPassword(password: String) : String? {
        return when {
            password.length < 7 -> "Password should be at least 7 characters"
            password.length > 20 -> "Password should be at most 20 characters"
            !password.matches(Regex.password.toRegex()) -> "Password format is wrong"
            else -> null
        }
    }
}