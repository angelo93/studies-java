import java.util.Stack;
import java.lang.Character;
//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     9
//
//  File Name:     Evaluator.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      11/14/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Evaluator class for project 9.
//
//********************************************************************
public class Evaluator {
   // constructor
   public Evaluator() {
      return;
   } // end constructor

   // ***************************************************************
   //
   // Method: evalExpression() (Non Static)
   //
   // Description: evaluate mathmatical expression
   //
   // Parameters: String expression
   //
   // Returns: int result
   //
   // **************************************************************
   public int evalExpression(String expression) {
      int result     = 0;
      String postfix = shuntingYard(expression);
      result         = evalPostfix(postfix);

      return result;
   } // end evalExpression

   // ***************************************************************
   //
   // Method: getPrecedence() (Non Static)
   //
   // Description: Get precedence of operator
   //
   // Parameters: char ch
   //
   // Returns: int precedence
   //
   // **************************************************************
   public int getPrecedence(char ch) {
      int precedence = 0;

      if (ch == '+' || ch == '-') {
         precedence = 1;
      } else if (ch == '*' || ch == '%') {
         precedence = 2;
      } else {
         precedence = -1;
      } // end if

      return precedence;
   } // end getPrecedence()

   // ***************************************************************
   //
   // Method: isLeftAssociated() (Non Static)
   //
   // Description: Determine if operator has left association
   //
   // Parameters: char ch
   //
   // Returns: boolean is_left_assoc
   //
   // **************************************************************
   public boolean isLeftAssociated(char ch) {
      boolean is_left_assoc = false;

      if (ch == '+' || ch == '-' || ch == '%' || ch == '*') {
         is_left_assoc = true;
      } // end if

      return is_left_assoc;
   } // end isLeftAssociated()

   // ***************************************************************
   //
   // Method: shuntingYard() (Non Static)
   //
   // Description: Convert infix to postfix using shunting yard algorithm
   //
   // Parameters: String expression
   //
   // Returns: String postfix
   //
   // **************************************************************
   public String shuntingYard(String expression) {
      Stack<Character> stack = new Stack<>();
      String postfix         = new String("");

      for (int i = 0; i < expression.length(); ++i) {
         // Finding character at 'i'th index
         char c = expression.charAt(i);

         // If operand, add it to postfix
         if (Character.isLetterOrDigit(c) == true) {
            postfix += c;
         } else {
            // Determine precedence of operators
            while (!stack.isEmpty()
                  && getPrecedence(c) <= getPrecedence(stack.peek())
                  && isLeftAssociated(c)) {
               postfix += stack.pop();
            } // end while

            stack.push(c);
         } // end if
      } // end for

      // pop remaining operators
      while (!stack.isEmpty()) {
         postfix += stack.pop();
      } // end while

      return postfix;
   } // end shuntingYard()

   // ***************************************************************
   //
   // Method: evalPostfix() (Non Static)
   //
   // Description: Convert infix to postfix using shunting yard algorithm
   //
   // Parameters: String postfix_expression
   //
   // Returns: int stack.pop() (result)
   //
   // **************************************************************
   public int evalPostfix(String postfix_expression) {
      Stack<Integer> stack = new Stack<>();

      for (int i = 0; i < postfix_expression.length(); i++) {
         char c = postfix_expression.charAt(i);

         // push to stack if operand
         // inplace int conversion
         if (Character.isDigit(c)) {
            stack.push(c - '0');
         } else {
            // grab two operands
            int val1 = stack.pop();
            int val2 = stack.pop();

            // operate on operands
            // Switch case may be better?
            if (c == '+') {
               stack.push(val2 + val1);
            } else if (c == '-') {
               stack.push(val2 - val1);
            } else if (c == '%') {
               stack.push(val2 % val1);
            } else {
               stack.push(val2 * val1);
            } // end if
         } // end if
      } // end for

      // result is last item in stack
      return stack.pop();
   } // end evalPostfix()
}
