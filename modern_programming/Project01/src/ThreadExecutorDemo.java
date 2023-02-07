//********************************************************************
//
//  Author:        Instructor
//
//  Project #:     1
//
//  File Name:     ThreadExecutorDemo.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/09/2022
//
//  Instructor:    Prof. Fred Kumi 
//
//  Chapter:       N/A
//
//  Description:   Thread executor code that displays the number
//                 of CPU cores and haiku.
//
//********************************************************************

import java.security.SecureRandom;

public class ThreadExecutorDemo implements Runnable
{
	private final SecureRandom randomNumbers = new SecureRandom();
	
	@Override
	public void run()
	{
       displayMsg();
	}
	
	//***************************************************************
    //
    //  Method:       displayMsg (Non Static)
    // 
    //  Description:  The developer information method of the program
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public void displayMsg()
	{
		for (int counter = 1; counter <= 3; counter++)
		{
		   System.out.printf("%s wrote haiku: %n \"%s\"%nAt loop counter %d %n%n", 
           Thread.currentThread().getName(), getMessage(), counter);
		}
	}
	
	//***************************************************************
    //
    //  Method:       getMessage (Non Static)
    // 
    //  Description:  Displays a Haiku selected a random
    //
    //  Parameters:   None
    //
    //  Returns:      N/A 
    //
    //**************************************************************
	public String getMessage()
	{
	   String[] haiku = { 
	   "The Web site you seek\n Cannot be located, but\n Countless more exist.",
	   "Odd bed fellows \n are Solaris and Linux \n who would have thunk it?",
	   "A crash reduces \n your expensive computer \n to a simple stone.",
	   "Hello World is the \n most famous computer code \n ever written in C",
	   "Stay the patient course \n of little worth is your ire \n the network is down.",
	   "Having been erased, \n the document you are seeking \n must now be retyped."
	   };
		 
       return(haiku[randomNumbers.nextInt(6)]);
	}
}
