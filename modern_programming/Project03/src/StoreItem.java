//********************************************************************
//
//  Developer:     Instructor
//
//  Project #:     Three
//
//  File Name:     StoreItem.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/23/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   StoreItem class.
//
//********************************************************************
public class StoreItem {
   private int itemNumber;          // Identifying number
   private int numUnits;            // Number of units available
   private double price;            // Price of the item
   private String itemDescription;  // Description of item

   // constructor
   public StoreItem(int itemNumber, int numUnits, double price,
         String itemDescription) {
      this.itemNumber      = itemNumber;
      this.numUnits        = numUnits;
      this.price           = price;
      this.itemDescription = itemDescription;
   } // End of constructor

   // ***************************************************************
   //
   // Method: getItemNumber (Non Static)
   //
   // Description: Returns Item Number
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public int getItemNumber() {
      return this.itemNumber;
   } // End of getItemNumber

   // ***************************************************************
   //
   // Method: getNumUnits (Non Static)
   //
   // Description: Return number of units
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public int getNumUnits() {
      return this.numUnits;
   } // End of getNumUnits

   // ***************************************************************
   //
   // Method: getPrice (Non Static)
   //
   // Description: Return price of item.
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public double getPrice() {
      return this.price;
   } // End of getPrice

   // ***************************************************************
   //
   // Method: getDescription (Non Static)
   //
   // Description: Returns description
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public String getDescription() {
      return this.itemDescription;
   } // End of getDescription

   // ***************************************************************
   //
   // Method: displayFullItemInfo (Non Static)
   //
   // Description: Displays all info of item
   //
   // Parameters: StoreItem item
   //
   // Returns: N/A
   //
   // **************************************************************
   public void displayFullItemInfo() {
      System.out.printf("%-12d %-12s %-10d %.2f %n", this.itemNumber,
            this.itemDescription, this.numUnits, this.price);
   } // End of displayFullItemInfo

   // ***************************************************************
   //
   // Method: displayMenuItemInfo (Non Static)
   //
   // Description: Displays info for menu options
   //
   // Parameters: StoreItem item
   //
   // Returns: N/A
   //
   // **************************************************************
   public void displayMenuItemInfo(int index) {
      System.out.printf("%-6d %s%n", index, this.itemDescription);
   } // End of displayMenuItemInfo

   // ***************************************************************
   //
   // Method: incrementUnitCount (Non Static)
   //
   // Description: Increments number of units by sepcified amount
   //
   // Parameters: int amount
   //
   // Returns: N/A
   //
   // **************************************************************
   public void incrementUnitCount(int amount) {
      this.numUnits += amount;
   } // End of incrementUnitCount

   // ***************************************************************
   //
   // Method: decrementUnitCount (Non Static)
   //
   // Description: Decrements number of units by sepcified amount
   //
   // Parameters: int amount
   //
   // Returns: N/A
   //
   // **************************************************************
   public void decrementUnitCount(int amount) {
      this.numUnits -= amount;
   } // End of decrementUnitCount
}
