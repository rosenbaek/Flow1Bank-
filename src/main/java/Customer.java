import java.util.Date;
import java.util.Scanner;

public class Customer
{
    //Variables
    private int customerId;
    private String customerName;
    private String customerCity;

    private int amount;

    //Constructor
    public Customer(int customerId, String customerName, String customerCity)
    {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerCity = customerCity;
    }

    //Getters
    public int getCustomerId()
    {
        return customerId;
    }

    public String getName()
    {
        return customerName;
    }

    public String getCity()
    {
        return customerCity;
    }

    //Methods
    public int withdrawMoney()
    {
        //Skal kobles sammen med Transaction og Account

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount you wish to withdraw: ");
        amount = scanner.nextInt();

        if (amount <= 0)
        {
            System.out.println("You can only withdraw a positive number, try again.");
            withdrawMoney();
        } else
            System.out.println("You have withdrawn: " + amount);

        return amount;
    }

    public int depositMoney()
    {
        //Skal kobles sammen med Transaction og Account

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the amount you wish to deposit: ");
        amount = scanner.nextInt();

        if (amount <= 0)
        {
            System.out.println("You can only deposit a positive number, try again.");
            withdrawMoney();
        } else
            System.out.println("You have deposited: " + amount);

        return amount;
    }

    public int checkBalance()
    {
        return 0;
    }



}
