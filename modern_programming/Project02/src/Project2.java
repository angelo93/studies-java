//********************************************************************
//
//  Developer:     Instructor
//
//  Project #:     Two
//
//  File Name:     Project2.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/16/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Employee hierarchy test class.  You are allowed to
//                 modify only line 89.  If you modify any other part
//                 of the class, you will not receive credit for
//                 this project.
//
//********************************************************************

public class Project2
{
   //***************************************************************
   //
   //  Method:       main
   //
   //  Description:  The main method of the program
   //
   //  Parameters:   String array
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public static void main(String[] args)
   {
      // Create an object of the main class and use it to call
      // the non-static developerInfo method
      Project2 obj = new Project2();
      obj.developerInfo();

      // create five-element Employee array
      Employee[] employees = new Employee[6];

      // initialize array with Employees
      employees[0] = new SalariedEmployee(
         "John", "Smith", "111-11-1111", 800.00);

      employees[1] = new HourlyEmployee(
         "Karen", "Price", "222-22-2222", 16.75, 40);

      employees[2] = new CommissionEmployee(
         "Sue", "Jones", "333-33-3333", 10000, .06);

      employees[3] = new BasePlusCommissionEmployee(
         "Bob", "Lewis", "444-44-4444", 5000, .04, 300);

      employees[4] = new PieceEmployee(
         "Rick", "Bridges", "555-55-5555", 2.25, 400);

      employees[5] = new HourlyEmployee(
         "John", "Doe", "666-66-6666", 16.75, 50);

      System.out.println("Employees processed polymorphically:\n");

      // generically process each element in array employees
      for (Employee currentEmployee : employees)
      {
         System.out.println(currentEmployee); // invokes toString
         System.out.printf(
            "Weekly Salary: $%,.2f\n\n", currentEmployee.earnings());
      } // end for

   } // End of the main method

   //***************************************************************
   //
   //  Method:       developerInfo (Non Static)
   //
   //  Description:  The developer information method of the program
   //
   //  Parameters:   None
   //
   //  Returns:      N/A
   //
   //**************************************************************
   public void developerInfo()
   {
      System.out.println("Name:    Michael Navarro-Sanchez");
      System.out.println("Course:  COSC 4301 Modern Programming");
      System.out.println("Project: One\n\n");

   } // End of the developerInfo method

} // End of class Project2
