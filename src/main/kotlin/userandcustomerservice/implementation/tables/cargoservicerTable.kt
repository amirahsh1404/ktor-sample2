package userandcustomerservice.implementation.tables

import business.dataclasses.ParentUser
import implementation.tables.customerTable.select
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object cargoservicerTable : Table("cargoservicer") {
    val id = integer("servicerid")

    fun insertCustomer(user : ParentUser, nationalCodeUser: String) : ResultRow? {
        return transaction {
            val idOfUser : Int? = ParentUserTable.getUserID(user.username)
            val idNew = cargoservicerTable.insert {
                if (idOfUser != null) {
                    it[id] = idOfUser
                }
            }  get cargoservicerTable.id
            cargoservicerTable.select { cargoservicerTable.id eq idNew }.singleOrNull()
        }
    }

    fun readUserById(userId: Int): ResultRow? {
        return transaction {
            cargoservicerTable.selectAll().where { cargoservicerTable.id eq userId }.singleOrNull()
        }
    }

}