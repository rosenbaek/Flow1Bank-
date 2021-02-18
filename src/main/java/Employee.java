import java.util.HashMap;
import java.util.Scanner;

public class Employee
{
    //Variables
    private int employeeId;
    private String employeeName;
    private String employeeCity;

    private Customer customer;
    private HashMap<String,Account> accounts;

    //Constructor
    public Employee(int employeeId, String employeeName, String employeeCity)
    {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeCity = employeeCity;
    }

    //Getters
    public int getEmployeeId() { return employeeId; }

    public String getEmployeeName() { return employeeName; }

    public String getEmployeeCity() { return employeeCity; }

    //Methods
    public void withdrawMoney()
    {
        for (Customer tmp:Utility.returnAllCustomers()) {
            System.out.println(tmp.toString());
        }
        customer = Utility.returnCustomer(Utility.promptForAnswerInt("Write ID on the customer you will choose"));
        accounts = customer.getAccounts();

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


            int amount = Utility.promptForAnswerInt("Selected: " + accounts.get(accountName).getName() + ". Enter the amount you wish to withdraw: ");

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
        for (Customer tmp:Utility.returnAllCustomers()) {
            System.out.println(tmp.toString());
        }
        customer = Utility.returnCustomer(Utility.promptForAnswerInt("Write ID on the customer you will choose"));
        accounts = customer.getAccounts();

        //Printer Accounts
        for (String i : accounts.keySet()) {
            System.out.println("Name: " + i + ", Balance: " + accounts.get(i).getBalance());
        }

        try {
            String accountName = Utility.promptForAnswer("Enter the name of the account you want to deposit to: ");
            accountName.toLowerCase();
            accountName = accountName.substring(0, 1).toUpperCase() + accountName.substring(1);

            int accountId = accounts.get(accountName).getAccountId();
            int amount = Utility.promptForAnswerInt("Enter the amount you wish to deposit: ");

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
        for (Customer tmp:Utility.returnAllCustomers()) {
            System.out.println(tmp.toString());
        }
        customer = Utility.returnCustomer(Utility.promptForAnswerInt("Write ID on the customer you will choose"));
        accounts = customer.getAccounts();
        for (String i : accounts.keySet()) {
            System.out.println("Name: " + i + ", Balance: " + accounts.get(i).getBalance());
        }
    }

    private void printTransactions() {

        for (Customer tmp:Utility.returnAllCustomers()) {
            System.out.println(tmp.toString());
        }
        customer = Utility.returnCustomer(Utility.promptForAnswerInt("Write ID on the customer you will choose"));
        accounts = customer.getAccounts();

        for (String x : accounts.keySet()) {
            for (int i = 0; i< accounts.get(x).getTransactionList().size(); i++) {
                System.out.println("Name: " + accounts.get(x).getName() + ", Amount: " + accounts.get(x).getTransactionList().get(i).getAmount() + ", Date: " + accounts.get(x).getTransactionList().get(i).getDate());
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + employeeId + ", Name: " + employeeName + ", City: " + employeeCity;
    }

    private void transferMoney() {
        Customer fromCustomer = null;
        Customer toCustomer = null;
        for (Customer tmp:Utility.returnAllCustomers()) {
            System.out.println(tmp.toString());
        }
        fromCustomer = Utility.returnCustomer(Utility.promptForAnswerInt("Write ID on the customer you will transfer from"));
        for (String i : fromCustomer.getAccounts().keySet()) {
            System.out.println("Name: " + i + ", Balance: " + fromCustomer.getAccounts().get(i).getBalance());
        }
        String fromAccountName = Utility.promptForAnswer("Enter the name of the account you want to transfer from: ");
        fromAccountName.toLowerCase();
        fromAccountName = fromAccountName.substring(0, 1).toUpperCase() + fromAccountName.substring(1);

        int fromAccountId = fromCustomer.getAccounts().get(fromAccountName).getAccountId();
        int amount = Math.abs(Utility.promptForAnswerInt("Enter the amount you wish to transfer: "));
        Utility.createTransactionInDatabase(-amount,fromAccountId); //Takes money from sender

        toCustomer =  Utility.returnCustomer(Utility.promptForAnswerInt("Write ID on the customer you will transfer to"));
        for (String i : toCustomer.getAccounts().keySet()) {
            System.out.println("Name: " + i + ", Balance: " + toCustomer.getAccounts().get(i).getBalance());
        }
        String toAccountName = Utility.promptForAnswer("Enter the name of the account you want to deposit to: ");
        toAccountName.toLowerCase();
        toAccountName = toAccountName.substring(0, 1).toUpperCase() + toAccountName.substring(1);
        int toAccountId = toCustomer.getAccounts().get(toAccountName).getAccountId();
        Utility.createTransactionInDatabase(amount,toAccountId); //Deliveres money for the reciever
        System.out.println("You have deposited: " + amount + ", to account: " + toAccountName);
    }

    public void employeeMenu() {
        Scanner scan = new Scanner(System.in);
        String[] menu = {"Deposit Money", "Withdraw Money", "Check Balance", "Print Transactions", "Transfer Money", "Exit"};

        String leftAlignFormat = "| %-4d | %-34s | %n"; //%-4d=4 digits, %-15s= 15 string charactors
        System.out.format("+------+------------------------------------+%n");
        System.out.format("| Nr   | Options Employee: " + employeeName + " %n");
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
                    employeeMenu();
                    break;
                case "2":
                    //Withdraw Money
                    withdrawMoney();
                    employeeMenu();
                    break;
                case "3":
                    //Check Balance
                    checkBalance();
                    employeeMenu();
                    break;
                case "4":
                    //Print Transactions
                    printTransactions();
                    employeeMenu();
                    break;
                case "5":
                    //Transfer Money
                    System.out.println("Transfer money");
                    transferMoney();
                    DisplayMenu.displayMenu();
                    break;
                case "6":
                    //Exit
                    DisplayMenu.displayMenu();
                    break;
            }
        }
    }


}
