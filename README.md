ChatSocket & ChatSocketServer

ChatSocketServer: https://github.com/azimsh3r/ChatSocketServer

Overview
ChatSocket is a command-line chat application developed as a test task for an internship. This project utilizes sockets to enable real-time communication between multiple clients. It consists of two main components: the client application and the server application.

Features
Real-time messaging among connected clients.
Command-line interface for easy interaction.
Handles user disconnection and reconnection smoothly.
Messages are formatted in a simple JSON structure.
Getting Started
Prerequisites
Kotlin installed (JDK 11 or later).
Gradle for building the project.
Installation
Clone the repositories:

bash
Copy code
git clone https://github.com/azimsh3r/ChatSocket.git
git clone https://github.com/azimsh3r/ChatSocketServer.git
Navigate to each project directory and build:

bash
Copy code
cd ChatSocket
./gradlew build

cd ../ChatSocketServer
./gradlew build
Running the Server
Navigate to the ChatSocketServer directory:

bash
Copy code
cd ChatSocketServer
Start the server:

bash
Copy code
kotlin -classpath build/libs/ChatSocketServer.jar network.SocketManagerKt
The server will listen on port 1111 by default.

Running the Client
Navigate to the ChatSocket directory:

bash
Copy code
cd ChatSocket
Start the client:

bash
Copy code
kotlin -classpath build/libs/ChatSocket.jar chat.ChatClientKt
Enter your username when prompted and start chatting!

Testing
Open multiple terminal instances to connect several clients to the server.
Send messages to test the real-time functionality.
Use "DISCONNECT" to exit the chat gracefully.
Usage
Type your message and press enter to send.
Enter "EXIT" to close the client application.
Contributing
Contributions are welcome! Feel free to open issues or submit pull requests for improvements or bug fixes.

License
This project is licensed under the MIT License.

Acknowledgments
Thanks to the Kotlin community for the resources and support that helped in developing this project.
