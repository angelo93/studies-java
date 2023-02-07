//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     two
//
//  File Name:     HourlyEmployee.java
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
public class HourlyEmployee extends Employee {
   private double wage;
   private double hours;

   // constructor
   public HourlyEmployee(String firstName, String lastName,
         String socialSecurityNumber, double wage, double hours)
   {
      super(firstName, lastName, socialSecurityNumber);

      if (wage < 0.0)
      {
         throw new IllegalArgumentException("Hourly wage must be >= 0.0");
      } // enb if

       // Must be more than 0 hours and less than total hours in a week
      if ((hours < 0.0) || (hours > 168.0))
      {
         throw new IllegalArgumentException(
               "Hours worked must be >= 0.0 and <= 168.0");
      } // end if

      this.wage = wage;
      this.hours = hours;
   } // end constructor

   //***************************************************************
   //
   //  Method:       setWage (Non Static)
   //
   //  Description:  Sets the wage of the class instance to new value
   //
   //  Parameters:   double wage
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public void setWage(double wage)
   {
      if (wage < 0.0)
      {
         throw new IllegalArgumentException("Hourly wage must be >= 0.0");
      } // end if

      this.wage = wage;

      return;
   } // end setWage()

   //***************************************************************
   //
   //  Method:       getWage (Non Static)
   //
   //  Description:  Returns current value of wage for class instance
   //
   //  Parameters:   N/A
   //
   //  Returns:      double wage
   //
   //**************************************************************
   public double getWage()
   {
      return wage;
   } // end getWage()

   //***************************************************************
   //
   //  Method:       setHours (Non Static)
   //
   //  Description:  Sets the number of hours for class instance
   //
   //  Parameters:   double hours
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public void setHours(double hours)
   {
      // more than 0, less than total hours in a week
      if ((hours < 0.0) || (hours > 168.0))
      {
         throw new IllegalArgumentException(
               "Hours worked must be >= 0.0 and <= 168.0");
      } // end if

      this.hours = hours;

      return;
   } // end setHours()

   //***************************************************************
   //
   //  Method:       getHours (Non Static)
   //
   //  Description:  Gets the value of hours for class instance
   //
   //  Parameters:   N/A
   //
   //  Returns:      double hours
   //
   //**************************************************************
   public double getHours()
   {
      return hours;
   } // end getHours()

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
      return getWage() * getHours();
   } // end earnings()
}
