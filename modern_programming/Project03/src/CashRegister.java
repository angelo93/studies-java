import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//********************************************************************
//
//  Developer:     Instructor
//
//  Project #:     Three
//
//  File Name:     CashRegister.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/23/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   CashRegister class.
//
//********************************************************************
public class CashRegister {
   private List<StoreItem> cart      = new ArrayList<StoreItem>(); // User cart, used for purchases
   private List<StoreItem> inventory = new ArrayList<StoreItem>(); // Stores inventory
   private List<String> cashiers     = new ArrayList<String>();    // List of cashiers
   private final double tax          = 1.0825;                     // Sales tax

   // constructor
   public CashRegister(List<StoreItem> inventory, List<String> cashiers) {
      this.inventory = inventory;
      this.cashiers  = cashiers;
   } // End of constructor

   // ***************************************************************
   //
   // Method: purchaseItem (Non Static)
   //
   // Description: Accepts a StoreItem object
   // and adds it to internal list.
   //
   // Parameters: int itemIndex
   //
   // Returns: N/A
   //
   // **************************************************************
   public void purchaseItem(StoreItem stockItem) {
      // Check if any items in stock
      if (stockItem.getNumUnits() > 0) {
         // Check if item exists in cart already
         boolean exists = this.cart.stream().anyMatch(item -> stockItem.getItemNumber() == (item.getItemNumber()));
         if (exists == true) {
            // Get index in Cart list
            int cartIndex = IntStream.range(0, this.cart.size())
                  .filter(item -> this.cart.get(item).getItemNumber() == stockItem.getItemNumber())
                  .findFirst()
                  .orElse(-1);

            // Increment count in Cart
            StoreItem cartItem = this.cart.get(cartIndex);
            cartItem.incrementUnitCount(1);
         } else {
            StoreItem pendingItem = new StoreItem(stockItem.getItemNumber(), 1,
                  stockItem.getPrice(), stockItem.getDescription());
            this.cart.add(pendingItem);
         } // End if

         stockItem.decrementUnitCount(1);
         System.out.printf("Added to cart: %s%n", stockItem.getDescription());
      } else {
         System.out.println("Out of Stock");
      } // End if

      return;
   } // End purchaseItem

   // ***************************************************************
   //
   // Method: getTotal (Non Static)
   //
   // Description: Returns total price of items in internal list.
   //
   // Parameters: N/A
   //
   // Returns: float totalPrice
   //
   // **************************************************************
   public double getTotal() {
      double totalPrice = this.cart.stream().mapToDouble((item -> item.getNumUnits() * item.getPrice())).sum();
      return totalPrice;
   } // End getTotal

   // ***************************************************************
   //
   // Method: showItems (Non Static)
   //
   // Description: Displays data for items in internal list.
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void showItems() {
      System.out.println();

      System.out.println("==================== Cart ====================");
      System.out.printf("%-12s %-12s %-10s %-10s%n", "Item Number", "Description", "Num Units", "Price ($)");
      this.cart.stream().forEach(item -> item.displayFullItemInfo());

      System.out.println();

      return;
   } // End showItems

   // ***************************************************************
   //
   // Method: clearRegister (Non Static)
   //
   // Description: Clears internal list.
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void clearRegister() {
      // Iterate through list
      this.cart.forEach(item -> {
         // Get inventory index
         int inventoryIndex = IntStream.range(0, this.inventory.size())
               .filter(stockItem -> this.inventory.get(stockItem).getItemNumber() == item.getItemNumber())
               .findFirst()
               .orElse(-1);

         // Add number of units to num units in inventory
         StoreItem baseItem = this.inventory.get(inventoryIndex);
         baseItem.incrementUnitCount(item.getNumUnits());
      });

      // Clear list
      this.cart.clear();

      return;
   } // End clearRegister

   // ***************************************************************
   //
   // Method: showInventory (Non Static)
   //
   // Description: Displays snapshot of store's inventory
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void showInventory() {
      System.out.println();

      System.out.println("================ Store Inventory ================");
      System.out.printf("%-12s %-12s %-10s %-10s%n", "Item Number", "Description", "Num Units", "Price ($)");
      this.inventory.stream().forEach(item -> item.displayFullItemInfo());

      System.out.println();

      return;
   } // End showInventory

   // ***************************************************************
   //
   // Method: buildReciept (Non Static)
   //
   // Description: Builds receipt using specified criteria
   //
   // Parameters: List<StoreItem> sortedList
   //
   // Returns: List<String> reciept
   //
   // **************************************************************
   public List<String> buildReciept(List<StoreItem> sortedList) {
      double finalPrice = getTotal() * this.tax;
      List<String> reciept = new ArrayList<String>();
      Random rand = new Random();
      String cashierName = cashiers.get(rand.nextInt(cashiers.size()));

      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();
      System.out.println();

      reciept.add(String.format("%n"));

      reciept.add(String.format("Cashier: %s%n", cashierName));
      reciept.add(String.format("Date: %s%n%n", dtf.format(now)));

      reciept.add(String.format("==================== Cart ====================%n"));
      reciept.add(String.format("%-12s %-12s %-10s %-10s%n", "Item Number", "Description", "Num Units", "Price ($)"));
      sortedList.stream().forEach(item -> {
         String tempString = String.format("%-12d %-12s %-10d %-8.2f %n", item.getItemNumber(),
               item.getDescription(), item.getNumUnits(), item.getPrice());
         reciept.add(tempString);
      });

      reciept.add(String.format("%n"));

      reciept.add(String.format("==================== Total ====================%n"));
      reciept.add(String.format("%-36s %.2f%n", "Total", getTotal()));
      reciept.add(String.format("%-36s %.2f%n", "Tax", (finalPrice - getTotal())));
      reciept.add(String.format("%-36s %.2f%n", "Final", finalPrice));

      reciept.add(String.format("%n"));

      return reciept;
   }

   // ***************************************************************
   //
   // Method: printReciept (Non Static)
   //
   // Description: Checkout user. Include tax = 8.25%.
   //
   // Parameters: List<String> reciept
   //
   // Returns: N/A
   //
   // **************************************************************
   public void printReciept(List<String> reciept) {
      try (Formatter output = new Formatter("Project3-Output.txt")) {
         reciept.forEach(line -> output.format(line));
         output.close();
      } catch (IOException e) {
         System.err.println("Failed to write file.");
         e.printStackTrace();
         System.exit(-1);
      } // End try

      return;
   }

   // ***************************************************************
   //
   // Method: checkOut (Non Static)
   //
   // Description: Checkout user. Include tax = 8.25%.
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void checkOut(Scanner inputScanner) {
      List<StoreItem> sortedList = this.cart.stream()
            .sorted((item1, item2) -> item1.getDescription().compareTo(item2.getDescription()))
            .collect(Collectors.toList());
      List<String> reciept = buildReciept(sortedList);

      // Print reciept to console
      reciept.forEach(line -> System.out.print(line));

      char answer = '0';
      do {
         System.out.print("Confirm Payemnt? (Y)es/(N)o: ");
         String temp = inputScanner.nextLine().trim().toUpperCase();

         if (temp.equals("YES") || temp.equals("NO")
               || temp.equals("Y") || temp.equals("N")) {
            answer = temp.charAt(0);
         }
      } while (answer != 'Y' && answer != 'N');

      if (answer == 'Y') {
         printReciept(reciept);
         this.cart.clear();
         System.out.println("Thank you for shopping with us. Your reciept has been saved.");
      } else {
         System.out.println("Emptying cart and restarting transaction.");
         clearRegister();
      } // End if

      return;
   } // End checkOut

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

      System.out.println();

      System.out.println("---------------- Items ----------------");
      System.out.printf("%-6s %s%n", "Option", "Description");
      this.inventory.stream().forEach(item -> item.displayMenuItemInfo(this.inventory.indexOf(item)));

      System.out.println();

      System.out.println("------------- Transaction -------------");
      System.out.printf("%-6s Description", "Option");
      System.out.printf("%n%-6d Show Cash Register", this.inventory.size());
      System.out.printf("%n%-6d Clear Cash Register", this.inventory.size() + 1);
      System.out.printf("%n%-6d Show Inventory", this.inventory.size() + 2);
      System.out.printf("%n%-6d Check Out", this.inventory.size() + 3);

      System.out.println();

      return;
   } // End displayMenu

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
   public void processOption(int option, Scanner inputScanner) {
      if (option < this.inventory.size()) {
         StoreItem stockItem = this.inventory.get(option);
         this.purchaseItem(stockItem);
      } else if (option == this.inventory.size()) {
         this.showItems();
      } else if (option == this.inventory.size() + 1) {
         this.clearRegister();
      } else if (option == this.inventory.size() + 2) {
         this.showInventory();
      } else if (option == this.inventory.size() + 3) {
         this.checkOut(inputScanner);
      } else {
         System.out.println("Unsupported Option.");
      } // End if

   } // End processOption
}
