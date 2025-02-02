package user.domain.entity

data class FullName(var fullName: String?) {

    init {
        require(!fullName.isNullOrBlank()) { "FullName must not be blank" }
        require(fullName!!.length >= 5) { "Full name should be at least 5 characters" }
        require(fullName!!.length <= 15) { "Full name should be at most 15 characters" }
        require(fullName!!.matches(Regex.fullName.toRegex())) { "Full name contains illegal characters" }
    }

}