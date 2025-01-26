package implementation.tables.notnow

import org.jetbrains.exposed.sql.Table

object cityTable : Table("city"){
    val cityID = integer("cityid")
    val cityName = varchar("cityname", 30)
    val priceFactor = double("pricefactor")
}