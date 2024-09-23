# ChatSocket

ChatSocketServer: https://github.com/azimsh3r/ChatSocketServer

ChatSocket is a command-line application that enables real-time messaging using socket communication. This project consists of two parts: the ChatSocket server and the ChatSocket client.

## Overview

- **Server:** Listens for incoming connections from clients and facilitates message broadcasting.
- **Client:** Connects to the server, sends messages, and receives messages from other clients.

## Features

- Real-time messaging
- Multiple client connections
- Simple command-line interface
- Supports user disconnection

## Requirements

- Kotlin 1.x
- JDK 8 or higher

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/azimsh3r/ChatSocket.git
    git clone https://github.com/azimsh3r/ChatSocketServer.git
    ```

2. Navigate to the project directory:
    ```bash
    cd ChatSocket
    ```

3. Build the project using Gradle:
    ```bash
    ./gradlew build
    ```

## Running the Server

1. Navigate to the server directory:
    ```bash
    cd ChatSocketServer
    ```

2. Run the server:
    ```bash
    kotlin -cp build/libs/ChatSocketServer.jar network.SocketManagerKt
    ```

## Running the Client

1. Navigate to the client directory:
    ```bash
    cd ChatSocket
    ```

2. Run the client:
    ```bash
    kotlin -cp build/libs/ChatSocket.jar network.ChatClientKt
    ```

3. Follow the on-screen prompts to enter your username and start chatting!

## Usage

- Type your messages in the client and hit enter to send.
- Type `DISCONNECT` to leave the chat.

## Contributing

If you'd like to contribute to this project, please fork the repository and submit a pull request with your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.

## Acknowledgments

- [Kotlin](https://kotlinlang.org/) - The programming language used.
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - For handling asynchronous tasks.

## Contact

For any inquiries, please reach out to [your-email@example.com].
