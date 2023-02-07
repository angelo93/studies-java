import java.lang.Math;

//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     Final Question 3
//
//  File Name:     Question1Class.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      11/29/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Question 3 class file.
//
//********************************************************************
public class Question3Class {
   private int x;
   private int y;

   // constructor
   public Question3Class(int x, int y) {
      this.setX(x);
      this.setY(y);
      return;
   }

   // ***************************************************************
   //
   // Method: getDistance() (Non Static)
   //
   // Description: get distance of two points
   //
   // Parameters: Question3Class point2
   //
   // Returns: double distance
   //
   // **************************************************************
   public double getDistance(Question3Class point2) {
      double x1 = this.getX();
      double y1 = this.getY();
      double x2 = point2.getX();
      double y2 = point2.getY();

      double part_one = Math.pow((x2 - x1), 2);
      double part_two = Math.pow((y2 - y1), 2);
      double distance = Math.sqrt(part_one + part_two);

      return distance;
   }

   // ***************************************************************
   //
   // Method: lineProperties() (Non Static)
   //
   // Description: determine prop of line
   //
   // Parameters: Question3Class point2
   //
   // Returns: String ln_prop
   //
   // **************************************************************
   public String lineProperties(Question3Class point2) {
      String ln_prop = "";

      if (this.getX() == point2.getX()) {
         ln_prop = "Horizontal";
      } else if (this.getY() == point2.getY()) {
         ln_prop = "Vertical";
      } else {
         ln_prop = "Slanted";
      }

      return ln_prop;
   }

   // ***************************************************************
   //
   // Method: lineSlope() (Non Static)
   //
   // Description: determine slope of line
   //
   // Parameters: Question3Class point2
   //
   // Returns: String ln_slope
   //
   // **************************************************************
   public String lineSlope(Question3Class point2) {
      String ln_slope = "";

      int rise = point2.getY() - this.getY();
      int run = point2.getX() - this.getX();

      if (run == 0) {
         ln_slope = "undefined";
      } else if (point2.getX() == this.getX() && point2.getY() == this.getY()){
         ln_slope = "0.0";
      } else {
         double slope = (double)rise / (double)run;
         ln_slope = String.format("%.3f", slope);
      }

      return ln_slope;
   }

   // ***************************************************************
   //
   // Method: getX() (Non Static)
   //
   // Description: get x
   //
   // Parameters: N/A
   //
   // Returns: this.x
   //
   // **************************************************************
   public int getX() {
      return this.x;
   }

   // ***************************************************************
   //
   // Method: getY() (Non Static)
   //
   // Description: get y
   //
   // Parameters: N/A
   //
   // Returns: this.y
   //
   // **************************************************************
   public int getY() {
      return this.y;
   }

   // ***************************************************************
   //
   // Method: setX() (Non Static)
   //
   // Description: set x
   //
   // Parameters: int x
   //
   // Returns: N/A
   //
   // **************************************************************
   public void setX(int x) {
      this.x = x;
      return;
   }

   // ***************************************************************
   //
   // Method: setY() (Non Static)
   //
   // Description: set y
   //
   // Parameters: int y
   //
   // Returns: N/A
   //
   // **************************************************************
   public void setY(int y) {
      this.y = y;
      return;
   }

   // return String representation of Employee object
   @Override
   public String toString() {
      return String.format("(%d, %d)", this.getX(), this.getY());
   }
}
