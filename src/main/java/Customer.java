import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Customer
{
    //Variables
    private int customerId;
    private String customerName;
    private String customerCity;
    private HashMap<String,Account> accounts;
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

    private void setAccounts(HashMap<String,Account> accounts) {
        this.accounts = accounts;
    }

    //Methods
    public HashMap<String,Account> getAccounts(){
        HashMap<String,Account> accounts = new HashMap<>();
        accounts = Utility.returnAccounts(customerId);
        this.accounts = accounts;
        return accounts;
    }


    public void withdrawMoney()
    {
        for (String i : accounts.keySet()) {
            System.out.println("Name: " + i + ", Balance: " + accounts.get(i).getBalance());
        }

        try {
            String accountName = Utility.promptForAnswer("Enter the name of the account you want to withdraw to: ");

            //Makes first char to uppercase
            accountName.toLowerCase();
            accountName = accountName.substring(0, 1).toUpperCase() + accountName.substring(1);

            double balanceOfSelectedAccount = 0;
            int accountId = accounts.get(accountName).getAccountId();
            balanceOfSelectedAccount = accounts.get(accountName).getBalance();


            amount = Utility.promptForAnswerInt("Selected: " + accounts.get(accountName).getName() + ". Enter the amount you wish to withdraw: ");

            if ((balanceOfSelectedAccount - Math.abs(amount)) < 0) {
                System.out.println("You do not have enough money on the account");
                withdrawMoney();
            } else {
                System.out.println("You have withdrawn: " + -amount);
                Utility.createTransactionInDatabase(-amount, accountId);
            }
        } catch (Exception e) {
            System.out.println("Did not match any account in your database. Try again");
            withdrawMoney();
        }

    }

    public void depositMoney()
    {
        //Printer Accounts
        for (String i : accounts.keySet()) {
            System.out.println("Name: " + i + ", Balance: " + accounts.get(i).getBalance());
        }

        try {
            String accountName = Utility.promptForAnswer("Enter the name of the account you want to deposit to: ");
            accountName.toLowerCase();
            accountName = accountName.substring(0, 1).toUpperCase() + accountName.substring(1);

            int accountId = accounts.get(accountName).getAccountId();
            amount = Utility.promptForAnswerInt("Enter the amount you wish to deposit: ");

            if (amount <= 0)
            {
                System.out.println("You can only deposit a positive number, try again.");
                depositMoney();
            } else {
                System.out.println("You have deposited: " + amount + ", to account: " + accountName);
                Utility.createTransactionInDatabase(amount,accountId);
            }
        } catch (Exception e) {
            System.out.println("Did not match any account in your database. Try again");
            depositMoney();
        }

    }

    private void checkBalance()
    {
        for (String i : accounts.keySet()) {
            System.out.println("Name: " + i + ", Balance: " + accounts.get(i).getBalance());
        }
    }

    private void printTransactions() {
        //System.out.println(Utility.returnAllTransactionsFromUser(customerId));



            for (String x : accounts.keySet()) {
                for (int i = 0; i< accounts.get(x).getTransactionList().size(); i++) {
                    System.out.println("Name: " + accounts.get(x).getName() + ", Amount: " + accounts.get(x).getTransactionList().get(i).getAmount() + ", Date: " + accounts.get(x).getTransactionList().get(i).getDate());
                }
            }
            //for (int x = 0; x<accounts.get(i).getTransactionList().size(); x++) {
              //  System.out.println("Account name: " + accounts.get(i).getName() + " " + accounts.get(i).getTransactionList().get(x).toString());
            //}

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
            System.out.format("| Nr   | Options   Customer: " + customerName + " %n");
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
                        checkBalance();
                        customerMenu();
                        break;
                    case "4":
                        //Print Transactions
                        printTransactions();
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
