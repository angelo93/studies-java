//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     two
//
//  File Name:     PieceEmployee.java
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
public class PieceEmployee extends Employee {
   private double wage;
   private int pieces;

   // constructor
   public PieceEmployee(String firstName, String lastName,
         String socialSecurityNumber, double wage, int pieces)
   {
      super(firstName, lastName, socialSecurityNumber);

      if (wage < 0.0)
      {
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      } // end if

      if (pieces < 0)
      {
         throw new IllegalArgumentException("Number of pieces must be >= 0");
      } // end if

      this.wage = wage;
      this.pieces = pieces;
   } // end constructor

   //***************************************************************
   //
   //  Method:       setWage (Non Static)
   //
   //  Description:  Sets the value of wage
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
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      } // end if

      this.wage = wage;

      return;
   } // end setWage()

   //***************************************************************
   //
   //  Method:       getWage (Non Static)
   //
   //  Description:  Gets the value of wage
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
   //  Method:       setPieces (Non Static)
   //
   //  Description:  Sets the value of pieces
   //
   //  Parameters:   double pieces
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public void setPieces(int pieces)
   {
      if (pieces < 0)
      {
         throw new IllegalArgumentException("Number of pieces must be >= 0");
      } // end if

      this.pieces = pieces;

      return;
   } // end setPieces()

   //***************************************************************
   //
   //  Method:       getPieces (Non Static)
   //
   //  Description:  Gets the value of pieces
   //
   //  Parameters:   N/A
   //
   //  Returns:      double pieces
   //
   //**************************************************************
   public int getPieces()
   {
      return pieces;
   } // end getPieces()

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
      return getWage() * (double)getPieces();
   } // end earnings()
}
