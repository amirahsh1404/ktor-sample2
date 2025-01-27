package userandcustomerservice.implementation

import business.dataclasses.ParentUser
import implementation.tables.ParentUserTable
import implementation.tables.cargoservicerTable
import implementation.tables.customerTable

var loggedInUser: ParentUser? = null

object Implementation {
    fun createUser(user: ParentUser): String {
        val check: String = userandcustomerservice.implementation.Implementation.createUserCheck(user)
        if (check != "OK!") return check
        if (ParentUserTable.insertUser(user) != null) {
            userandcustomerservice.implementation.loggedInUser = user
            return "user created successfully"
        }
        return "Unknown problem user not created"
    }

    fun createUserCheck(user: ParentUser): String {
        val usernameCheck = userandcustomerservice.implementation.Implementation.checkUserName(user.username)
        if (usernameCheck != "OK!") return usernameCheck
        val passwordCheck = userandcustomerservice.implementation.Implementation.checkPassword(user.password)
        if (passwordCheck != "OK!") return passwordCheck
        val emailAndPhoneNUmberCheck = userandcustomerservice.implementation.Implementation.checkNameAndEmailAndPhone(
            user.name,
            user.email,
            user.phoneNumber
        )
        if (emailAndPhoneNUmberCheck != "OK!") return emailAndPhoneNUmberCheck

        return "Ok!"
    }


    fun checkUserName(username: String): String {
        return when {
            username.isBlank() -> "Username cannot be blank"
            username.length < 3 -> "Username should be at least 3 characters"
            username.length > 30 -> "Username is too long"
            !username.matches(userandcustomerservice.implementation.Regex.username.toRegex()) -> "Username can contain only alphanumeric characters"
            ParentUserTable.readUserByUserName(username) != null -> "User with this username created before"
            else -> "OK!"
        }
    }

    fun checkPassword(password: String): String {
        return when {
            password.isBlank() -> "Password cannot be blank"
            password.length < 6 -> "Password should be at least 6 characters"
            password.length > 40 -> "Password is too long"
            !password.matches(userandcustomerservice.implementation.Regex.password.toRegex()) -> "Password should contain alphanumeric characters and " +
                    "special characters"

            else -> "OK!"
        }
    }

    fun checkNameAndEmailAndPhone(name : String, email: String, phoneNumber: String): String {
        return when {
            name.isBlank() && email.isBlank() &&  phoneNumber.isBlank()-> "Ok!"
            !name.matches(userandcustomerservice.implementation.Regex.name.toRegex()) -> "Name format is wrong"
            !email.matches(userandcustomerservice.implementation.Regex.email.toRegex()) -> "Wrong format for email"
            !phoneNumber.matches(userandcustomerservice.implementation.Regex.phoneNumber.toRegex()) -> "Wrong format for phone number"
            else -> "OK!"


        }
    }


    fun login(user: ParentUser): String {
        val check = userandcustomerservice.implementation.Implementation.loginCheck(user)
        if (check != "OK!") return check
        userandcustomerservice.implementation.loggedInUser = user
        return "logged in successfully"
    }

    fun loginCheck(user: ParentUser): String {
        val userChecked = ParentUserTable.readUserByUserName(user.username)
        return when {
            user.username.isBlank() -> "Username cannot be blank"
            user.password.isBlank() -> "Password cannot be blank"
            userChecked == null -> "User doesnt exist"
            userChecked[ParentUserTable.password] != user.password -> "Wrong password"
            else -> "OK!"
        }
    }


    fun changeRoleToCustomer( nationalCode: String): String {
        if (ParentUserTable.getUserID(userandcustomerservice.implementation.loggedInUser!!.username)?.let { customerTable.readUserById(it) } != null)
            return "user is customer from before!"
        if (nationalCode.isBlank()) return "nationalCode cannot be blank"
        if (!nationalCode.matches(userandcustomerservice.implementation.Regex.nationalCode.toRegex())) return "nationalCode format is wrong"
        if (userandcustomerservice.implementation.loggedInUser?.let { customerTable.insertCustomer(it, userandcustomerservice.implementation.loggedInUser!!.username) } != null)
            return "changed role successfully!"
        return "Unknown problem, user not changed role"
    }

    fun changeRoleToServicer() : String {
        if (ParentUserTable.getUserID(userandcustomerservice.implementation.loggedInUser!!.username)?.let { cargoservicerTable.readUserById(it) } != null)
            return "user is servicer from before!"

        if (userandcustomerservice.implementation.loggedInUser?.let { customerTable.insertCustomer(it, userandcustomerservice.implementation.loggedInUser!!.username) } != null)
            return "changed role successfully!"
        return "Unknown problem, user not changed role"
    }

    fun transferMoney( money : Double): String? {
        return userandcustomerservice.implementation.loggedInUser?.let { ParentUserTable.transferMoney(it.username, money) }
    }

    fun withdrawMoney( money : Double): String? {
        return userandcustomerservice.implementation.loggedInUser?.let { ParentUserTable.withdrawMoney(it.username, money) }
    }

}
