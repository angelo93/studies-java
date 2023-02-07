//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     Five
//
//  File Name:     Rectangle.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      10/11/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Rectangle test class
//
//********************************************************************
public class Rectangle {
   private double length = 0.0;
   private double width  = 0.0;

   // ***************************************************************
   //
   // Method: Rectangle (Non Static)
   //
   // Description: default constructor
   //
   // Parameters: None
   //
   // Returns: N/A
   //
   // **************************************************************
   public Rectangle() {
      this.length = 2.0;
      this.width  = 2.0;
      return;
   }

   // ***************************************************************
   //
   // Method: Rectangle (Non Static)
   //
   // Description: Paramaterized constructor
   //
   // Parameters: double length
   //             double width
   //
   // Returns: N/A
   //
   // **************************************************************
   public Rectangle(double length, double width) {
      this();  // Call default constructor to ensure default values are set

      boolean validLength = validateSide(length, "Length");
      boolean validWidth  = validateSide(width, "Width");

      if (validLength == true && validWidth == true) {
         this.length = length;
         this.width  = width;
      } else {
         System.out.println("Using default values");
      }

      return;
   }

   // ***************************************************************
   //
   // Method: validateSide (Non Static)
   //
   // Description: Paramaterized constructor
   //
   // Parameters: double side_val
   //             String side_name
   //
   // Returns: boolean valid
   //
   // **************************************************************
   public boolean validateSide(double side_val, String side_name) {
      boolean valid = true;

      if (side_val <= 0.0 || side_val >= 30.0) {
         valid = false;
         throw new IllegalArgumentException(); // Message handled by parent
      }

      return valid;
   }

   // ***************************************************************
   //
   // Method: setLength (Non Static)
   //
   // Description: Set length of rectangle
   //
   // Parameters: double length
   //
   // Returns: N/A
   //
   // **************************************************************
   public void setLength(double length) {
      boolean validLength = validateSide(length, "Length");

      if (validLength == true) {
         this.length = length;
      }

      return;
   }

   // ***************************************************************
   //
   // Method: setWidth (Non Static)
   //
   // Description: Set width of rectangle
   //
   // Parameters: double width
   //
   // Returns: N/A
   //
   // **************************************************************
   public void setWidth(double width) {
      boolean validWidth  = validateSide(width, "Width");

      if (validWidth == true) {
         this.width = width;
      }

      return;
   }

   // ***************************************************************
   //
   // Method: getLength (Non Static)
   //
   // Description: Set length of rectangle
   //
   // Parameters: N/A
   //
   // Returns: double length
   //
   // **************************************************************
   public double getLength() {
      return this.length;
   }

   // ***************************************************************
   //
   // Method: getLength (Non Static)
   //
   // Description: Set length of rectangle
   //
   // Parameters: N/A
   //
   // Returns: double width
   //
   // **************************************************************
   public double getWidth() {
      return this.width;
   }

   // ***************************************************************
   //
   // Method: getPerimeter (Non Static)
   //
   // Description: Set perimeter of rectangle
   //
   // Parameters: N/A
   //
   // Returns: double perimeter
   //
   // **************************************************************
   public double getPerimeter() {
      double ttl_length = 2 * getLength();
      double ttl_width  = 2 * getWidth();
      double perimeter  = ttl_length + ttl_width;

      return perimeter;
   }

   // ***************************************************************
   //
   // Method: getArea (Non Static)
   //
   // Description: Set area of rectangle
   //
   // Parameters: N/A
   //
   // Returns: double area
   //
   // **************************************************************
   public double getArea() {
      return getLength() * getWidth();
   }

   // ***************************************************************
   //
   // Method: toString (Non Static)
   //
   // Description: Override string representation
   //
   // Parameters: N/A
   //
   // Returns: String
   //
   // **************************************************************
   @Override
   public String toString() {
      return String.format("Rectangle Info\nLength: %.2f\nWidth: %.2f\nPerimeter: %.2f\nArea: %.2f", getLength(),
            getWidth(), getPerimeter(), getArea());
   }
}
