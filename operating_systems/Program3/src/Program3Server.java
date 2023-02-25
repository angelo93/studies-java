import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

//********************************************************************
//
//Author:        Michael Navarro
//
//Project #:     Three
//
//File Name:     Program3Server.java
//
//Course:        COSC 4302 Operating Systems
//
//Due Date:      02/24/2023
//
//Instructor:    Prof. Fred Kumi
//
//Java Version:  17.0.6
//
//Chapter:       3.8.1
//
//Description:   Server echoes client message
//
//********************************************************************

public class Program3Server {
    private Socket             clientSocket = null;
    private ServerSocket       serverSocket = null;
    private InputStreamReader  inputStream  = null;
    private OutputStreamWriter outputStream = null;
    private BufferedReader     buffReader   = null;
    private BufferedWriter     buffWriter   = null;

    // ***************************************************************
    //
    // Method:      main
    //
    // Description: The main method of the program
    //
    // Parameters:  String array
    //
    // Returns:     N/A
    //
    // **************************************************************
    public static void main(String[] args) {
        Program3Server statServer = new Program3Server();
        statServer.developerInfo();
        statServer.mainLoop();

        return;
    } // End main()

    // ***************************************************************
    //
    // Method:      developerInfo (Non Static)
    //
    // Description: The developer information method of the program
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void developerInfo() {
        System.out.println("Name:    Michael Navarro");
        System.out.println("Course:  COSC 4302 Operating Systems");
        System.out.println("Program: Three\n");

        return;
    } // End developerInfo()f

    // ***************************************************************
    //
    // Method:      mainLoop (Non Static)
    //
    // Description: Main loop of server
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void mainLoop() {
        connToSocket();
        int     numClients = 0;
        boolean running    = true;
        while (running == true) {
            try {
                // Wait for connection
                clientSocket = serverSocket.accept();
                initStreams();
                System.out.println("Client Connection Opened");

                // Keep server running for new connections
                // as per project requirements
                boolean connected = true;
                if (connected)
                    numClients++;
                while (connected == true) {
                    String msgFromClient = buffReader.readLine();
                    System.out.printf("Client[%d]: %s\n", numClients, msgFromClient);

                    boolean isDisconnected = clientDisconnected(msgFromClient);
                    if (isDisconnected) {
                        connected = false;
                    } else {
                        // Echo client
                        writeToClient(msgFromClient);
                    } // End if
                } // End while

                // Free resources for next client
                cleanup();
            } catch (IOException e) {
                System.err.println("Failed to run job for client");
            } // End try
        }

        return;
    } // End mainLoop()

    // ***************************************************************
    //
    // Method:      connToSocket (Non Static)
    //
    // Description: Connect to socket on specified port
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void connToSocket() {
        try {
            serverSocket = new ServerSocket(4302);
        } catch (IOException e) {
            System.err.println("Failed to connect to port for listenting");
        } // End try

        return;
    } // End connToSocket()

    // ***************************************************************
    //
    // Method:      initStreams (Non Static)
    //
    // Description: initialize input/output streams and wrap with buffer
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void initStreams() {
        try {
            // Setup R/W streams
            inputStream  = new InputStreamReader(clientSocket.getInputStream());
            outputStream = new OutputStreamWriter(clientSocket.getOutputStream());

            // Wrap in buffers
            buffReader = new BufferedReader(inputStream);
            buffWriter = new BufferedWriter(outputStream);
        } catch (IOException e) {
            System.err.println("Failed to initialize input/output streams");
        } // End try

        return;
    } // End initStreams()

    // ***************************************************************
    //
    // Method:      cleanup (Non Static)
    //
    // Description: Cleanup resources
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void cleanup() {
        // Attempt to close client resources
        try {
            clientSocket.close();
            inputStream.close();
            outputStream.close();
            buffReader.close();
            buffWriter.close();
        } catch (IOException e) {
            System.err.println("Failed to cleanup resources");
        } // End try

        return;
    } // End cleanup()

    // ***************************************************************
    //
    // Method:      writeToClient (Non Static)
    //
    // Description: Write to client through socket
    //
    // Parameters:  String msgToClient
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void writeToClient(String msgToClient) {
        try {
            buffWriter.write(msgToClient);
            buffWriter.newLine();
            buffWriter.flush();
        } catch (IOException e) {
            System.err.println("Failed to write to client");
        } // End try

        return;
    } // End writeToClient()

    // ***************************************************************
    //
    // Method:      clientDisconnected (Non Static)
    //
    // Description: Check if client disconnected
    //
    // Parameters:  String msgFromClient
    //
    // Returns:     Boolean isDisconnected
    //
    // **************************************************************
    public boolean clientDisconnected(String msgFromClient) {
        boolean isDisconnected = false;
        if (msgFromClient == null) {
            // ctrl + c or some other abrupt disconnect
            System.out.println("Client Connection Closed Abrubtly");
            System.out.println();
            isDisconnected = true;
        } else if (msgFromClient.toUpperCase().equalsIgnoreCase("BYE")) {
            // clean disconnect
            System.out.println("Client Connection Closed");
            System.out.println();
            isDisconnected = true;
        } // end if

        return isDisconnected;
    } // End clientDisconnected()
} // Class StatServer()