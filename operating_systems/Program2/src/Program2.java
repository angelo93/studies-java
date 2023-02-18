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
        File src_file = getSrcFile();
        File dst_file = getDstFile();

        // Destination file set to "" if errors occur
        // or user chooses to not overwrite.
        if (dst_file.getName().equals("") == false) {
            copyFile(src_file, dst_file);
        } else {
            System.out.println("Exiting without copying.");
        } // end if

        return;
    } // End mainLoop()

    // ***************************************************************
    //
    // Method:      getSrcFile (Non Static)
    //
    // Description: Get source file
    //
    // Parameters:  None
    //
    // Returns:     File src_file
    //
    // **************************************************************
    public File getSrcFile() {
        String prompt   = "Please enter source file path: ";
        File   src_file = new File(getFilePath(prompt));

        while (src_file.exists() == false || src_file.isFile() == false) {
            System.out.println("File either doesn't exist or is not a file:");
            System.out.println(src_file.getAbsolutePath());
            System.out.println();
            src_file = new File(getFilePath(prompt));
        } // end while

        return src_file;
    } // end getSrcFile()

    // ***************************************************************
    //
    // Method:      getDstFile (Non Static)
    //
    // Description: Get destination file
    //
    // Parameters:  None
    //
    // Returns:     File dst_file
    //
    // **************************************************************
    public File getDstFile() {
        boolean created  = false;
        String  prompt   = "Please enter destination file path: ";
        File    dst_file = new File(getFilePath(prompt));

        // Attempt to create destination file
        try {
            created = dst_file.createNewFile();

            if (created == false) {
                String input = getInput("^Y|N|y|n$", "Overwrite? (Y)es (N)o: ");
                if (input.equals("N") || input.equals("n")) {
                    dst_file = new File("");
                } // end if
            } // end if

        } catch (IOException e) {
            System.out.println("Failed to create file");
            dst_file = new File("");
        } // end try

        return dst_file;
    } // end getDstFile()

    // ***************************************************************
    //
    // Method: getInput (Non Static)
    //
    // Description: Get user input with scanner
    //
    // Parameters: String regex
    //             String prompt
    //
    // Returns: String input
    //
    // **************************************************************
    public String getInput(String regex, String prompt) {
        String input = "";

        while (!(input.matches(regex) && input.length() > 0)) {
            System.out.print(prompt);
            input = scanner.nextLine();
        } // end while

        return input;
    } // end getInput()

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

        // Get raw input and trim whitespace
        System.out.print(prompt);
        input = scanner.nextLine().trim();

        return input;
    } // end getFilePath()

    // ***************************************************************
    //
    // Method:      copyFile (Non Static)
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
        System.out.printf("Copying from: %s => %s", src_file.getName(), dst_file.getName());
        System.out.println();

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
    } // end copyFile()
}
