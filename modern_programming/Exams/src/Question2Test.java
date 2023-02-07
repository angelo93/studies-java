// This program takes an integer representing a day of the 
// year and translates it to an description of the form
// month - day of month.
// Assumes all years have 365 days

import java.util.Scanner;

public class Question2Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Tell user what program does
        System.out.println("This program converts a day given as a " +
                "number between 1 and 365 into month and day.");
        // Get user input
        System.out.print("\nEnter an integer between 1 and 365: ");
        Question2Class monthDayOfYear = new Question2Class(input.nextInt());

        System.out.print("\nThe translated day is ");
        monthDayOfYear.translateDay();

        input.close();
    }
}