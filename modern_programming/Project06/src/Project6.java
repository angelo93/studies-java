import java.util.Scanner;

//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     Six
//
//  File Name:     Project6.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      10/24/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Project6 hierachy test class.
//
//********************************************************************

public class Project6 {
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
      Project6 obj = new Project6();
      obj.developerInfo();
      obj.jobLoop();
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
      System.out.println("Project: Six\n\n");
   } // End developerInfo()

   // ***************************************************************
   //
   // Method: jobLoop (Non Static)
   //
   // Description: Main job loop
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void jobLoop() {
      Scanner inputScanner = new Scanner(System.in);

      System.out.println("This program allows you to create a fraction to do arithmetic with.");
      System.out.println("To exit the program, enter 0 for both numerator and denominator when asked.");

      System.out.printf("%nPlease enter a numerator (integer): ");
      int numerator = getInt(inputScanner);
      System.out.printf("%nPlease enter a denominator (integer): ");
      int denominator = getInt(inputScanner);

      if (numerator != 0 || denominator != 0) {
         try {
            Fraction main_frac = new Fraction(numerator, denominator);
            int choice         = 0;
            boolean running    = true;

            waitForUser(inputScanner);
            while (running == true) {
               // Clear screen
               System.out.print("\033[H\033[2J");
               System.out.flush();

               // Get user choice
               displayMenu();
               System.out.printf("Please select a choice: ");
               choice = getInt(inputScanner);
               boolean exit = processOption(choice, inputScanner, main_frac);

               if (exit == true) {
                  running = false;
               } else {
                  waitForUser(inputScanner);
               } // End if
            } // End while
         } catch (IllegalArgumentException e) {
            System.out.println("Denominator can NOT be 0");
         } // End try
      } else {
         System.out.println("Exiting Program");
      } // End if

      inputScanner.close();
      return;
   } // End jobLoop()

   // ***************************************************************
   //
   // Method: getInt (Non Static)
   //
   // Description: Get int from user input
   //
   // Parameters: Scanner inputScanner
   //
   // Returns: int val
   //
   // **************************************************************
   public int getInt(Scanner inputScanner) {
      String temp = inputScanner.nextLine();

      while (!(temp.matches("-?[0-9]+") && temp.length() > 0)) {
         System.out.print("Please enter an integer: ");
         temp = inputScanner.nextLine();
      } // End while
      int val = Integer.parseInt(temp);

      return val;
   } // End getInt()

   // ***************************************************************
   //
   // Method: waitForUser (Non Static)
   //
   // Description: Waits for user input
   //
   // Parameters: Scanner inputScanner
   //
   // Returns: N/A
   //
   // **************************************************************
   public void waitForUser(Scanner inputScanner) {
      // Wait for user
      System.out.printf("%nPress \"ENTER\" to continue...");
      inputScanner.nextLine();

      return;
   } // End waitForUser()

   // ***************************************************************
   //
   // Method: displayMenu (Non Static)
   //
   // Description: Displays main menu with the following options:
   // * Each item name from inventory input.
   // * Show Cash Register
   // * Clear Cash Register
   // * Show Inventory
   // * Check Out
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void displayMenu() {
      System.out.println("=======================================");
      System.out.println("|              Main Menu              |");
      System.out.println("=======================================");
      System.out.println("To exit the program, enter 0 for both");
      System.out.println("numerator and denominator when asked.");
      System.out.println();
      System.out.println("1) Add two Fractions");
      System.out.println("2) Subtract two Fractions");
      System.out.println("3) Multiply two Fractions");
      System.out.println("4) Divide two Fractions");
      System.out.println("5) Print the Fraction");
      System.out.println();

      return;
   } // End displayMenu()

   // ***************************************************************
   //
   // Method: processOption (Non Static)
   //
   // Description: Proccess and execute user option.
   //
   // Parameters: int option
   //
   // Returns: N/A
   //
   // **************************************************************
   public boolean processOption(int option, Scanner inputScanner, Fraction main_frac) {
      boolean exit = false;

      if (option <= 4 && option >= 1) {
         System.out.printf("%nPlease enter a numerator (integer): ");
         int numerator = getInt(inputScanner);
         System.out.printf("%nPlease enter a denominator (integer): ");
         int denominator = getInt(inputScanner);

         if (numerator == 0 && denominator == 0) {
            exit = true;
         } else {
            try {
               Fraction temp_frac = new Fraction(numerator, denominator);

               if (option == 1) {
                  main_frac.addFraction(temp_frac);
               } else if (option == 2) {
                  main_frac.subtractFraction(temp_frac);
               } else if (option == 3) {
                  main_frac.multiplyFraction(temp_frac);
               } else {
                  main_frac.divideFraction(temp_frac);
               } // End if
            } catch (IllegalArgumentException e){
               System.out.println("Denominator can NOT be 0");
            } // End try
         }
      } else if (option == 5) {
         main_frac.displayImproper();
      } else {
         System.out.println("Invalid option.");
      } // End if

      return exit;
   } // End processOption()
}
