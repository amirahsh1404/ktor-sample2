package user.domain.repository

import user.domain.aggregate.user.entity.valueObjects.Email
import user.domain.aggregate.user.entity.valueObjects.FullName
import user.domain.aggregate.user.entity.User
import user.domain.aggregate.user.entity.valueObjects.Username


interface UserRepo{
    fun getByUsername(username: Username): User?
    fun getByEmail(email: Email): User?
    fun saveUser(user: User) : Boolean
    fun update(username: Username, fullName: FullName, email: Email) : Boolean
    fun delete(username : Username) : Boolean

}