import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

//********************************************************************
//
//  Author:        Michael Navarro
//
//  Project #:     Two
//
//  File Name:     Program2.java
//
//  Course:        COSC 4302 Operating Systems
//
//  Due Date:      02/08/2023
//
//  Instructor:    Prof. Fred Kumi
//
//  Java Version:  17.0.6
//
//  Chapter:       2.3
//
//  Description:   Copies contents of one file into another file
//
//********************************************************************

public class Program2 {
    Scanner scanner = new Scanner(System.in);

    // ***************************************************************
    //
    // Method:      main
    //
    // Description: The main method of the project
    //
    // Parameters:  String array
    //
    // Returns:     N/A
    //
    // **************************************************************
    public static void main(String[] args) {
        // Create an object of the main class and use it to call
        // the non-static runDemo and developerInfo methods
        Program2 obj = new Program2();
        obj.developerInfo();
        obj.mainLoop();

        return;
    } // End of the main method

    // ***************************************************************
    //
    // Method:      mainLoop (Non Static)
    //
    // Description: Main loop of program
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void mainLoop() {

        // Get filenames
        String src_file_path = getFilePath("Please enter source file path: ");
        String dst_file_path = getFilePath("Please enter destination file path: ");
        System.out.println();

        // Open file handlers
        File src_file = new File(src_file_path);
        File dst_file = new File(dst_file_path);

        // Validate files
        boolean src_is_valid = validFile(src_file);
        boolean dst_is_valid = validFile(dst_file);

        // Only copy if both file paths point to valid files
        if (src_is_valid == true && dst_is_valid == true) {
            copyFile(src_file, dst_file);
        } // end if

        return;
    } // End mainLoop()

    // ***************************************************************
    //
    // Method:      developerInfo (Non Static)
    //
    // Description: The developer information method of the program
    //              This method must be included in all projects.
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void developerInfo() {
        System.out.println("Name:    Michael Navarro");
        System.out.println("Course:  COSC 4302 Operating Systems");
        System.out.println("Program: Two\n");

        return;
    } // End of the developerInfo method

    // ***************************************************************
    //
    // Method:      getFilePath (Non Static)
    //
    // Description: Get file path from user
    //
    // Parameters:  None
    //
    // Returns:     String input
    //
    // **************************************************************
    public String getFilePath(String prompt) {
        String input = "";

        // Get raw input
        System.out.print(prompt);
        input = scanner.nextLine().trim();

        return input;
    } // end getFilePath()

    // ***************************************************************
    //
    // Method:      validFile (Non Static)
    //
    // Description: Check if file exists and is a file
    //
    // Parameters:  String file path
    //
    // Returns:     boolean exists
    //
    // **************************************************************
    public boolean validFile(File file) {
        boolean is_valid = false;

        if (file.exists() == true && file.isFile() == true) {
            is_valid = true;
        } else {
            System.out.println("File either doesn't exist or is not a file:");
            System.out.println(file.getAbsolutePath());
            System.out.println();
        } // end if

        return is_valid;
    }

    // ***************************************************************
    //
    // Method:      copyFilevalidFile (Non Static)
    //
    // Description: Copy contents of one file to another
    //
    // Parameters:  File src_file
    //              File dst_file
    //
    // Returns:     N/A
    //
    // **************************************************************
    public void copyFile(File src_file, File dst_file) {
        System.out.println("Copying source file content to destination file...");
        
        try {
            InputStream  in_stream  = new FileInputStream(src_file);
            OutputStream out_stream = new FileOutputStream(dst_file);
            
            byte[] buffer = new byte[1024];
            int    length;
            
            // While there is content left in source, fill buffer and write out
            while ((length = in_stream.read(buffer)) > 0) {
                out_stream.write(buffer, 0, length);
            } // end while

            in_stream.close();
            out_stream.close();

            System.out.println("Contents copied successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("One or both files not found.");
        } catch (IOException e) {
            System.out.println("Unable to copy contents.");
        }

        return;
    }
}
