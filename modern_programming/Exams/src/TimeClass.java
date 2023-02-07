// TimeClass class declaration with overloaded constructors for question 52.  

public class TimeClass
{
   private int hour;   // 0 - 23
   private int minute; // 0 - 59
   private int second; // 0 - 59

   // TimeClass no-argument constructor: 
   // initializes each instance variable to zero  
   public TimeClass()
   {                                             
      this(0, 0, 0); // invoke constructor with three arguments
   } 

   // TimeClass constructor: hour supplied, minute and second defaulted to 0
   public TimeClass(int hour)
   {                                               
      this(hour, 0, 0); // invoke constructor with three arguments 
   } 

   // TimeClass constructor: hour and minute supplied, second defaulted to 0
   public TimeClass(int hour, int minute) {
      this(hour, minute, 0); // invoke constructor with three arguments 
   } 

   // TimeClass constructor: hour, minute and second supplied   
   public TimeClass(int hour, int minute, int second)
   {                    
      if (hour < 0 || hour >= 24)
	  {
         throw new IllegalArgumentException("hour must be 0-23");
      } 

      if (minute < 0 || minute >= 60)
	  {
         throw new IllegalArgumentException("minute must be 0-59");
      } 

      if (second < 0 || second >= 60)
	  {
         throw new IllegalArgumentException("second must be 0-59");
      } 

      this.hour = hour;
      this.minute = minute; 
      this.second = second; 
   } 

   // set a new time value using universal time;  
   // validate the data
   public void setTime(int hour, int minute, int second) {
      if (hour < 0 || hour >= 24)
	  {
         throw new IllegalArgumentException("hour must be 0-23");
      } 

      if (minute < 0 || minute >= 60)
	  {
         throw new IllegalArgumentException("minute must be 0-59");
      } 

      if (second < 0 || second >= 60)
	  {
         throw new IllegalArgumentException("second must be 0-59");
      } 

      this.hour = hour;
      this.minute = minute; 
      this.second = second; 
   } 

   // validate and set hour 
   public void setHour(int hour)
   {
      if (hour < 0 || hour >= 24)
	  {
         throw new IllegalArgumentException("hour must be 0-23");
      }

      this.hour = hour;
   } 

   // validate and set minute 
   public void setMinute(int minute)
   {
      if (minute < 0 || minute >= 60)
	  {
         throw new IllegalArgumentException("minute must be 0-59");
      }

      this.minute = minute; 
   } 

   // validate and set second 
   public void setSecond(int second)
   {
      if (second < 0 || second >= 60)
	  {
         throw new IllegalArgumentException("second must be 0-59");
      }

      this.second = second;
   } 

   // get hour value      
   public int getHour()
   {
	  return hour;
   }

   // get minute value      
   public int getMinute()
   {
	  return minute;
   } 

   // get second value      
   public int getSecond()
   {
	   return second;
   }   
} 
