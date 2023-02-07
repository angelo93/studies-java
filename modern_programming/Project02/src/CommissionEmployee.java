//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     two
//
//  File Name:     CommissionEmployee.java
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
public class CommissionEmployee extends Employee {
   private double grossSales;
   private double commissionRate;

   // constructor
   public CommissionEmployee(String firstName, String lastName,
         String socialSecurityNumber, double grossSales, double commissionRate)
   {
      super(firstName, lastName, socialSecurityNumber);

      if (commissionRate <= 0.0 || commissionRate >= 1.0) {
         throw new IllegalArgumentException(
               "Commission rate must be > 0.0 and < 1.0");
      }

      if (grossSales < 0.0) {
         throw new IllegalArgumentException("Gross sales must be >= 0.0");
      }

      this.grossSales = grossSales;
      this.commissionRate = commissionRate;
   } // end constructor

   //***************************************************************
   //
   //  Method:       setGrossSales (Non Static)
   //
   //  Description:  Sets the value of grossSales
   //
   //  Parameters:   double grossSales
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public void setGrossSales(double grossSales)
   {
      if (grossSales < 0.0)
      {
         throw new IllegalArgumentException("Gross sales must be >= 0.0");
      } // end if

      this.grossSales = grossSales;

      return;
   } // end setGrossSales

   //***************************************************************
   //
   //  Method:       getGross sales (Non Static)
   //
   //  Description:  Gets the value of grossSales
   //
   //  Parameters:   N/A
   //
   //  Returns:      double hours
   //
   //**************************************************************
   public double getGrossSales()
   {
      return grossSales;
   } // end getgrossSales()

   //***************************************************************
   //
   //  Method:       setCommissionRate (Non Static)
   //
   //  Description:  Sets the value of commissionRate
   //
   //  Parameters:   double commissionRate
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public void setCommissionRate(double commissionRate)
   {
      if (commissionRate <= 0.0 || commissionRate >= 1.0)
      {
         throw new IllegalArgumentException(
               "Commission rate must be > 0.0 and < 1.0");
      } // end if

      this.commissionRate = commissionRate;

      return;
   } // end setCommissionRate()

   //***************************************************************
   //
   //  Method:       getCommissionRate (Non Static)
   //
   //  Description:  Gets the value of commissionRate
   //
   //  Parameters:   N/A
   //
   //  Returns:      double commissionRate
   //
   //**************************************************************
   public double getCommissionRate()
   {
      return commissionRate;
   } // end getCommissionRate()

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
      return getCommissionRate() * getGrossSales();
   } // end earnings()
}
