package user.domain

import user.domain.entity.*


class UserService(private val userRepository: UserRepo) {

    fun exists(username: Username): Boolean {
        return userRepository.getByUsername(username) != null
    }

    fun emailExists(email: Email): Boolean {
        return userRepository.getByEmail(email) != null

    }

    fun create(user: User): Boolean {
        return userRepository.saveUser(user)
    }

    fun passwordIsCorrect(username: Username, password: Password): Boolean {
        val user = userRepository.getByUsername(username)
        if (user != null) {
            return user.password == password
        }
        return false
    }

    fun changeInformation(username: Username, fullName: FullName, email: Email): Boolean {
        return userRepository.update(username, fullName, email)
    }

    fun deleteUser(username: Username): Boolean {
        return userRepository.delete(username)
    }

}