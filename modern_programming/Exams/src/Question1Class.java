import java.util.Arrays;

//********************************************************************
//
//  Developer:     Michael Navarro
//
//  Project #:     Final Question 1
//
//  File Name:     Question1Class.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      11/29/2022
//
//  Instructor:    Fred Kumi
//
//  Description:   Question 1 class file.
//
//********************************************************************
public class Question1Class {
   private int sec_lowest;

   // constructor
   public Question1Class() {
      return;
   }

   // ***************************************************************
   //
   // Method: averageSubArray() (Non Static)
   //
   // Description: calculates average for a subarray minus second lowest number
   //
   // Parameters: int[] inputArray,
   //             int startIdx,
   //             int endIdx
   //
   // Returns: double avg
   //
   // **************************************************************
   public double averageSubArray(int[] inputArray, int startIdx, int endIdx) {
      secondLowestInArray(inputArray, startIdx, endIdx);

      double avg = 0.0;
      int total = 0;
      int count = 0;

      for (int i = startIdx; i <= endIdx; i++) {
         total += inputArray[i];
         count++;
      }

      total -= this.getSecondLowest();
      count--;
      avg = (double) total / (double) count;

      return avg;
   }

   // ***************************************************************
   //
   // Method: secondLowestInArray() (Non Static)
   //
   // Description: determines second lowest int in subarray
   //
   // Parameters: int[] inputArray,
   //             int startIdx,
   //             int endIdx
   //
   // Returns: N/A
   //
   // **************************************************************
   private void secondLowestInArray(int[] inputArray, int startIdx, int endIdx) {
      int[] slice = Arrays.copyOfRange(inputArray, startIdx, endIdx);
      Arrays.sort(slice);

      setSecondLowest(slice[1]);

      return;
   }

   // ***************************************************************
   //
   // Method: getSecondLowest() (Non Static)
   //
   // Description: calculates average for a subarray minus second lowest number
   //
   // Parameters: N/A
   //
   // Returns: int this.sec_lowest
   //
   // **************************************************************
   public int getSecondLowest() {
      return this.sec_lowest;
   }

   // ***************************************************************
   //
   // Method: setSecondLowest() (Non Static)
   //
   // Description: sets sec_lowest
   //
   // Parameters: int num
   //
   // Returns: N/A
   //
   // **************************************************************
   public void setSecondLowest(int num) {
      this.sec_lowest = num;
      return;
   }
}