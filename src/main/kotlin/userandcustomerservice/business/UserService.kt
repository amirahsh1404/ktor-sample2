package userandcustomerservice.business

import business.dataclasses.ParentUser
import userandcustomerservice.implementation.Implementation as Im

class UserService : UserRepository {
    override fun createUser(
        username: String, password: String, name: String,
        email: String, phoneNumber: String, money: Double

    ): String {

        return Im.createUser(ParentUser(username, password, name, email, phoneNumber, money))
    }

    override fun loginUser(username: String, password: String): String {
        return Im.login(ParentUser(username, password, "", "", "", 0.0))
    }

    override fun changeRoleToCustomer(nationalCode: String): String {
        return Im.changeRoleToCustomer(nationalCode)
    }

    override fun changeRoleToServicer(): String {
        return Im.changeRoleToServicer()
    }

    override fun transferMoney(money: Double): String? {
        return Im.transferMoney(money)
    }

    override fun withdrawMoney(money: Double): String? {
        return Im.withdrawMoney(money)
    }



}