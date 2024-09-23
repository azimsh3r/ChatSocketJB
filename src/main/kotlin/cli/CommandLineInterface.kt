package cli

import chat.ChatClient

class CommandLineInterface {
    private var portNumber : Int? = null
    private val chatClient = ChatClient()

    fun run() {
        var response : String? = null
        var username: String? = null

        while(username == null) {
            print("Input your username: ")
            username = readlnOrNull()
        }

        while(response != "Y" && response != "n") {
            print("Do you want to create a new chat? [Y/n]: ")
            response = readlnOrNull()
        }

        val createChat : Boolean = response == "Y"

        portNumber = when(createChat) {
            true -> chatClient.requestNewChat()
            false -> requestPortNumber()
        }

        try {
            chatClient.connect(portNumber!!, username)
        } catch (e : NullPointerException) {
            e.printStackTrace()
        }
    }

    private fun requestPortNumber() : Int {
        var port : String?

        do {
            print("Input The Port Number of the Existing Chat: ")
            port = readlnOrNull()
        } while (port?.toIntOrNull() == null || !chatClient.isValid(port.toInt()))

        return port.toInt()
    }
}
