import java.security.SecureRandom;

public class Question3Test
{
   public static void main(String[] args)
   {
       SecureRandom randomObj = new SecureRandom();

       System.out.println("This program finds relationships and properties of points");
       System.out.println("in a two-dimensional plane.\n");

       Question3Class point1 = new Question3Class(randomObj.nextInt(10), randomObj.nextInt(10));
       Question3Class point2 = new Question3Class(randomObj.nextInt(10), randomObj.nextInt(10));

       System.out.printf("The distance between P1%s and P2%s is %.2f%n", point1, point2,
                          point1.getDistance(point2));

       System.out.printf("The line between P1%s and P2%s is %s%n", point1, point2,
               point1.lineProperties(point2));

       System.out.printf("The slope of the line between P1%s and P2%s is %s%n", point1, point2,
               point1.lineSlope(point2));
   }
}