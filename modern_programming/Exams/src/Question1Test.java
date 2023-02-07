import java.security.SecureRandom;

public class Question1Test {
    private static final int MAX_ARRAY = 15;

   public static void main(String[] args)
   {
      int[] inputArray = new int[MAX_ARRAY];
      Question1Test testObj = new Question1Test();
      Question1Class questionObj = new Question1Class();

      testObj.fillArray(inputArray);
      testObj.displayArray(inputArray);

      int startIdx = 3;
      int endIdx = 8;

      double average = questionObj.averageSubArray(inputArray, startIdx, endIdx);

      System.out.print("\n\nThe average of the elements at index " + startIdx
                        + " through " + endIdx + " with the second lowest of "
                        + questionObj.getSecondLowest()
                        + " dropped is: ");
      System.out.printf("%.2f%n", average);
   }

    public void fillArray(int[] inputArray) {
        SecureRandom randomObj = new SecureRandom();
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = randomObj.nextInt(100) + 1;
        }
    }

    public void displayArray(int[] inputArray) {
        System.out.println("Elements in the are array are: ");
        for (int idx = 0; idx < inputArray.length; idx++) {
            System.out.print(inputArray[idx] + "  ");
        }
    }
}