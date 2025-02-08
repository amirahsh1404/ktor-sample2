package user.domain.repository

import user.domain.aggregate.user.entity.Email
import user.domain.aggregate.user.entity.FullName
import user.domain.aggregate.user.User
import user.domain.aggregate.user.entity.Username


interface UserRepo{
    fun getByUsername(username: Username): User?
    fun getByEmail(email: Email): User?
    fun saveUser(user: User) : Boolean
    fun update(username: Username, fullName: FullName, email: Email) : Boolean
    fun delete(username : Username) : Boolean

}