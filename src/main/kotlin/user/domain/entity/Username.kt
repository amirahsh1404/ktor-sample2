package user.domain.entity

data class Username (var username: String?){


    init {
        require(username.isNullOrBlank()) { "Username should not be blank" }
        require(username!!.length >= 3) { "Username should be at least 3 characters" }
        require(username!!.length <= 15) { "Username should be at most 15 characters" }
        require(username!!.matches(Regex.username.toRegex())) { "Username contains illegal characters" }
    }

}