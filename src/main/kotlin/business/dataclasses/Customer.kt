package business.dataclasses

data class Customer(
    override var username: String,
    override var password : String,
    override var name: String,
    override var email : String,
    override var phoneNumber : String,
    override var money: Double,
    var cargoList: MutableList<Cargo>,
    var nationalCOde : String

    ) : ParentUser(username, password, name, email, phoneNumber, money){

}