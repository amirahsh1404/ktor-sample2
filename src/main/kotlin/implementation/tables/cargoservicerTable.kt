package implementation.tables

import org.jetbrains.exposed.sql.Table

object cargoservicerTable : Table("cargoservicer") {
    val id = integer("servicerid")


}