package user.domain.entity

data class Email(val value: String) {


    init {
        require(value.length >= 5) { "Email is too short" }
        require(value.matches(Regex.email.toRegex())) { "Email format is invalid" }
    }

}