package user.bl.entity

class FullName(var fullName: String) {

    fun createFullName(fullName: String): String? {
        return when {
            fullName.length < 3 -> "FullName should be at least 5 characters"
            fullName.length > 15 -> "FullName should be at most 15 characters"
            !fullName.matches(Regex.fullName.toRegex()) -> "FullName contains illegal characters"
            else -> null
        }
    }
}