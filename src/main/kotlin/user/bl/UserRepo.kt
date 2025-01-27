package user.bl

import user.bl.entity.Email
import user.bl.entity.FullName
import user.bl.entity.User
import user.bl.entity.Username


interface UserRepo{
    fun getByUsername(username: Username): User?
    fun getByEmail(email: Email): User?
    fun saveUser(user: user.bl.entity.User) : Boolean
    fun update(username: Username, fullName: FullName, email: Email) : Boolean
    fun delete(username : Username) : Boolean

}