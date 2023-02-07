import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

//********************************************************************
//
//  Developer:     Instructor
//
//  Project #:     Three
//
//  File Name:     Project3.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/23/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Project3 hierachy test class.
//                 Instantiates:
//                 * StoreItem
//                 * CashRegister
//
//********************************************************************

public class Project3 {
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
      // Create an object of the main class and use it to call
      // the non-static developerInfo method
      Project3 obj = new Project3();
      obj.developerInfo();
      obj.transactionLoop();
      return;
   } // End of the main method

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
      System.out.println("Project: Three\n\n");
   } // End of the developerInfo method

   // ***************************************************************
   //
   // Method: transactionLoop (Non Static)
   //
   // Description: The developer information method of the program
   //
   // Parameters: None
   //
   // Returns: N/A
   //
   // **************************************************************
   public void transactionLoop() {
      Scanner inputScanner = new Scanner(System.in);

      // Setup CashRegister
      List<StoreItem> inventory = parseInventory(inputScanner);
      List<String> cashiers = parseCashiers(inputScanner);
      CashRegister register_01 = new CashRegister(inventory, cashiers);

      // Begin main loop
      int choice = -1;
      boolean running = true;
      while (running == true) {
         // Clear screen
         System.out.print("\033[H\033[2J");
         System.out.flush();

         register_01.displayMenu();

         System.out.printf("%nPlease select an option: ");
         String temp = inputScanner.nextLine();;
         while (!(temp.matches("-?[0-9]+") && temp.length() > 0)) {
            System.out.print("Please enter an integer (negative to quite): ");
            temp = inputScanner.nextLine();
         };
         choice = Integer.parseInt(temp);

         if (choice < 0) {
            running = false;
         } else {
            register_01.processOption(choice, inputScanner);

            // Wait for user
            System.out.printf("%nPress \"ENTER\" to continue...");
            inputScanner.nextLine();
         } // End if
      } // End while

      // Cleanup
      inputScanner.close();
   } // End of the transactionLoop method

   // ***************************************************************
   //
   // Method: parseInventory (Non Static)
   //
   // Description: Parses inventory input file specified by user.
   //
   // Parameters: Scanner inputScanner
   //
   // Returns: List<StoreItem> inventory
   //
   // **************************************************************
   public List<StoreItem> parseInventory(Scanner inputScanner) {
      List<StoreItem> inventory = new ArrayList<StoreItem>();

      // Get filename from user
      System.out.print("Enter inventory filename: ");
      Path path = Paths.get(inputScanner.nextLine());

      try (Stream<String> lines = Files.lines(path)) {
         lines.forEach(line -> {
            String[] info = line.split("\\s+");

            // Parse line from file
            int itemNumber = Integer.parseInt(info[0]);
            String itemDescription = info[1];
            int numUnits = Integer.parseInt(info[2]);
            double price = Double.parseDouble(info[3]);

            // Add item info as new StoreItem instance to collection
            inventory.add(new StoreItem(itemNumber, numUnits, price, itemDescription));
         });
      } catch (IOException e) {
         System.err.println("Failed to read file.");
         e.printStackTrace();
         System.exit(-1);
      } // End try

      return inventory;
   } // End of the parseInventory method

   // ***************************************************************
   //
   // Method: parseCashiers (Non Static)
   //
   // Description: Parses cashiers input file specified by user.
   //
   // Parameters: Scanner inputScanner
   //
   // Returns: List<StoreItem> cashiers
   //
   // **************************************************************
   public List<String> parseCashiers(Scanner inputScanner) {
      List<String> cashiers = new ArrayList<String>();

      // Get filename from user
      System.out.print("Enter cashier filename: ");
      Path path = Paths.get(inputScanner.nextLine());

      try (Stream<String> lines = Files.lines(path)) {
         lines.forEach(line -> cashiers.add(line));
      } catch (IOException e) {
         System.err.println("Failed to read file.");
         e.printStackTrace();
         System.exit(-1);
      } // End try

      return cashiers;
   } // End of the parseCashiers method
}
