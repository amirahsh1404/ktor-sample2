package user.infr.repo

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import user.domain.repository.UserRepo
import user.domain.aggregate.user.entity.Email
import user.domain.aggregate.user.entity.FullName
import user.domain.aggregate.user.entity.User
import user.domain.aggregate.user.entity.Username
import user.infr.repo.pm.UserTable

class UserRepositoryImpl : UserRepo {
    override fun getByUsername(username: Username): User? {

        val user : ResultRow? = transaction {
            UserTable.select { UserTable.username eq username.value}.singleOrNull()
        }
        if (user == null) return user
        return User(
            user[UserTable.username],
            user[UserTable.password],
            user[UserTable.fullName],
            user[UserTable.email],
            )
    }

    override fun getByEmail(email: Email): User? {
        val user : ResultRow? = transaction {
            UserTable.selectAll().where { UserTable.email eq email.value }.singleOrNull()
        }
        if (user == null) return user
        return User(
            user[UserTable.username],
            user[UserTable.password],
            user[UserTable.fullName],
            user[UserTable.email],
        )
    }

    override fun saveUser(user: User): Boolean {
        transaction {
            UserTable.insert {
                it[username] = user.username.value
                it[password] = user.password.value
                it[fullName] = user.fullName.value
                it[email] = user.email.value
            }
        }
        return true
    }

    override fun update(username: Username, fullName: FullName, email: Email): Boolean {
        transaction {
            UserTable.update({ UserTable.username eq username.value}) {
                it[UserTable.fullName] = fullName.value
                it[UserTable.email] = email.value
            }
        }
        return true
    }

    override fun delete(username : Username): Boolean {
        transaction {
            UserTable.deleteWhere { UserTable.username eq username.value }
        }
        return true    }


}
