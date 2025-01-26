package implementation.tables

import org.jetbrains.exposed.sql.Table

object customerTable : Table("customer") {
    val id = integer("customerid")
    val nationalCode = varchar("nationalcode",15)

}