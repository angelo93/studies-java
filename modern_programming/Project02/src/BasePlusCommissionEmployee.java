//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     two
//
//  File Name:     BasePlusCommissionEmployee.java
//
//  Course:        COSC 4301 - Modern Programming
//
//  Due Date:      9/16/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Subclass of CommissionEmployee class
//
//********************************************************************
public class BasePlusCommissionEmployee extends CommissionEmployee {
   private double baseSalary;

   // constructor
   public BasePlusCommissionEmployee(String firstName, String lastName,
         String socialSecurityNumber, double grossSales,
         double commissionRate, double baseSalary)
   {
      super(firstName, lastName, socialSecurityNumber,
            grossSales, commissionRate);

      if (baseSalary < 0.0)
      {
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      } // end if

      this.baseSalary = baseSalary;
   } // end constructor

   //***************************************************************
   //
   //  Method:       setBaseSalary (Non Static)
   //
   //  Description:  Sets the value of baseSalary
   //
   //  Parameters:   double baseSalary
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public void setBaseSalary(double baseSalary)
   {
      if (baseSalary < 0.0)
      {
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      } // end if

      this.baseSalary = baseSalary;

      return;
   } // end setBaseSalary()

   //***************************************************************
   //
   //  Method:       getBaseSalary (Non Static)
   //
   //  Description:  Gets the value of baseSalary
   //
   //  Parameters:   N/A
   //
   //  Returns:      double baseSalary
   //
   //**************************************************************
   public double getBaseSalary()
   {
      return baseSalary;
   } // end getBaseSalary()

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
      return getBaseSalary() + super.earnings();
   } // end earnings()
}
