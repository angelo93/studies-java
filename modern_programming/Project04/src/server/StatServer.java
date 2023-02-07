import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//********************************************************************
//
//  Developer:     Instructor
//
//  Project #:     Four
//
//  File Name:     StatServer.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/30/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Project 4 server test class.
//                 Instantiates:
//                 * server
//
//********************************************************************

public class StatServer {
   private Socket clientSocket             = null;
   private ServerSocket serverSocket       = null;
   private InputStreamReader inputStream   = null;
   private OutputStreamWriter outputStream = null;
   private BufferedReader buffReader       = null;
   private BufferedWriter buffWriter       = null;

   // ***************************************************************
   //
   // Method: main
   //
   // Description: The main method of the program
   //
   // Parameters: String array
   //
   // Returns: N/A
   //
   // **************************************************************
   public static void main(String[] args) {
      StatServer statServer = new StatServer();
      statServer.developerInfo();
      statServer.runJob();

      return;
   } // End main()

   // ***************************************************************
   //
   // Method: developerInfo (Non Static)
   //
   // Description: The developer information method of the program
   //
   // Parameters: None
   //
   // Returns: N/A
   //
   // **************************************************************
   public void developerInfo() {
      System.out.println("Name:    Michael Navarro-Sanchez");
      System.out.println("Course:  COSC 4301 Modern Programming");
      System.out.println("Project: Four\n\n");
   } // End developerInfo()f

   // ***************************************************************
   //
   // Method: runJob (Non Static)
   //
   // Description: Main job loop of server
   //
   // Parameters: None
   //
   // Returns: N/A
   //
   // **************************************************************
   public void runJob() {
      connToSocket();
      boolean running = true;
      while (running == true) {
         try {
            // Wait for connection
            clientSocket = serverSocket.accept();
            initStreams();
            System.out.println("Client Connection Opened");

            // Keep server running for new connections
            // as per project requirements
            boolean connected = true;
            while (connected == true) {
               String msgFromClient = buffReader.readLine();
               System.out.println("Client: " + msgFromClient);

               // Check for client disconnect
               if (msgFromClient.toUpperCase().equalsIgnoreCase("BYE")) {
                  System.out.println("Client Connection Closed");
                  System.out.println();
                  connected = false;
               } else {
                  List<String> params = getParams(msgFromClient);
                  boolean inputIsValid  = inputIsValid(params);

                  // At this point, we are free to send
                  // stats of number list
                  if (inputIsValid == true) {
                     sendStats(params);
                  } // End if
               } // End if
            } // End while

            // Free resources for next client
            cleanup();
         } catch (IOException e) {
            System.err.println("Failed run job for client");
            e.printStackTrace();
            System.exit(-1);
         } // End try
      }

      return;
   } // End runjob()

   // ***************************************************************
   //
   // Method: connToSocket (Non Static)
   //
   // Description: Connect to socket on specified port
   //
   // Parameters: None
   //
   // Returns: N/A
   //
   // **************************************************************
   public void connToSocket() {
      try {
         serverSocket = new ServerSocket(4301);
      } catch (IOException e) {
         System.err.println("Failed to connect to port for listenting");
         e.printStackTrace();
         System.exit(-1);
      } // End try

      return;
   } // End connToSocket()

   // ***************************************************************
   //
   // Method: initStreams (Non Static)
   //
   // Description: initialize input/output streams and wrap with buffer
   //
   // Parameters: None
   //
   // Returns: N/A
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
         e.printStackTrace();
         System.exit(-1);
      } // End try

      return;
   } // End initStreams()

   // ***************************************************************
   //
   // Method: cleanup (Non Static)
   //
   // Description: Cleanup resources
   //
   // Parameters: None
   //
   // Returns: N/A
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
         e.printStackTrace();
         System.exit(-1);
      } // End try

      return;
   } // End cleanup()

   // ***************************************************************
   //
   // Method: writeToClient (Non Static)
   //
   // Description: Write to client through socket
   //
   // Parameters: String msgToClient
   //
   // Returns: N/A
   //
   // **************************************************************
   public void writeToClient(String msgToClient) {
      try {
         buffWriter.write(msgToClient);
         buffWriter.newLine();
         buffWriter.flush();
      } catch (IOException e) {
         System.err.println("Failed to write to client");
         e.printStackTrace();
         System.exit(-1);
      } // End try

      return;
   } // End writeToClient()


   // ***************************************************************
   //
   // Method: writeToClient (Non Static)
   //
   // Description: Splits up client message into a list of parameters.
   //              Param list must still be parsed and validated
   //
   // Parameters: String msgToClient
   //
   // Returns: List<String> params
   //
   // **************************************************************
   public List<String> getParams(String msgFromClient) {
      // Replace non numeric blocks of chars to empty spaces
      String tempString = msgFromClient.replaceAll("[^0-9]+", " ");
      // Remove trailing/leading spaces, split on empty spaces
      List<String> params = Arrays.asList(tempString.trim().split(" "));

      return params;
   } // End getParams()

   // ***************************************************************
   //
   // Method: inputIsValid (Non Static)
   //
   // Description: Validates input according to specified project
   //              requirements
   //
   // Parameters: List<String> params
   //
   // Returns: boolean valid
   //
   // **************************************************************
   public boolean inputIsValid(List<String> params) {
      boolean valid = true;
      int min       = 0;
      int max       = 0;
      int opt       = 0;

      // Ensure only 3 parameters exist
      if (params.size() != 3) {
         writeToClient("ERROR: Input must be composed of exactly 3 integers.");
         valid = false;
      } // End if

      // Ensure all params are integers
      if (valid == true) {
         // Integer test
         try {
            min = Integer.parseInt(params.get(0));
            max = Integer.parseInt(params.get(1));
            opt = Integer.parseInt(params.get(2));
         } catch (NumberFormatException e) {
            // We do not want to exit to allow client to keep trying
            writeToClient("ERROR: All parameters have to be integers.");
            valid = false;
         } // End try
      } // End if

      // Ensure all integers are greater than 0
      if (valid == true) {
         if ((min == 0) || (max == 0) || (opt == 0)) {
            writeToClient("ERROR: All integers must be greater than 0.");
            valid = false;
         } // End if
      } // End if

      // Ensure first integer is lower than second integer
      if (valid == true) {
         if (min >= max) {
            writeToClient("ERROR: First integer must be lower than the second.");
            valid = false;
         } // End if
      } // End if

      // Ensure third integer is 1 or 3
      if (valid == true) {
         if ((opt != 1) && (opt != 2)) {
            writeToClient("ERROR: The third integer must be 1 or 2.");
            valid = false;
         } // End if
      } // End if

      return valid;
   } // End inputIsValid()

   // ***************************************************************
   //
   // Method: getNumList (Non Static)
   //
   // Description: Builds number list based on client parameters
   //
   // Parameters: int min
   //             int max
   //             int opt
   //
   // Returns: List<Integer> numList
   //
   // **************************************************************
   public List<Integer> getNumList(int min, int max, int opt) {
      List<Integer> numList = new ArrayList<Integer>();

      for (int i = min; i <= max; i++) {
         numList.add(i);
      } // End for

      // Filter numbers
      if (opt == 1) {
         // Filter for odds
         numList = numList.stream().filter(n -> n % 2 != 0).collect(Collectors.toList());
      } else {
         // Filter for evens
         numList = numList.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
      } // End if

      return numList;
   } // End getNumList()

   // ***************************************************************
   //
   // Method: getSum (Non Static)
   //
   // Description: Gets the sum of the number list
   //
   // Parameters: List<Integer> numList
   //
   // Returns: int sum
   //
   // **************************************************************
   public int getSum(List<Integer> numList) {
      return numList.stream().mapToInt(Integer::intValue).sum();
   } // End getSum()

   // ***************************************************************
   //
   // Method: getMean (Non Static)
   //
   // Description: Gets the mean of the number list
   //
   // Parameters: List<Integer> numList
   //
   // Returns: double mean
   //
   // **************************************************************
   public double getMean(List<Integer> numList) {
      return numList.stream().mapToDouble(num -> num).average().orElse(0.0);
   } // End getMean()

   // ***************************************************************
   //
   // Method: getStdDeviation (Non Static)
   //
   // Description: Gets the standard deviation of the number list
   //
   // Parameters: List<Integer> numList
   //
   // Returns: double stdDev
   //
   // **************************************************************
   public double getStdDeviation(List<Integer> numList) {
      // Formula for standard deviation: σ=√((∑(val−mean)^2)/N)
      // variance = (∑(val−mean)^2)/N
      // std dev = √variance
      double variance = numList.stream()
            .map(i -> i - getMean(numList)) // val - mean
            .map(i -> i * i)                // (val - mean)^2
            .mapToDouble(i -> i)            // store as double
            .average()                      // ∑(val−mean)^2)/N
            .getAsDouble();                 // store as double

      double stdDev = Math.sqrt(variance);  // √variance

      // This is the std dev for the population
      // result is different than for a sample
      return stdDev;
   }

   // ***************************************************************
   //
   // Method: sendStats (Non Static)
   //
   // Description: Send statistical info to client
   //
   // Parameters: List<String> params
   //
   // Returns: N/A
   //
   // **************************************************************
   public void sendStats(List<String> params) {
      // Parse input from client
      int min = Integer.parseInt(params.get(0));
      int max = Integer.parseInt(params.get(1));
      int opt = Integer.parseInt(params.get(2));

      List<Integer> numList = getNumList(min, max, opt);

      String msgToClient = String.format("Sum: %d Mean: %.2f Std Dev: %.2f",
            getSum(numList), getMean(numList), getStdDeviation(numList));
      writeToClient(msgToClient);

      return;
   } // End sendStats()
} // Class StatServer()