// Use this class to test your AdjustTime class

import java.util.Scanner;

public class Question30Test
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      Question30Test obj = new Question30Test();
      
      AdjustTime time = new AdjustTime();

      System.out.println("Enter the time");
      System.out.print("Hours: ");
      time.setHour(input.nextInt());

      System.out.print("Minutes: ");
      time.setMinute(input.nextInt());

      System.out.print("Seconds: ");
      time.setSecond(input.nextInt());
      
      obj.currentTime(time);
      int choice = obj.getMenuChoice();
      
      while (choice != 5)
	  {
         switch (choice)
		 {
            case 1: // add 1 second
               time.incrementSecond();
               break;
               
            case 2: // add 1 minute
               time.incrementMinute();
               break;
            
            case 3: // and 1 hour
               time.incrementHour();
               break;
               
            case 4: // add arbitrary seconds
               System.out.print("Enter seconds to tick: ");
               int seconds = input.nextInt();
               
               for (int i = 0; i < seconds; i++)
			   {
                  time.incrementSecond();
               }
         } 
         
         obj.currentTime(time);
         choice = obj.getMenuChoice();
      }

   } // End of the main method
   
   // prints the time
   public void currentTime(AdjustTime time)
   {
       System.out.printf("Hour: %d  Minute: %d  Second: %d%n",
               time.getHour(), time.getMinute(), time.getSecond());
            
       System.out.printf("Universal time: %s   Standard time: %s%n",
               time.toUniversalString(), time.toString());
   }

   // prints a menu and returns a value corresponding to the menu choice
   public int getMenuChoice()
   {
      Scanner input = new Scanner(System.in);
 
      System.out.println();
      System.out.println("1. Add 1 second");
      System.out.println("2. Add 1 minute");
      System.out.println("3. Add 1 hour");
      System.out.println("4. Add seconds");
      System.out.println("5. Exit");
      System.out.print("Choice: ");
       
      return input.nextInt();
   } 
}
