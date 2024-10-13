package exception

class StatusResponseMatchersException(expectedCode: Int, receivedCode: Int) :
    ResponseMatchersException(
        "Возвращаемый код не равен ожидаемому: ожидаемый - $expectedCode," +
            " возвращаемый - $receivedCode",
    )
