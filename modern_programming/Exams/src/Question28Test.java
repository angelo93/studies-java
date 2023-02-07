// Use this class to test your Rectangle class

import java.util.InputMismatchException;
import java.util.Scanner;

public class Question28Test
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      Rectangle rectangle = new Rectangle();

      int choice = getMenuChoice();
      while (choice != 4)
	  {
         try {
            switch (choice)
			{
               case 1:
                  System.out.print("Enter length: ");
                  rectangle.setLength(input.nextDouble());
                  break;
               case 2:
                  System.out.print("Enter width: ");
                  rectangle.setWidth(input.nextDouble());
                  break;
               case 3:
                  System.out.print("Enter length: ");
                  rectangle.setLength(input.nextDouble());
                  System.out.print("Enter width: ");
                  rectangle.setWidth(input.nextDouble());
                  break;
            }

            System.out.println();
            System.out.println (rectangle);
         }
         catch (IllegalArgumentException e) {
            System.out.printf("length and width must be 2.0 - 35.0%n%n");
         }
         catch (InputMismatchException e) {
             System.out.printf("Please number values%n%n");
         }

         choice = getMenuChoice();
      }
   }

   // prints a menu and returns a value corresponding to the menu choice
   private static int getMenuChoice()
   {
      Scanner input = new Scanner(System.in);

      System.out.println();
      System.out.println("1. - Set Length");
      System.out.println("2. - Set Width");
      System.out.println("3. - Set Length and Width");
      System.out.println("4. - Exit\n");
      System.out.print("Choice: ");

      return input.nextInt();
   }
}
