package implementation.tables.notnow

import org.jetbrains.exposed.sql.Table

object servicertocargoTable : Table("servicertocargo") {
    val servicerid = integer("servicerid")
    val cargoId = integer("cargo_id")
}