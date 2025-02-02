package user.domain.entity

class Username (var username: String){

    fun createUsername(username: String) : String? {
        return when {
            username.length < 3 -> "Username should be at least 3 characters"
            username.length > 15 -> "Username should be at most 15 characters"
            !username.matches(Regex.username.toRegex()) -> "Username contains illegal characters"
            else -> null
        }
    }
}