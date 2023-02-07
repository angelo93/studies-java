import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//********************************************************************
//
//  Developer:     Instructor
//
//  Project #:     Five
//
//  File Name:     PrimeServer.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      10/07/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Project 5 prime test class.
//                 Instantiates:
//                 * PrimeServer
//
//********************************************************************

public class PrimeServer {
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
      PrimeServer primeServer = new PrimeServer();
      primeServer.developerInfo();
      primeServer.runJob();

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
      System.out.println("Project: Five\n\n");
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
            System.out.println("<SERVER> Client Connection Opened");

            // Keep server running for new connections
            // as per project requirements
            boolean connected = true;
            while (connected == true) {
               String msgFromClient = buffReader.readLine();
               System.out.println("<Client> " + msgFromClient);

               // Check for client disconnect
               if (msgFromClient.toUpperCase().equalsIgnoreCase("BYE")) {
                  System.out.println("<SERVER> Client Connection Closed");
                  System.out.println();
                  connected = false;
               } else {
                  List<String> params  = getParams(msgFromClient);
                  boolean inputIsValid = inputIsValid(params);

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

      // Ensure only 3 parameters exist
      if (params.size() != 2) {
         writeToClient("ERROR: Input two integers or 'Bye' to quit");
         valid = false;
      } // End if

      // Ensure all params are integers
      if (valid == true) {
         // Integer test
         try {
            min = Integer.parseInt(params.get(0));
            max = Integer.parseInt(params.get(1));
         } catch (NumberFormatException e) {
            // We do not want to exit to allow client to keep trying
            writeToClient("ERROR: All parameters have to be integers.");
            valid = false;
         } // End try
      } // End if

      // Ensure all integers are greater than 0
      if (valid == true) {
         if ((min == 0) || (max == 0)) {
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

      return valid;
   } // End inputIsValid()

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
   public long getSum(List<Integer> numList) {
      return numList.stream().mapToLong(Integer::intValue).sum();
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
   } // End getStdDeviation()

   // ***************************************************************
   //
   // Method: getPrimes (Non Static)
   //
   // Description: Filter the prime numbers out and add to thread safe list
   //
   // Parameters: int min
   //             int max
   //
   // Returns: List<Integer> primeNums
   //
   // **************************************************************
   public List<Integer> getPrimes(int min, int max) {
      int coreCount                   = Runtime.getRuntime().availableProcessors();
      ExecutorService executorService = Executors.newFixedThreadPool(coreCount);

      // Build up number list and filter out prime numbers
      List<Integer> primeNums = Collections.synchronizedList(new ArrayList<>());

      for (int i = min; i <= max; i++) {
         int num = i;
         // Spawn threads with anonymous function
         executorService.execute(() -> {
            // New instance of PrimeTest per thread,
            // avoids threads waiting on using one class instance
            PrimeTest tester = new PrimeTest();
            boolean isPrime  = tester.isPrime(num);

            if (isPrime == true) {
               primeNums.add(num);
            } // end if
         });
      } // end for

      // Wait for threads, handle process taking too long
      executorService.shutdown();
      boolean tasksEnded = true;
      try {
         tasksEnded = executorService.awaitTermination(5, TimeUnit.MINUTES);
         if (tasksEnded == false) {
            // Force shutdown to avoid hanging
            executorService.shutdownNow();
         } // end if
      } catch (InterruptedException ex) {
         tasksEnded = false;
      } // end try

      if (tasksEnded == false) {
         // Task failed to finish in time,
         // clear any numbers in list, set to 0
         primeNums.clear();
         primeNums.add(0);
         writeToClient("Process timeout, please enter a smaller range.");
      } // end if

      return primeNums;
   } // End getPrimes()

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
      // Get list of prime numbers within min and max, inclusive
      int min = Integer.parseInt(params.get(0));
      int max = Integer.parseInt(params.get(1));
      List<Integer> primeNums = getPrimes(min, max);

      // Build up and send message to client
      String msgToClient = String.format("Sum: %d Mean: %.2f Std Dev: %.2f Prime Numbers: ",
            getSum(primeNums), getMean(primeNums), getStdDeviation(primeNums));
      int count = 0;
      for (Integer primeNum : primeNums) {
         count++;
         String temp = "";
         if (count < primeNums.size()) {
            temp = String.format("%s, ", Integer.toString(primeNum));
         } else {
            temp = String.format("%s", Integer.toString(primeNum));
         } // end if
         msgToClient = msgToClient + temp;
      } // end for

      writeToClient(msgToClient);

      return;
   } // End sendStats()
} // Class PrimeServer()