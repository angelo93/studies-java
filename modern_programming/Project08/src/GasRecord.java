//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     eight
//
//  File Name:     GasRecord.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      11/07/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   GasRecord class.
//
//********************************************************************

public class GasRecord {
   private String date  = "";
   private double price = -1;

   // constructor
   public GasRecord(String date, double price) {
      this.date  = date;
      this.price = price;
      return;
   } // end constructor

   // ***************************************************************
   //
   // Method: getDate (Non Static)
   //
   // Description: Gets date
   //
   // Parameters: N/A
   //
   // Returns: String date
   //
   // **************************************************************
   public String getDate() {
      return this.date;
   } // end getDate()

   // ***************************************************************
   //
   // Method: getMonth (Non Static)
   //
   // Description: Gets month
   //
   // Parameters: N/A
   //
   // Returns: String month
   //
   // **************************************************************
   public String getMonth() {
      return this.date.split("-")[0];
   } // end getMonth()

   // ***************************************************************
   //
   // Method: getDay (Non Static)
   //
   // Description: Gets day
   //
   // Parameters: N/A
   //
   // Returns: String day
   //
   // **************************************************************
   public String getDay() {
      return this.date.split("-")[1];
   } // end getDay()

   // ***************************************************************
   //
   // Method: getYear (Non Static)
   //
   // Description: Gets year
   //
   // Parameters: N/A
   //
   // Returns: String year
   //
   // **************************************************************
   public String getYear() {
      return this.date.split("-")[2];
   } // end getYear()

   // ***************************************************************
   //
   // Method: getPrice (Non Static)
   //
   // Description: Gets price
   //
   // Parameters: N/A
   //
   // Returns: String price
   //
   // **************************************************************
   public double getPrice() {
      return this.price;
   } // end getPrice()

   // ***************************************************************
   //
   // Method: displayRecord (Non Static)
   //
   // Description: Dsiplays record information
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void displayRecord() {
      System.out.printf("Date: %s Price: %f%n", this.date, this.price);
      return;
   } // end displayRecord()
}