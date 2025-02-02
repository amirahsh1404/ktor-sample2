package user.domain.entity

data class Password(val value: String) {

    init {
        require(value.length >= 7) { "Password should be at least 7 characters" }
        require(value.length <= 20) { "Password should be at most 20 characters" }
        require(value.matches(Regex.password.toRegex())) { "Password format is wrong" }
    }


}