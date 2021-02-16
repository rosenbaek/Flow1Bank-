import java.util.ArrayList;

public class Bank
{
    //Variables
    private int bankId;
    private String bankName;
    private String bankCity;

    //Constructor
    public Bank(int bankId, String bankName, String bankCity)
    {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankCity = bankCity;
    }

    //ArrayList for Customers
    ArrayList<Customer> customers = new ArrayList<Customer>();
        //customers.add(new Customer(1,"Mr. Tester","England"));
        //customers.add(new Customer(2, "Ms. Tester", "Worldwide"));

    //ArrayList for Employees
    ArrayList<Employee> employees = new ArrayList<Employee>();
        //employees.add(new Employee(01,"Goblin Bob","Gringotts"));
        //employees.add(new Employee(02,"Goblin Bobline","Gringotts"));

}
