import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

//********************************************************************
//
//Author:        Michael Navarro
//
//Project #:     Three
//
//File Name:     Program3Client.java
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
//Description:   Client for echo server
//
//********************************************************************

public class Program3Client {
    private Socket             serverSocket = null;
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
        Program3Client echoClient = new Program3Client();
        echoClient.developerInfo();
        echoClient.mainLoop();

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
    } // End developerInfo()

    // ***************************************************************
    //
    // Method:      mainLoop (Non Static)
    //
    // Description: Main loop of client
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void mainLoop() {
        try {
            // Connect to server socket
            connToSocket();
            initStreams();

            try (Scanner scanner = new Scanner(System.in)) {
                // Echo loop
                boolean running = true;
                while (running == true) {
                    System.out.print("Please enter message, 'bye' to quit: ");
                    String msgToServer = scanner.nextLine();

                    // Write to server
                    buffWriter.write(msgToServer);
                    buffWriter.newLine(); // Add line terminator
                    buffWriter.flush(); // Flush stream to clear buffer for next msg

                    if (msgToServer.toUpperCase().equalsIgnoreCase("BYE")) {
                        running = false;
                    } else {
                        String msgFromServer = buffReader.readLine();
                        System.out.println("Server: " + msgFromServer);
                        System.out.println();
                    } // End if
                } // End while
            } // End try (resource)
        } catch (IOException e) {
            System.err.println("Failed to init scanner for communication with server.");
            e.printStackTrace();
            System.exit(-1);
        } finally {
            cleanup();
        } // End try

        return;
    } // End mainLoop()

    // ***************************************************************
    //
    // Method:      connToSocket (Non Static)
    //
    // Description: Connect to server socket on specified port
    //              and ip-address
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void connToSocket() {
        try {
            serverSocket = new Socket("127.0.0.1", 4302);
        } catch (IOException e) {
            System.err.println("Failed to connect with server");
            e.printStackTrace();
            System.exit(-1);
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
            inputStream  = new InputStreamReader(serverSocket.getInputStream());
            outputStream = new OutputStreamWriter(serverSocket.getOutputStream());

            buffReader = new BufferedReader(inputStream);
            buffWriter = new BufferedWriter(outputStream);
        } catch (IOException e) {
            System.err.println("Failed to initialize input/output streams");
            e.printStackTrace();
            System.exit(-1);
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
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (buffReader != null) {
                buffReader.close();
            }
            if (buffWriter != null) {
                buffWriter.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to cleanup resources");
            e.printStackTrace();
            System.exit(-1);
        } // End try

        return;
    } // End cleanup()
}
