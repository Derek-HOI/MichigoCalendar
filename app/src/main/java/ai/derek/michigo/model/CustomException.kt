package ai.derek.michigo.model

class CustomException : Exception {

    constructor() : super()
    constructor(msg: String) : super(msg)
    constructor(
        msg: String,
        cause: Throwable,
        httpCode: Int = -1,
        httpMessage: String = "",
        errorBody: String? = null
    ) : super(msg, cause) {
        this.httpCode = httpCode
        this.httpMessage = httpMessage
        this.errorBody = errorBody
    }

    var httpCode: Int = -1
    var httpMessage: String = ""
    var errorBody: String? = null

    override fun toString(): String =
        "cause= ${cause};\nmessage= ${message};\nhttpCode= $httpCode; httpMessage= $httpMessage;"
}
