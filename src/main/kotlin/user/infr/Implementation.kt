package user.infr


import user.bl.UserRepo
import user.bl.entity.*


object Implementation : UserRepo {
    override fun getByUsername(username: Username): User? {
        val user = UserTable.readUserByUserName(username.username)
        if (user == null) return user
        return User(
            Username(user[UserTable.username]),
            Password(user[UserTable.password]),
            FullName(user[UserTable.fullName]),
            Email(user[UserTable.email]),
            )
    }

    override fun getByEmail(email: Email): User? {
        val user = UserTable.readUserByEmail(email.email)
        if (user == null) return user
        return User(
            Username(user[UserTable.username]),
            Password(user[UserTable.password]),
            FullName(user[UserTable.fullName]),
            Email(user[UserTable.email]),
        )
    }

    override fun saveUser(user: user.bl.entity.User): Boolean {
        return UserTable.insertUser(user.username.username,
            user.password.password,
            user.fullName.fullName,
            user.email.email)
    }

    override fun update(username: Username, fullName: FullName, email: Email): Boolean {
        return UserTable.update(username.username,fullName.fullName,email.email)
    }


    override fun delete(username : Username): Boolean {
        return UserTable.delete(username.username)
    }


}
