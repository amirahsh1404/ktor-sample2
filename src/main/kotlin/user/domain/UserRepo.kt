package user.domain

import user.domain.entity.Email
import user.domain.entity.FullName
import user.domain.entity.User
import user.domain.entity.Username


interface UserRepo{
    fun getByUsername(username: Username): User?
    fun getByEmail(email: Email): User?
    fun saveUser(user: User) : Boolean
    fun update(username: Username, fullName: FullName, email: Email) : Boolean
    fun delete(username : Username) : Boolean

}