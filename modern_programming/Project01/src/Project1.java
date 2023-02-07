//********************************************************************
//
//  Author:        Instructor
//
//  Project #:     1
//
//  File Name:     Project1.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      9/09/2022
//
//  Instructor:    Prof. Fred Kumi
//
//  Chapter:       N/A
//
//  Description:   Thread executor code that displays the number of
//                 CPU cores and haiku.
//
//                 You are allowed to modify only line 105. If you modify
//                 any other part of this program, you will NOT receive
//                 credit for the Orientation Project.
//
//********************************************************************

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Project1
{
    //***************************************************************
    //
    //  Method:       main
    //
    //  Description:  The main method of the project
    //
    //  Parameters:   String array
    //
    //  Returns:      N/A
    //
    //**************************************************************
	public static void main(String[] args)
	{
        // Create an object of the main class and use it to call
        // the non-static runDemo and developerInfo methods
		Project1 obj = new Project1();
        obj.developerInfo();
		obj.runDemo();

	} // End of the main method

	//***************************************************************
    //
    //  Method:       runDemo (Non Static)
    //
    //  Description:  The main processor method of the program
    //
    //  Parameters:   None
    //
    //  Returns:      N/A
    //
    //**************************************************************
	public void runDemo()
	{
		// Get count of available cores
		int coreCount = Runtime.getRuntime().availableProcessors();

		System.out.println("Your system has " + coreCount + " processors\n");
		ExecutorService executorService = Executors.newFixedThreadPool(coreCount);

		// Submit tasks for execution
		for (int count = 1; count <= coreCount; count++)
		{
			executorService.execute(new ThreadExecutorDemo());
		}

        executorService.shutdown();

        try {
            // wait 1 minute for both writers to finish executing
            boolean tasksEnded =
               executorService.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (InterruptedException ex) {
           ex.printStackTrace();
        }

	} // End of the runDemo method

	//***************************************************************
    //
    //  Method:       developerInfo (Non Static)
    //
    //  Description:  The developer information method of the program
	//                This method must be included in all projects.
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
       System.out.println("Project: Project One\n");

    } // End of the developerInfo method
}
