package business

import business.dataclasses.ParentUser

interface UserRepository {
    fun createUser(user: ParentUser)
    fun loginUser()
    fun defineRole()
    fun changeRole()
    fun transferMoney()
    fun withdrawMoney()
    fun changeInformation()
}