//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     Six
//
//  File Name:     Fraction.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      10/24/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Project6 Fraction class.
//
//********************************************************************

public class Fraction {
   private int numerator   = -1;
   private int denominator = -1;

   // constructor
   public Fraction(int numerator, int denominator) {
      if (denominator != 0) {
         this.reduce(numerator, denominator);
         System.out.println(String.format("%nReduced Form: %d/%d --> %s", numerator, denominator, this));
      } else {
         throw new IllegalArgumentException();
      }

      return;
   } // End of constructor

   // ***************************************************************
   //
   // Method: setNumerator (Non Static)
   //
   // Description: Sets numerator
   //
   // Parameters: int numerator
   //
   // Returns: N/A
   //
   // **************************************************************
   public void setNumerator(int numerator) {
      this.numerator = numerator;
      return;
   } // End setNumerator()

   // ***************************************************************
   //
   // Method: setDenominator (Non Static)
   //
   // Description: Sets denominator
   //
   // Parameters: int denominator
   //
   // Returns: N/A
   //
   // **************************************************************
   public void setDenominator(int denominator) {
      this.denominator = denominator;
      return;
   } // End setDenominator()

   // ***************************************************************
   //
   // Method: getNumerator (Non Static)
   //
   // Description: Gets numerator
   //
   // Parameters: N/A
   //
   // Returns: int numerator
   //
   // **************************************************************
   public int getNumerator() {
      return this.numerator;
   } // End getNumerator()

   // ***************************************************************
   //
   // Method: getDenominator (Non Static)
   //
   // Description: Gets denominator
   //
   // Parameters: N/A
   //
   // Returns: int denominator
   //
   // **************************************************************
   public int getDenominator() {
      return this.denominator;
   } // End getDenominator()

   // ***************************************************************
   //
   // Method: addFraction (Non Static)
   //
   // Description: Adds two fractions
   //
   // Parameters: Fraction addend
   //
   // Returns: N/A
   //
   // **************************************************************
   public void addFraction(Fraction addend) {
      // Cache current vals
      int old_numerator   = this.getNumerator();
      int old_denominator = this.getDenominator();

      // Addition by cross multiplication
      int new_numerator   = (this.getNumerator() * addend.getDenominator()) + (addend.getNumerator() * this.getDenominator());
      int new_denominator = this.getDenominator() * addend.getDenominator();

      this.reduce(new_numerator, new_denominator);

      // Print out operation
      System.out.println(String.format("%nAdding: %d/%d + %s = %s", old_numerator, old_denominator, addend, this));

      return;
   } // End addFraction()

   // ***************************************************************
   //
   // Method: subtractFraction (Non Static)
   //
   // Description: Subtracts two fractions
   //
   // Parameters: Fraction subtrahend
   //
   // Returns: N/A
   //
   // **************************************************************
   public void subtractFraction(Fraction subtrahend) {
      // Cache current vals
      int old_numerator   = this.getNumerator();
      int old_denominator = this.getDenominator();

      // Subtraction by cross multiplication
      int new_numerator   = (this.getNumerator() * subtrahend.getDenominator()) - (subtrahend.getNumerator() * this.getDenominator());
      int new_denominator = this.getDenominator() * subtrahend.getDenominator();

      this.reduce(new_numerator, new_denominator);

      // Print out operation
      System.out.println(String.format("%nSubtracting: %d/%d - %s = %s", old_numerator, old_denominator, subtrahend, this));

      return;
   } // End subtractFraction()

   // ***************************************************************
   //
   // Method: multiplyuFraction (Non Static)
   //
   // Description: Multiplies two fractions
   //
   // Parameters: Fraction multiplicand
   //
   // Returns: N/A
   //
   // **************************************************************
   public void multiplyFraction(Fraction multiplicand) {
      // Cache current vals
      int old_numerator   = this.getNumerator();
      int old_denominator = this.getDenominator();

      // Calculate products
      int new_numerator   = this.getNumerator() * multiplicand.getNumerator();
      int new_denominator = this.getDenominator() * multiplicand.getDenominator();

      this.reduce(new_numerator, new_denominator);

      // Print out operation
      System.out.println(String.format("%nMultiplying: (%d/%d) * (%s) = %s", old_numerator, old_denominator, multiplicand, this));

      return;
   } // End multiplyFraction()

   // ***************************************************************
   //
   // Method: divideFraction (Non Static)
   //
   // Description: Divides two fractions
   //
   // Parameters: Fraction divisor
   //
   // Returns: N/A
   //
   // **************************************************************
   public void divideFraction(Fraction divisor) {
      // Cache current vals
      int old_numerator   = this.getNumerator();
      int old_denominator = this.getDenominator();

      // Calculate products using the reciprocal of the divisor
      int new_numerator   = this.getNumerator() * divisor.getDenominator();
      int new_denominator = this.getDenominator() * divisor.getNumerator();

      this.reduce(new_numerator, new_denominator);

      // Print out operation
      System.out.println(String.format("%nMultiplying: (%d/%d) * (%s) = %s", old_numerator, old_denominator, divisor, this));

      return;
   } // End divideFraction()

   // ***************************************************************
   //
   // Method: calculateGcd (Non Static)
   //
   // Description: Calculates the GCD of a fraction
   //
   // Parameters: int x
   //             int y
   //
   // Returns: int (gcd)
   //
   // **************************************************************
   public int calculateGcd(int x, int y) {
      return y == 0 ? x : this.calculateGcd(y, x % y);
   } // End calculateGcd()

   // ***************************************************************
   //
   // Method: reduce (Non Static)
   //
   // Description: Reduces fraction
   //
   // Parameters: int numerator
   //             int denominator
   //
   // Returns: N/A
   //
   // **************************************************************
   public void reduce(int numerator, int denominator) {
      boolean neg = (numerator < 0) != (denominator < 0);
      int num     = Math.abs(numerator);
      int den     = Math.abs(denominator);
      int gcd     = this.calculateGcd(num, den);

      // Reduce variables
      num = num / gcd;
      den = den / gcd;

      // Negate if previously negative
      if (neg) num *= -1;

      // Update fraction variables
      this.setNumerator(num);
      this.setDenominator(den);

      return;
   } // End reduce()

   // ***************************************************************
   //
   // Method: dsiplayImproper (Non Static)
   //
   // Description: Displays improper fractions in mixed form
   //
   // Parameters: N/A
   //
   // Returns: N/A
   //
   // **************************************************************
   public void displayImproper() {
      if (this.getNumerator() > this.getDenominator()) {
         int whole_num = this.getNumerator() /  this.getDenominator();
         int remainder = this.getNumerator() % this.getDenominator();

         // No easy way of displaying mixed fractions. This format does NOT mean multiplication...
         System.out.printf("%nMixed Form: %d(%d/%d)", whole_num, remainder, this.getDenominator());
      } else {
         System.out.printf("%nThe fraction is not an improper fraction: %s", this);
      } // End if

      return;
   } // End displayImproper()

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
      return String.format("%d/%d", this.getNumerator(), this.getDenominator());
   } // End toString()
}
