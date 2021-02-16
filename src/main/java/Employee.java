public class Employee
{
    //Variables
    private int employeeId;
    private String employeeName;
    private String employeeCity;

    //Constructor
    public Employee(int employeeId, String employeeName, String employeeCity)
    {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeCity = employeeCity;
    }

    //Getters
    public int getEmployeeId()
    {
        return employeeId;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }

    public String getEmployeeCity()
    {
        return employeeCity;
    }

    //Methods

}
