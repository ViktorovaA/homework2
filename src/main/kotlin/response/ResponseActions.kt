package response

import exception.BodyResponseMatchersException
import exception.StatusResponseMatchersException

class ResponseActions(val response: Response) {

    inner class StatusResponseMatchers {
        fun isOk() { if (response.code != 200) throw StatusResponseMatchersException(200, response.code) }

        fun isBadRequest() { if (response.code != 400) throw StatusResponseMatchersException(400, response.code) }

        fun isInternalServerError() { if (response.code != 500)
            throw StatusResponseMatchersException(500, response.code) }
    }

    inner class BodyResponseMatchers {
        fun isNull() { if (response.body != null) throw BodyResponseMatchersException("Тело запроса не пусто") }

        fun isNotNull() { if (response.body == null) throw BodyResponseMatchersException("Тело запроса пусто") }
    }

    inner class ResponseMatchers {
        fun status(func: StatusResponseMatchers.() -> Unit) {
            val statusResponseMatchers = StatusResponseMatchers()
            statusResponseMatchers.func()
        }

        fun body(func: BodyResponseMatchers.() -> Unit) {
            val bodyResponseMatchers = BodyResponseMatchers()
            bodyResponseMatchers.func()
        }
    }

    fun andDo(func: (Response) -> Unit): ResponseActions {
        func(response)
        return this
    }

    fun andExpect(func: ResponseMatchers.() -> Unit): ResponseActions {
        val responseMatchers = ResponseMatchers()
        responseMatchers.func()
        return this
    }
}
