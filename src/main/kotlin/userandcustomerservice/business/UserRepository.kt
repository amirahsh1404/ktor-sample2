package userandcustomerservice.business

import org.h2.engine.User


interface UserRepository {
    fun get(username: String): User?
    fun getByEmail(email: String): User?


    fun createUser(username : String,password: String, name : String,
                   email : String, phoneNumber : String, money : Double) : String
    fun loginUser(username : String, password : String) : String
    fun changeRoleToCustomer(nationalCode : String) : String
    fun changeRoleToServicer() : String
    fun transferMoney( money : Double) : String?
    fun withdrawMoney(money : Double) : String?

}