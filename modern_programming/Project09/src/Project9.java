import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Scanner;

//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     9
//
//  File Name:     Project9.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      11/14/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Project9 hierachy test class.
//
//********************************************************************
public class Project9 {
   private SecureRandom randomNumbers  = new SecureRandom(); // Generate random integers for a number of helper functions

   private LevelHandler basic          = new LevelHandler(2, 1);
   private LevelHandler intermediate   = new LevelHandler(3, 2);
   private LevelHandler advanced       = new LevelHandler(4, 3);
   private Evaluator evaluator         = new Evaluator();

   private int current_level  = 1; // Start level at Basic (1)
   private int streak         = 0; // Current streak of questions answered correctly

   private boolean level_up     = false; // True when user reaches a streak of 5 and answers corrctly
   private boolean allow_exit   = false; // True when user rachers a streak of 5
   private boolean write_to_log = true;

   private String log_name = "Project9-output.txt";

   // ***************************************************************
   //
   // Method: main
   //
   // Description: The main method of the program
   //
   // Parameters: String array
   //
   // Returns: N/A
   //
   // **************************************************************
   public static void main(String[] args) {
      // Create an object of the main class and use it to call
      // the non-static developerInfo method
      Project9 obj = new Project9();
      obj.createLog();
      obj.developerInfo();
      obj.mainLoop();

      return;
   } // end main()

   // ***************************************************************
   //
   // Method: developerInfo (Non Static)
   //
   // Description: The developer information method of the program
   //
   // Parameters: None
   //
   // Returns: N/A
   //
   // **************************************************************
   public void developerInfo() {
      outputHandler("Name:    Michael Navarro-Sanchez", true, true);
      outputHandler("Course:  COSC 4301 Modern Programming", true, true);
      outputHandler(String.format("Project: Nine\n\n"), true, true);

      return;
   } // end developerInfo()

   // ***************************************************************
   //
   // Method: mainLoop (Non Static)
   //
   // Description: Main menu loop
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void mainLoop() {
      Scanner scanner = new Scanner(System.in);

      boolean running = true;
      while (running == true) {
         // Get the level handler according to users current level
         LevelHandler temp = getLvlHandler();

         // Run question answer loop
         answerLoop(scanner, temp);

         // Update state according to answer loop results
         updState(scanner);

         // Allow user to exit once they achieve a 5 answer streak
         // This option is reset when leveling up
         if (this.allow_exit == true) {
            String input = getInput(scanner, "^Y|N$", "Exit program? (Y)es (N)o: ");
            running = !input.equals("Y");
         }// end if
      } // end while

      displayResults();

      return;
   } // end mainLoop()

   // ***************************************************************
   //
   // Method: createLog (Non Static)
   //
   // Description: Removes old log file and creates
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void createLog() {
      File log_file = new File(this.log_name);

      // Ensure old log is removed
      log_file.delete();

      try {
         log_file.createNewFile();
      } catch (IOException e) {
         System.out.println("Failed to create log file.");
         this.write_to_log = false;
      } // end try

      return;
   } // end createLog()

   // ***************************************************************
   //
   // Method: outputHandler (Non Static)
   //
   // Description: Handles output for prgram
   //
   // Parameters: String msg
   //             boolean console
   //             boolean log
   //
   // Returns: N/A
   //
   // **************************************************************
   public void outputHandler(String msg, boolean console, boolean log) {
      if (this.write_to_log == true && log == true) {
         try (FileWriter fWriter = new FileWriter(this.log_name, true)) {
            fWriter.append(msg);
            fWriter.append(String.format("%n"));
            fWriter.close();
         } catch (IOException e) {
            System.out.println("Failed to write to log.");
         } // end try
      } // end if

      if (console == true) {
         System.out.println(msg);
      } // end if

      return;
   } // end outputHandler()

   // ***************************************************************
   //
   // Method: answerLoop (Non Static)
   //
   // Description: Answer expression loop
   //
   // Parameters: Scanner scanner
   //             LevelHandler user_level
   //
   // Returns: N/A
   //
   // **************************************************************
   public void answerLoop(Scanner scanner, LevelHandler user_level) {
      // Setup loop variables
      boolean answering = true;
      boolean first_try = true;
      String input      = "";
      String expression = getExpression(user_level.getNumOperands(), user_level.getNumOperators());
      int ans           = this.evaluator.evalExpression(expression);

      user_level.incrementTotalQuestions();

      while (answering == true) {
         // System.out.printf("%nProblem: %s%n", expression.replaceAll(".(?=.)", "$0 "));
         outputHandler(String.format("%nProblem: %s", expression.replaceAll(".(?=.)", "$0 ")), true, true);
         // Use following print for debugging
         outputHandler(String.format("Answer: %s", String.valueOf(ans)), true, false);

         input = getInput(scanner, "-?[0-9]+", "Please enter solution: ");
         int user_ans = Integer.parseInt(input);

         if (user_ans == ans) {
            outputHandler(getCorrectMsg(), true, true);
            answering = false;

            // Answer only counted as correct if on first try
            // Streak only increased when correct on first try
            if (first_try == true) {
               this.streak++;
               user_level.incrementTotalCorrect();
            } // end if

         } else {
            outputHandler(getIncorrectMsg(), true, true);
            this.streak = 0; // reset streak to 0
         } // end if

         // Ensure first try state is set to false after first loop
         first_try = false;

         // Allow user to read console before clearing for next question
         waitForUser(scanner);
      } // end answerLoop()

      return;
   }

   // ***************************************************************
   //
   // Method: getLvlHandler (Non Static)
   //
   // Description: Selects level handler according to user level
   //
   // Parameters: N/A
   //
   // Returns: LevelHandler user_level
   //
   // **************************************************************
   public LevelHandler getLvlHandler() {
      LevelHandler user_level = null;

      if (this.current_level == 1) {
         user_level = this.basic;
      } else if (this.current_level == 2) {
         user_level = this.intermediate;
      } else {
         user_level = this.advanced;
      } // end if

      return user_level;
   } // end getLvlHandler()

   // ***************************************************************
   //
   // Method: updState (Non Static)
   //
   // Description: Update state of session
   //
   // Parameters: Scanner scanner
   //
   // Returns: N/A
   //
   // **************************************************************
   public void updState(Scanner scanner) {
      if (this.streak >= 5) {
         this.level_up = true;
         this.allow_exit = true;
      } // end if

      if (this.level_up == true) {
         // Only allow level up if user is NOT at max level
         if (this.current_level < 3) {
            String input = getInput(scanner, "^Y|N$", "Level up? (Y)es (N)o: ");

            // Reset state variables when leveling up
            if (input.equals("Y")) {
               this.current_level++;
               this.streak     = 0;
               this.level_up   = false;
               this.allow_exit = false;
            } // end if
         } // end if
      } // end ifs

      return;
   } // end updState()

   // ***************************************************************
   //
   // Method: displayResults (Non Static)
   //
   // Description: Displat results of each level
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void displayResults() {
      String msg = "";

      msg = String.format(
            "%n%-20s Total Qs: %d Correct: %d Percent Correct: %.2f",
            "[Basic Level]", this.basic.getTotalQuestions(), this.basic.getTotalCorrect(),
            this.basic.getPercentCorrect());
      if (this.basic.getPercentCorrect() < 80.00) {
         msg = msg + "    Please ask your teacher for extra help.";
      } // end if
      outputHandler(msg, true, true);

      msg = String.format("%-20s Total Qs: %d Correct: %d Percent Correct: %.2f", "[Intermediate Level]",
            this.intermediate.getTotalQuestions(), this.intermediate.getTotalCorrect(),
            this.intermediate.getPercentCorrect());
      outputHandler(msg, true, true);

      msg = String.format("%-20s Total Qs: %d Correct: %d Percent Correct: %.2f", "[Advanced Level]",
            this.advanced.getTotalQuestions(), this.advanced.getTotalCorrect(), this.advanced.getPercentCorrect());
      outputHandler(msg, true, true);

      return;
   } // end displayResults()

   // ***************************************************************
   //
   // Method: getInput (Non Static)
   //
   // Description: Get user input with scanner
   //
   // Parameters: Scanner inputScanner
   //             String regex
   //             String prompt
   //
   // Returns: String input
   //
   // **************************************************************
   public String getInput(Scanner inputScanner, String regex, String prompt) {
      String input = "";

      while (!(input.matches(regex) && input.length() > 0)) {
         System.out.print(prompt);
         input = inputScanner.nextLine();
         outputHandler(prompt + input, false, true);
      } // end while

      return input;
   } // end getInput()

   // ***************************************************************
   //
   // Method: waitForUser (Non Static)
   //
   // Description: Wait for user to confim
   //
   // Parameters: Scanner inputScanner
   //
   // Returns: N/A
   //
   // **************************************************************
   public void waitForUser(Scanner inputScanner) {
      // Wait for user
      outputHandler(String.format("%nPress \"ENTER\" to continue..."), true, true);
      inputScanner.nextLine();

      return;
   } // end waitForUser()

   // ***************************************************************
   //
   // Method: getCorrectMsg() (Non Static)
   //
   // Description: Get a random correct message
   //
   // Parameters: N/A
   //
   // Returns: String msg
   //
   // **************************************************************
   public String getCorrectMsg() {
      String[] correct = {
            "Excellent!",
            "Very good!",
            "Nice work!",
            "Way to go!",
            "Keep up the good work!",
      };
      return (correct[randomNumbers.nextInt(correct.length)]);
   } // end getCorrectMsg()

   // ***************************************************************
   //
   // Method: getIncorrectMsg() (Non Static)
   //
   // Description: Get a random incorrect message
   //
   // Parameters: N/A
   //
   // Returns: String msg
   //
   // **************************************************************
   public String getIncorrectMsg() {
      String[] incorrect = {
            "That is incorrect!",
            "No. Please try again!",
            "Wrong, Try once more!",
            "No. Don't give up!",
            "Incorrect. Keep trying!",
      };
      return (incorrect[randomNumbers.nextInt(incorrect.length)]);
   } // end IncorrectMsg()

   // ***************************************************************
   //
   // Method: getOperator() (Non Static)
   //
   // Description: Get a random operator
   //
   // Parameters: N/A
   //
   // Returns: String operator
   //
   // **************************************************************
   public String getOperator() {
      String[] ops = {
            "*",
            "%",
            "+",
            "-",
      };
      return (ops[randomNumbers.nextInt(ops.length)]);
   } // end getOperator()

   // ***************************************************************
   //
   // Method: getOperand() (Non Static)
   //
   // Description: Get a random operand
   //
   // Parameters: N/A
   //
   // Returns: int operand
   //
   // **************************************************************
   public int getOperand() {
      // Restrict to integers b/w 1-9, inclusive
      // Requireents state only positive integers,
      // 0 is not considered positive
      return randomNumbers.nextInt(9) + 1;
   } // end getOperand()

   // ***************************************************************
   //
   // Method: getCorrectMsg() (Non Static)
   //
   // Description: Get a random correct message
   //
   // Parameters: int num_operands
   //             int num_operators
   //
   // Returns: String mathmatical expression
   //
   // **************************************************************
   public String getExpression(int num_operands, int num_operators) {
      String expression = "";

      for (int i = 1; i <= num_operands; i++) {
         expression += String.valueOf(getOperand());

         if (i <= num_operators) {
            expression += getOperator();
         } // end if
      } // end for

      return expression;
   } // end getExpression()
}
