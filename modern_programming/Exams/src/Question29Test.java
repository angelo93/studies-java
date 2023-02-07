import java.util.ArrayList;
import java.util.List;

public class Question29Test
{
	public static void main(String[] args)
	{
	    List<Employee> employees = new ArrayList<>();
	    
	    employees.add(new HourlyWorker(1001, "Bob Jones", 25.50, 35));
	    employees.add(new HourlyWorker(1002, "Jane Green", 45.60, 40));
	    employees.add(new HourlyWorker(1003, "John Doe", 65.50, 55));
	    employees.add(new HourlyWorker(1004, "Joe Blue", 10.25, 39.99));
	    employees.add(new HourlyWorker(1005, "Jane Doe", 35.75, 40.01));
	    
	    System.out.println("Employees processed polymorphically:");
	      
	    // Polymorphically process each element in array employees
	    for (Employee currentEmployee : employees) 
	    {
	       currentEmployee.calcGrossPay();
	       System.out.println(currentEmployee); // invokes toString
	    } // End of the for loop
	} // End of the main method
}
