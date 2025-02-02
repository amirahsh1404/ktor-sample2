package user.domain.entity

data class Email(var email: String?) {


    init {
        require(email.isNullOrBlank()) { "Email should not be blank" }
        require(email!!.length >= 5) { "Email is too short" }
        require(email!!.matches(Regex.email.toRegex())) { "Email format is invalid" }
    }
    /*
    fun createEmail(email: String): String? {
        return when {
            email.length < 5 -> "email is too short"
            !email.matches(Regex.email.toRegex()) -> "email format is invalid"
            else -> null
        }
    }

     */
}