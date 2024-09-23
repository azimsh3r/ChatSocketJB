package chat

import dto.MessageDTO
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import java.io.*
import java.net.ServerSocket
import java.net.Socket
import java.time.LocalDateTime

class ChatClient {
    private var socket : Socket? = null
    private var inputStreamReader : InputStreamReader? = null
    private var outputStreamWriter: OutputStreamWriter? = null
    private var bufferedWriter : BufferedWriter? = null
    private var bufferedReader: BufferedReader? = null

    private var username : String? = null

    @Volatile
    private var isStopped: Boolean = false

    private fun handleIncomingMessages() {
        var jsonMessage : String?

        while (!isStopped) {
            try {
                jsonMessage = bufferedReader?.readLine()

                if (jsonMessage == null) {
                    break
                }
                val message : MessageDTO = Json.decodeFromString(jsonMessage)

                println("${message.username}: ${message.message} (${message.timestamp})")
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
        bufferedReader?.close()
    }

    private fun handleOutgoingMessages() {
        var message: String?

        while (true) {
            do {
                message = readlnOrNull()
            } while (message == null)

            if (message == "EXIT") {
                isStopped = true;

                bufferedReader?.close()

                bufferedWriter?.write("DISCONNECT" + "\n")
                bufferedWriter?.flush()
                break
            }

            val messageEntity =
                MessageDTO(message, LocalDateTime.now(), username ?: "unknown")

            val jsonElement: JsonElement = Json.encodeToJsonElement(messageEntity)
            val messageDTO = jsonElement.toString()

            bufferedWriter?.write(messageDTO + "\n")
            bufferedWriter?.flush()
        }
    }

    fun connect(port: Int, username: String) {
        this.username = username

        try {
            socket = Socket("localhost", port)

            inputStreamReader = socket?.getInputStream()?.let { InputStreamReader(it) }
            outputStreamWriter = socket?.getOutputStream()?.let { OutputStreamWriter(it) }

            bufferedReader = inputStreamReader?.let { BufferedReader(it) }
            bufferedWriter = outputStreamWriter?.let { BufferedWriter(it) }

            runBlocking {
                val incomingMessagesJob = launch (Dispatchers.IO) {
                    handleIncomingMessages()
                }
                val outgoingMessagesJob = launch (Dispatchers.IO) {
                    handleOutgoingMessages()
                }
                incomingMessagesJob.join()
                outgoingMessagesJob.join()
            }
        } catch (e : Exception) {
            e.printStackTrace()
        } finally {
            try {
                socket?.close()
                inputStreamReader?.close()
                outputStreamWriter?.close()
                bufferedWriter?.close()
                bufferedReader?.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
    }

    fun requestNewChat() : Int? {
        var portNumber : String? = null

        var chatRegisterSocket : Socket? = null
        var inputStreamReader : InputStreamReader? = null
        var bufferedReader: BufferedReader? = null

        try {
            chatRegisterSocket = Socket("localhost", 1111)
            inputStreamReader = InputStreamReader(chatRegisterSocket.getInputStream())
            bufferedReader = BufferedReader(inputStreamReader)

            portNumber = bufferedReader.readLine()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            chatRegisterSocket?.close()
            inputStreamReader?.close()
            bufferedReader?.close()
        }

        println("Chat PortNumber: $portNumber")
        return portNumber?.toInt()
    }

    fun isValid(portNumber: Int): Boolean {
        if (portNumber < 0 || portNumber > 65535) {
            return false
        }

        return try {
            ServerSocket(portNumber).use { false }
        } catch (e: Exception) {
            true
        }
    }
}
