import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Customer
{
    //Variables
    private int customerId;
    private String customerName;
    private String customerCity;
    private ArrayList<Account> accounts;
    private int amount;

    //Constructor
    public Customer(int customerId, String customerName, String customerCity)
    {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerCity = customerCity;
    }

    //Getters
    public int getCustomerId() { return customerId; }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getCustomerCity()
    {
        return customerCity;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
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
        amount = Utility.promptForAnswerInt("Enter the amount you wish to deposit: ");
        if (amount <= 0)
        {
            System.out.println("You can only deposit a positive number, try again.");
            depositMoney();
        } else {
            System.out.println("You have deposited: " + amount);

        }
        return amount;
    }

    public int checkBalance()
    {
        return 0;
    }

    @Override
    public String toString() {
        return "ID: " + customerId + ", Name: " + customerName + ", City: " + customerCity;
    }

    public void customerMenu() {
            Scanner scan = new Scanner(System.in);
            String[] menu = {"Deposit Money", "Withdraw Money", "Check Balance", "Print Transactions","Exit"};

            String leftAlignFormat = "| %-4d | %-34s | %n"; //%-4d=4 digits, %-15s= 15 string charactors
            System.out.format("+------+------------------------------------+%n");
            System.out.format("| Nr   | Options                            |%n");
            System.out.format("+------+------------------------------------+%n");
            for (int i = 0; i < menu.length; i++) {
                System.out.format(leftAlignFormat, i + 1, menu[i]);
            }
            System.out.format("+------+------------------------------------+%n");

            boolean finish = false;

            while (!finish) {
                String choice = scan.nextLine();
                switch (choice) {
                    case "1":
                        //Deposit Money


                        break;
                    case "2":
                        //Withdraw Money

                        break;
                    case "3":
                        //Check Balance

                        break;
                    case "4":
                        //Print Transactions

                        break;
                    case "5":
                        //Exit
                        DisplayMenu.displayMenu();
                        break;
                }
            }
        }

}
