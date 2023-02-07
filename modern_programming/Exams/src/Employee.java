// Exam 1 abstract class - Employee.java

public abstract class Employee
{
	private int employeeID;
	private String fullName;
	private double hourlyRate;
	
	public Employee(int employeeID, String fullName, double hourlyRate)
	{
	   this.employeeID = employeeID;
	   this.fullName = fullName;
	   
	   if (hourlyRate < 7.25)
	      hourlyRate = 7.25;
       this.hourlyRate = hourlyRate;
	}
	
	public int getEmployeeID()
	{
		return employeeID;
	}

	public void setEmployeeID(int employeeID)
	{
		this.employeeID = employeeID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public double getHourlyRate()
	{
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate)
	{
	   if (hourlyRate < 7.25)
	      hourlyRate = 7.25;
       this.hourlyRate = hourlyRate;
	}
	
	@Override
    public String toString()
    {
       return String.format("EmployeeID: %d%nFull Name: %s%nHourly Rate: $%.2f", 
         employeeID, fullName, hourlyRate);
    } 
	
    // abstract method must be overridden by concrete subclasses
	public abstract void calcGrossPay();
	
    // abstract method must be overridden by concrete subclasses
	public abstract double calcNetPay();
}
