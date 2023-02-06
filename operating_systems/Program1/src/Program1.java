//********************************************************************
//
//  Author:        Instructor
//
//  Project #:     One
//
//  File Name:     Program1.java
//
//  Course:        COSC 4302 Operating Systems
//
//  Due Date:      2/01/2023
//
//  Instructor:    Prof. Fred Kumi
//
//  Java Version:  17.0.6
//
//  Chapter:       N/A
//
//  Description:   Thread executor code that displays the number of
//                 CPU cores and haiku.
//
//                 You are allowed to modify only lines 15 and 107. If
//                 you modify any other part of this program, you will
//                 NOT receive credit for the Orientation Project.
//
//********************************************************************

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Program1 {
    // ***************************************************************
    //
    // Method: main
    //
    // Description: The main method of the project
    //
    // Parameters: String array
    //
    // Returns: N/A
    //
    // **************************************************************
    public static void main(String[] args) {
        // Create an object of the main class and use it to call
        // the non-static runDemo and developerInfo methods
        Program1 obj = new Program1();
        obj.developerInfo();
        obj.runDemo();

    } // End of the main method

    // ***************************************************************
    //
    // Method: runDemo (Non Static)
    //
    // Description: The main processor method of the program
    //
    // Parameters: None
    //
    // Returns: N/A
    //
    // **************************************************************
    public void runDemo() {
        // Get count of available cores
        int coreCount = Runtime.getRuntime().availableProcessors();

        System.out.println("Your system has " + coreCount + " processors\n");
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);

        // Submit tasks for execution
        for (int count = 1; count <= coreCount; count++) {
            executorService.execute(new ThreadExecutorDemo());
        }

        executorService.shutdown();

        try {
            // wait 1 minute for the threads to finish executing
            boolean tasksEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    } // End of the runDemo method

    // ***************************************************************
    //
    // Method: developerInfo (Non Static)
    //
    // Description: The developer information method of the program
    // This method must be included in all projects.
    //
    // Parameters: None
    //
    // Returns: N/A
    //
    // **************************************************************
    public void developerInfo() {
        System.out.println("Name:    Michael Navarro");
        System.out.println("Course:  COSC 4302 Operating Systems");
        System.out.println("Program: One\n");

    } // End of the developerInfo method
}
