import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     eight
//
//  File Name:     Project8.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      11/07/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Project8 hierachy test class.
//
//********************************************************************

public class Project8 {
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
      Project8 obj = new Project8();
      obj.developerInfo();
      obj.menuLoop();

      return;
   } // end main()

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
      System.out.println("Project: Eight\n\n");

      return;
   } // end developerInfo()

   // ***************************************************************
   //
   // Method: menuLoop (Non Static)
   //
   // Description: Main menu loop
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void menuLoop() {
      int choice              = 0;
      Scanner scanner         = new Scanner(System.in);
      List<GasRecord> records = parseRecords(scanner);

      boolean running = true;
      while (running == true) {
         // Clear screen
         System.out.print("[H[2J");
         System.out.flush();

         // Get user choice
         displayMenu();
         System.out.printf("Please select a choice: ");
         choice       = getInt(scanner);
         boolean exit = processOption(choice, records);

         if (exit == true) {
            running = false;
         } else {
            waitForUser(scanner);
         } // end if
      } // end while

      return;
   } // end menuLoop()

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
      } // end while
      int val = Integer.parseInt(temp);

      return val;
   } // end getInt()

   // ***************************************************************
   //
   // Method: parseRecords (Non Static)
   //
   // Description: Parses file for records of dates and gas prices
   //
   // Parameters: Scanner scanner
   //
   // Returns: List<GasRecord> records
   //
   // **************************************************************
   public List<GasRecord> parseRecords(Scanner scanner) {
      List<GasRecord> records = new ArrayList<GasRecord>();

      // Get filename from user
      System.out.println("Required data format: 'MM-DD-YYYY:Price'");
      System.out.print("Enter record filename: ");
      Path path = Paths.get(scanner.nextLine());

      try (Stream<String> lines = Files.lines(path)) {
         lines.forEach(line -> {
            // Parse each line of file
            String[] info = line.split(":");
            String date   = info[0];
            double price  = Double.parseDouble(info[1]);

            // Add new record to list
            records.add(new GasRecord(date, price));
         });
      } catch (IOException e) {
         System.err.println("Failed to read file.");
         waitForUser(scanner);
      } // end try

      return records;
   } // end parseRecords()

   // ***************************************************************
   //
   // Method: displayAvgPricePerYear (Non Static)
   //
   // Description: Displays average price per year
   //
   // Parameters: List<GasRecord> records
   //
   // Returns: N/A
   //
   // **************************************************************
   public void displayAvgPricePerYear(List<GasRecord> records) {
      // Map year to average price
      Map<String, Double> map = records.stream()
            .collect(Collectors.groupingBy(rec -> rec.getYear(),
                  TreeMap::new,
                  Collectors.averagingDouble(GasRecord::getPrice)));

      for (Map.Entry<String, Double> entry : map.entrySet()) {
         System.out.printf("Year: %s => Avg: $%.3f%n", entry.getKey(), entry.getValue());
      } // end for

      return;
   } // end displayAvgPricePerYear

   // ***************************************************************
   //
   // Method: displayAvgPricePerMonth (Non Static)
   //
   // Description: Displays average price per month per year
   //
   // Parameters: List<GasRecord> records
   //
   // Returns: N/A
   //
   // **************************************************************
   public void displayAvgPricePerMonth(List<GasRecord> records) {
      // Map month of year to average price
      Map<String, Map<String, Double>> map = records.stream()
            .collect(Collectors.groupingBy(GasRecord::getYear,
                  TreeMap::new,
                  Collectors.groupingBy(GasRecord::getMonth,
                        Collectors.averagingDouble(GasRecord::getPrice))));

      for (Entry<String, Map<String, Double>> entry : map.entrySet()) {
         String year = entry.getKey();
         for (Entry<String, Double> rec : entry.getValue().entrySet()) {
            String month = rec.getKey();
            double avg   = rec.getValue();
            System.out.printf("Date: %s-%s => Avg: $%.3f%n", month, year, avg);
         } // end for
      } // end for

      return;
   } // end displayAvgPricePerMonth()

   // ***************************************************************
   //
   // Method: displayMinMaxPerYear (Non Static)
   //
   // Description: Displays min and max price per year
   //
   // Parameters: List<GasRecord> records
   //
   // Returns: N/A
   //
   // **************************************************************
   public void displayMinMaxPerYear(List<GasRecord> records) {
      // Map year to GasRecord with max price
      Map<String, Optional<GasRecord>> max_per_year = records.stream()
            .collect(Collectors.groupingBy(rec -> rec.getYear(),
                  TreeMap::new,
                  Collectors.maxBy(Comparator.comparing(GasRecord::getPrice))));

      // Map year to GasRecord with min price
      Map<String, Optional<GasRecord>> min_per_year = records.stream()
            .collect(Collectors.groupingBy(rec -> rec.getYear(),
                  TreeMap::new,
                  Collectors.minBy(Comparator.comparing(GasRecord::getPrice))));

      // Display min and max per year
      for (Entry<String, Optional<GasRecord>> entry : min_per_year.entrySet()) {
         String year             = entry.getKey();
         Optional<GasRecord> min = entry.getValue();
         Optional<GasRecord> max = max_per_year.get(year);

         System.out.printf("---------- %s ----------%n", year);
         System.out.printf("Min => Date: %s Price: $%.3f%n", min.get().getDate(), min.get().getPrice());
         System.out.printf("Max => Date: %s Price: $%.3f%n", max.get().getDate(), max.get().getPrice());
      } // end for

      return;
   } // end displayMinMaxPerYear()

   // ***************************************************************
   //
   // Method: listPrices (Non Static)
   //
   // Description: Sorts records by ascending or descending
   //              based on price
   //
   // Parameters: List<GasRecord> records
   //             boolean high_to_low
   //
   // Returns: N/A
   //
   // **************************************************************
   public void listPrices(List<GasRecord> records, boolean high_to_low) {
      String filename             = new String();
      List<GasRecord> sorted_recs = new ArrayList<GasRecord>();

      if (high_to_low == true) {
         sorted_recs = records.stream()
               .sorted(Comparator.comparing(GasRecord::getPrice).reversed())
               .collect(Collectors.toList());
         filename = "Project8-high-to-low.txt";
      } else {
         sorted_recs = records.stream()
               .sorted(Comparator.comparing(GasRecord::getPrice))
               .collect(Collectors.toList());
         filename = "Project8-low-to-high.txt";
      } // end if

      writeList(sorted_recs, filename);

      return;
   } // end listPrices()

   // ***************************************************************
   //
   // Method: writeList (Non Static)
   //
   // Description: Write list to file
   //
   // Parameters: List<GasRecord> records
   //             String filename
   //
   // Returns: N/A
   //
   // **************************************************************
   public void writeList(List<GasRecord> records, String filename) {
      try (Formatter output = new Formatter(filename)) {
         records.forEach(rec -> output.format("%s $%.3f%n", rec.getDate(), rec.getPrice()));
         output.close();
         System.out.printf("File saved: ./%s%n", filename);
      } catch (IOException e) {
         System.err.println("Failed to write file.");
      } // end try

      return;
   } // end writeList()

   // ***************************************************************
   //
   // Method: displayMenu (Non Static)
   //
   // Description: Displays main menu with specified options
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
      System.out.println("To exit the program, enter 0");
      System.out.println();
      System.out.println("1) Display Average Price per Year");
      System.out.println("2) Display Average Price per Month");
      System.out.println("3) Display Highest and Lowest Price per Year");
      System.out.println("4) Save Prices: Lowest to Highest");
      System.out.println("5) Save Prices: Highest to Lowest");
      System.out.println();

      return;
   } // end displayMenu()

   // ***************************************************************
   //
   // Method: processOption (Non Static)
   //
   // Description: Proccess and execute user option.
   //
   // Parameters: int option
   //             List<GasRecord> records
   //
   // Returns: boolean exit
   //
   // **************************************************************
   public boolean processOption(int option, List<GasRecord> records) {
      boolean exit = false;

      if (option == 0) {
         exit = true;
      } else if (option == 1) {
         displayAvgPricePerYear(records);
      } else if (option == 2) {
         displayAvgPricePerMonth(records);
      } else if (option == 3) {
         displayMinMaxPerYear(records);
      } else if (option == 4) {
         listPrices(records, false);
      } else if (option == 5) {
         listPrices(records, true);
      } else {
         System.out.println("Invalid option.");
      } // end if

      return exit;
   } // end processOption()

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
   } // end waitForUser()
}
