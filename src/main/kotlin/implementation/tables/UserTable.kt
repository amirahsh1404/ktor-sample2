package implementation.tables

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object UsersTable : Table("users") {

    val id = integer("user_id").autoIncrement()
    val username = varchar("username", 255)
    val nationalCode = long("national_code")
    val birthDate = varchar("birth_date", 255).nullable()
    var active = bool("active_sign").default(false)

    override val primaryKey = PrimaryKey(id)

//    fun insert(user: UserInit) : String {
//        return transaction {
//            val id = insert {
//                it[username] = user.username
//                it[nationalCode] = user.nationalCode
//                it[birthDate] = user.birthDate
//                it[active] = user.active
//            } get UsersTable.id
//            UsersTable.select { UsersTable.id eq id}.singleOrNull()
//                ?.let { UserFinal.convertToJson(it)}
//                ?: "Not been able to create new user"
//        }
//    }

    private fun readUserById(userId: Int): ResultRow? {
        return transaction {
            UsersTable.select { UsersTable.id eq userId }.singleOrNull()
        }
    }

    fun getUserById(userId: Int): String {
        val user = readUserById(userId)
        if (user != null) {
            return UserFinal.convertToJson(user)

        } else {
            return "User with ID $userId not found."
        }

    }

    private fun getActiveUsers(): List<ResultRow> {
        return transaction {
            UsersTable.select { active eq true }.toList()
        }
    }

    private fun getNonActiveUsers(): List<ResultRow> {
        return transaction {
            UsersTable.select { active eq false }.toList()
        }
    }

    fun activeUsers(activeOrNotActive: Boolean): String {
        val users: List<ResultRow>
        if (activeOrNotActive)
            users = getActiveUsers()
        else users = getNonActiveUsers()
        if (!users.isEmpty()) {
            return UserFinal.convertListToJson(users)
        } else {
            if (activeOrNotActive)
                return "there are no active users"
            else
                return "there are no not active users"

        }

    }

    fun changeActivation(userID: Int): String {
        val user = readUserById(userID)
        return transaction{
            if (user != null) {
                if (!user[active]) {
                    UsersTable.update({ UsersTable.id eq user[UsersTable.id] }) {
                        it[active] = true
                    }
                    "User activated"

                } else {
                    UsersTable.update({ UsersTable.id eq user[UsersTable.id] }) {
                        it[active] = false
                    }
                    "User  deActivated"
                }
            } else {
                "User not found."
            }
        }
    }
}