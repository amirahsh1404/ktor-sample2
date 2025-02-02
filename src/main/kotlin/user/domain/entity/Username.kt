package user.domain.entity

data class Username (val value: String){


    init {
        require(value.length >= 3) { "Username should be at least 3 characters" }
        require(value.length <= 15) { "Username should be at most 15 characters" }
        require(value.matches(Regex.username.toRegex())) { "Username contains illegal characters" }
    }

}