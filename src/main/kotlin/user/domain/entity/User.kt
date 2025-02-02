package user.domain.entity

data class User(
    val username: Username,
    val password: Password,
    val fullName: FullName,
    val email: Email,
) {
    constructor(username: String, password: String, fullName: String, email: String) : this(
        Username(username),
        Password(password),
        FullName(fullName),
        Email(email)
    )

}
