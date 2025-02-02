package user.domain.entity

class User(
    var username: Username,
    var password: Password,
    var fullName: FullName,
    var email: Email,
) {
    constructor(username: String, password: String, fullName: String, email: String) : this(
        Username(username),
        Password(password),
        FullName(fullName),
        Email(email)
    )

}
