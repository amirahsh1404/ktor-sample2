package user.domain.aggregate.user.entity

import user.domain.aggregate.user.entity.valueObjects.Email
import user.domain.aggregate.user.entity.valueObjects.FullName
import user.domain.aggregate.user.entity.valueObjects.Password
import user.domain.aggregate.user.entity.valueObjects.Username

data class User private constructor(
    val username: Username,
    val password: Password,
    val fullName: FullName,
    val email: Email,
) {

    companion object {
        fun makeNew(username: Username, password: Password, fullName: FullName, email: Email): User {
            return User(username = username, password = password, fullName = fullName, email = email)
        }

        fun constructFromPersisted(username: String, password: String, fullName: String, email: String):
                User {
            return User(
                username = Username.constructFromPersisted(username),
                password = Password.constructFromPersisted(password),
                fullName = FullName.constructFromPersisted(fullName),
                email = Email.constructFromPersisted(email)
            )
        }
    }

}
