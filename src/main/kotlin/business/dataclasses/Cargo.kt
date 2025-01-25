package business.dataclasses

class Cargo(
    var id : Int,
    var name : String,
    var description : String,
    var route : Route,
    var cargoServicer: CargoServicer,
) {


}