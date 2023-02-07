//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     Final Question 2
//
//  File Name:     Question1Class.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      11/29/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Question 2 class file.
//
//********************************************************************
public class Question2Class {
   private int dayOfYear;
   private String[] months = {"January", "Febraury", "March", "April",
                              "May", "June", "July", "August", "September",
                              "October", "November", "December"};
   private int[] days_passed = {31, 59, 90, 120, 151, 181,
                                212, 243, 273, 304, 334, 365};

   // constructor
   public Question2Class(int dayOfYear) {
      // Validate input, use default if out of bounds
      if (dayOfYear < 1 || dayOfYear > 365) {
         System.out.println("Class expects a number between 1 and 365, inclusive");
         System.out.println("Defaulting to: 1");
         this.dayOfYear = 1;
      } else {
         this.dayOfYear = dayOfYear;
      } // end if

      return;
   } // end constructor

   // ***************************************************************
   //
   // Method: translateDay() (Non Static)
   //
   // Description: translate a day from a number to month and day
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void translateDay() {
      String date = "";
      int month = 0;

      // Get the index of number of days passed within the bounds of day given
      // Our month is the month before the totol number days passed is greater
      boolean looking = true;
      while (looking == true) {
         if (this.days_passed[month] >= this.dayOfYear) {
            month = month--;
            looking = false;
         } else {
            month++;
         }
      }

      // Special case if we are in January
      if (month == 0) {
         date = months[month] + " " + this.dayOfYear;
      } else {
         date = months[month] + " " + (this.dayOfYear - days_passed[month - 1]);
      }

      System.out.println(date);
   } // end translateDay
}
