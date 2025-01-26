package business.dataclasses


class CargoServicer(
    override var username: String,
    override var password: String,
    override var name: String,
    override var email: String,
    override var phoneNumber: String,
    override var money: Double,
    var cargoList: MutableList<Cargo>,

    ) : ParentUser(username, password, name, email, phoneNumber, money) {
}