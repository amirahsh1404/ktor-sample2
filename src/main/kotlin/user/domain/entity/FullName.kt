package user.domain.entity

data class FullName(val value: String) {

    init {
        require(value.length >= 5) { "Full name should be at least 5 characters" }
        require(value.length <= 15) { "Full name should be at most 15 characters" }
        require(value.matches(Regex.fullName.toRegex())) { "Full name contains illegal characters" }
    }

}