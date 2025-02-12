package user.croscutting.ResultPackage

class MyFailure(
    val code: String,
    val value: String?,
    var cause : MyFailure?
) {
    constructor(code: String, value : String) : this(
        code = code, value =value , cause = null)
    constructor(code: String, cause: MyFailure) : this(
        code = code, value = "", cause = cause
    )
    constructor(code: String,) : this(
        code = code, value = "", cause = null
    )

    fun addCause(cause: MyFailure) {
        this.cause = cause
    }
}
