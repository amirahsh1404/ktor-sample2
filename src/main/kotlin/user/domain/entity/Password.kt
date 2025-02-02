package user.domain.entity

data class Password(var password: String?) {

    init {
        require(password.isNullOrBlank()) { "Password should not be blank" }
        require(password!!.length >= 7) { "Password should be at least 7 characters" }
        require(password!!.length <= 20) { "Password should be at most 20 characters" }
        require(password!!.matches(Regex.password.toRegex())) { "Password format is wrong" }
    }


}