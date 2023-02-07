//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     Exam 01
//
//  File Name:     HourlyWorker.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      10/11/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Subclass of Employee abstract class
//
//********************************************************************
public class HourlyWorker extends Employee {
   private double hoursWorked = 0.0;
   private double grossPay    = 0.0;

   // constructor
   public HourlyWorker(int employeeID, String fullName, double hourlyRate, double hoursWorked) {
      super(employeeID, fullName, hourlyRate);

      boolean validHours = validateHours(hoursWorked);
      if (validHours == true) {
         this.hoursWorked = hoursWorked;
      }

      return;
   }

   //***************************************************************
   //
   //  Method:       calcGrossPay (Non Static)
   //
   //  Description:  Calculate gross pay
   //
   //  Parameters:   N/A
   //
   //  Returns:      N/A
   //
   //**************************************************************
   @Override
   public void calcGrossPay() {
      double overtimeHours = 0.0;
      double overtimePay   = 0.0;
      double normalHours   = this.hoursWorked;
      double normalPay     = 0.0;

      if (this.hoursWorked > 40.0) {
         overtimeHours = this.hoursWorked - 40.0;
         normalHours   = 40.0;
      }

      overtimePay = (overtimeHours * (1.5 * super.getHourlyRate()));
      normalPay   = normalHours * super.getHourlyRate();

      this.grossPay = overtimePay + normalPay;
      return;
   }

   //***************************************************************
   //
   //  Method:       calcNetPay (Non Static)
   //
   //  Description:  Calculate net pay
   //
   //  Parameters:   N/A
   //
   //  Returns:      N/A
   //
   //**************************************************************
   @Override
   public double calcNetPay() {
      double netPay = this.grossPay;
      if (grossPay > 650.00) {
         netPay = this.grossPay - (this.grossPay * .1075);
      }
      return netPay;
   }

   //***************************************************************
   //
   //  Method:       toString (Non Static)
   //
   //  Description:  Build output string
   //
   //  Parameters:   N/A
   //
   //  Returns:      N/A
   //
   //**************************************************************
   @Override
   public String toString() {
      return super.toString() + String.format("%nHours Worked: %.2f%nGross Pay: $%.2f%nNet Pay: $%.2f%n", getHoursWorked(), this.grossPay, calcNetPay());
   }

   //***************************************************************
   //
   //  Method:       validateHours (Non Static)
   //
   //  Description:  Validate hours worked
   //
   //  Parameters:   double hoursWorked
   //
   //  Returns:      boolean valid
   //
   //**************************************************************
   public boolean validateHours(double hoursWorked) {
      boolean valid = true;
      if ((hoursWorked < 0.0) || (hoursWorked > 80.0)) {
         valid = false;
         throw new IllegalArgumentException(
               "Hours worked must be >= 0.0 and <= 80.0");
      } // end if

      return valid;
   }

   //***************************************************************
   //
   //  Method:       setHoursWorked (Non Static)
   //
   //  Description:  Set hours worked
   //
   //  Parameters:   double hoursWorked
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public void setHoursWorked(double hoursWorked) {
      boolean validHours = validateHours(hoursWorked);
      if (validHours == true) {
         this.hoursWorked = hoursWorked;
      }
      return;
   }

   //***************************************************************
   //
   //  Method:       getHoursWorked (Non Static)
   //
   //  Description:  Get hours worked
   //
   //  Parameters:   N/A
   //
   //  Returns:      double hoursWorked
   //
   //**************************************************************
   public double getHoursWorked() {
      return this.hoursWorked;
   }

}
