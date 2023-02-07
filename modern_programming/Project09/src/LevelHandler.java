//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     9
//
//  File Name:     LevelHandler.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      11/14/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Level handler class for project 9.
//
//********************************************************************
public class LevelHandler {
   private int total_questions = 0; // Total questions asked for level
   private int total_correct   = 0; // Total correct (answered correct on first try)
   private int num_operands    = 0; // Number of operands, used when building expressions
   private int num_operators   = 0; // Number of operators, used when building expressions

   // constructor
   public LevelHandler(int num_operands, int num_operators) {
      this.num_operands = num_operands;
      this.num_operators = num_operators;
      return;
   } // end constructor

   // ***************************************************************
   //
   // Method: getTotalQuestions() (Non Static)
   //
   // Description: Get total questions
   //
   // Parameters: N/A
   //
   // Returns: int this.total_questions
   //
   // **************************************************************
   public int getTotalQuestions() {
      return this.total_questions;
   } // end getTotalQuestions()

   // ***************************************************************
   //
   // Method: getTotalCorrect() (Non Static)
   //
   // Description: Get total correct
   //
   // Parameters: N/A
   //
   // Returns: int this.total_correct
   //
   // **************************************************************
   public int getTotalCorrect() {
      return this.total_correct;
   } // end getTotalCorrect()

   // ***************************************************************
   //
   // Method: incrementTotalQuestions() (Non Static)
   //
   // Description: Increment total questions by 1
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void incrementTotalQuestions() {
      this.total_questions++;
      return;
   } // end incrementTotalQuestions()

   // ***************************************************************
   //
   // Method: incrementTotalCorrect() (Non Static)
   //
   // Description: Increment total correct by 1
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void incrementTotalCorrect() {
      this.total_correct++;
      return;
   } // end incrementTotalCorrect()

   // ***************************************************************
   //
   // Method: getNumOperands() (Non Static)
   //
   // Description: Get number of operands
   //
   // Parameters: N/A
   //
   // Returns: int this.num_operands
   //
   // **************************************************************
   public int getNumOperands() {
      return this.num_operands;
   } // end getNumOperands()

   // ***************************************************************
   //
   // Method: getNumOperators() (Non Static)
   //
   // Description: Get number of operators
   //
   // Parameters: N/A
   //
   // Returns: int this.num_operators
   //
   // **************************************************************
   public int getNumOperators() {
      return this.num_operators;
   } // end getNumOperators()

   // ***************************************************************
   //
   // Method: getPerecentCorrect() (Non Static)
   //
   // Description: Get percentage of questions answered correctly
   //
   // Parameters: N/A
   //
   // Returns: double percentCorrect
   //
   // **************************************************************
   public double getPercentCorrect() {
      double percentCorrect = 0.0;

      if (getTotalQuestions() != 0) {
         percentCorrect = ((double)getTotalCorrect() / (double)getTotalQuestions()) * 100;
      } // end if

      return percentCorrect;
   } // end getPercentCorrect()
}
