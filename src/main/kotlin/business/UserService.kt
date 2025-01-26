package business

import business.dataclasses.ParentUser

class UserService : UserRepository {
    override fun createUser(user : ParentUser) {
        createUser(user)
    }

    override fun loginUser() {
        TODO("Not yet implemented")
    }

    override fun defineRole() {
        TODO("Not yet implemented")
    }

    override fun changeRole() {
        TODO("Not yet implemented")
    }

    override fun transferMoney() {
        TODO("Not yet implemented")
    }

    override fun withdrawMoney() {
        TODO("Not yet implemented")
    }

    override fun changeInformation() {
        TODO("Not yet implemented")
    }


}