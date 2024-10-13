package response

class Client {
    fun perform(code: Int, body: String?) = ResponseActions(Response(code, body))
}
