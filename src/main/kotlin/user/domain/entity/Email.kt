package user.domain.entity

class Email(var email: String) {

    fun createEmail(email: String): String? {
        return when {
            email.length < 5 -> "email is too short"
            !email.matches(Regex.email.toRegex()) -> "email format is invalid"
            else -> null
        }
    }
}