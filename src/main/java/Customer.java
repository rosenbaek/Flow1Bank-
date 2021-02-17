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
        getAccounts();
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
    private void getAccounts(){
        ArrayList<Account>accounts = new ArrayList<>();
        accounts = Utility.returnAccounts(customerId);
        this.accounts = accounts;
    }

    public void withdrawMoney()
    {
        //Printer Accounts
        for (int i = 0; i<accounts.size(); i++){
            System.out.println("Name: " + accounts.get(i).getName() + ", Account ID: " + accounts.get(i).getAccountId() + ", Balance: " + accounts.get(i).getBalance());
        }
        int accountId = Utility.promptForAnswerInt("Enter the account id of the account you want to deposit to: ");
        amount = Utility.promptForAnswerInt("Enter the amount you wish to withdraw: ");

        if (amount <= 0)
        {
            System.out.println("You can only withdraw a positive number, try again.");
            withdrawMoney();
        } else {
            System.out.println("You have withdrawn: " + -amount);
            Utility.createTransactionInDatabase(-amount,accountId);
        }

    }

    public void depositMoney()
    {
        //Printer Accounts
        for (int i = 0; i<accounts.size(); i++){
            System.out.println("Name: " + accounts.get(i).getName() + ", Account ID: " + accounts.get(i).getAccountId() + ", Balance: " + accounts.get(i).getBalance());
        }

        int accountId = Utility.promptForAnswerInt("Enter the account id of the account you want to deposit to: ");
        amount = Utility.promptForAnswerInt("Enter the amount you wish to deposit: ");
        if (amount <= 0)
        {
            System.out.println("You can only deposit a positive number, try again.");
            depositMoney();
        } else {
            System.out.println("You have deposited: " + amount + ", to account ID: " + accountId);
            Utility.createTransactionInDatabase(amount,accountId);
        }

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
                        depositMoney();
                        customerMenu();
                        break;
                    case "2":
                        //Withdraw Money
                        withdrawMoney();
                        customerMenu();
                        break;
                    case "3":
                        //Check Balance
                        System.out.println(accounts);
                        customerMenu();
                        break;
                    case "4":
                        //Print Transactions
                        customerMenu();
                        break;
                    case "5":
                        //Exit
                        DisplayMenu.displayMenu();
                        break;
                }
            }
        }

}
