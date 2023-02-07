//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     two
//
//  File Name:     SalariedEmployee.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      9/16/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Subclass of Employee abstract class
//
//********************************************************************
public class SalariedEmployee extends Employee {
   private double weeklySalary;

   // constructor
   public SalariedEmployee(String firstName, String lastName,
         String socialSecurityNumber, double weeklySalary)
   {
      super(firstName, lastName, socialSecurityNumber);

      if (weeklySalary < 0.0)
      {
         throw new IllegalArgumentException("Weekly salary must be >= 0.0");
      } // end if

      this.weeklySalary = weeklySalary;
   } // end constructor

   //***************************************************************
   //
   //  Method:       setWeeklySalary (Non Static)
   //
   //  Description:  Sets the value of WeeklySalary
   //
   //  Parameters:   double WeeklySalary
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public void setWeeklySalary(double weeklySalary)
   {
      if (weeklySalary < 0.0)
      {
         throw new IllegalArgumentException(
               "Weekly salary must be >= 0.0");
      } // end if

      this.weeklySalary = weeklySalary;

      return;
   } // end setWeeklySalary()

   //***************************************************************
   //
   //  Method:       getWeeklySalary (Non Static)
   //
   //  Description:  Gets the value of grossSales
   //
   //  Parameters:   N/A
   //
   //  Returns:      double WeeklySalary
   //
   //**************************************************************
   public double getWeeklySalary()
   {
      return weeklySalary;
   }// end getWeeklySalary()

   //***************************************************************
   //
   //  Method:       earnings (Non Static)
   //
   //  Description:  Returns earnings of employee.
   //                Overrides abstract super class method.
   //
   //  Parameters:   N/A
   //
   //  Returns:      double earnings
   //
   //**************************************************************
   @Override
   public double earnings()
   {
      return getWeeklySalary();
   } // end earnings()
}
