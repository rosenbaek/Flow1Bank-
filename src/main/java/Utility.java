import java.lang.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//@Christian
public abstract class Utility {
    private static String URL = "jdbc:mysql://localhost:3306/ebberodBank?serverTimezone=UTC";
    private static String USER = "bank";
    private static String PASS = "Bank1234";
    private static Connection con;
    private static PreparedStatement ps_add_customer;
    private static PreparedStatement ps_get_all_customers;
    private static PreparedStatement ps_get_customer;
    private static PreparedStatement ps_add_bank;
    private static PreparedStatement ps_get_bank;
    private static PreparedStatement ps_add_transaction;
    private static PreparedStatement ps_get_transaction;
    private static PreparedStatement ps_get_all_customer_info;

    private static PreparedStatement ps_add_account;
    private static PreparedStatement ps_get_account;

    public static String promptForAnswer(String text) {
        System.out.println(text);
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        return input;
    }

    public static int promptForAnswerInt(String text) {
        System.out.println(text);
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        int output = Integer.parseInt(input);
        return output;
    }

    public static double promptForAnswerDouble() {
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();
        input = input.replace(",",".");
        double doubleinput = Double.parseDouble(input);
        return doubleinput;
    }

    /////////////////////////////////
    // DATABASE INTERGRATION BELOW //
    /////////////////////////////////

    //Creates bank
    public static void createBankInDatabase(String name, String city) {
        try {
            name.toLowerCase();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            ps_add_bank.setString(1, name);
            ps_add_bank.setString(2, city);
            ps_add_bank.executeUpdate();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //Creates Customer
    public static void createCustomerInDatabase(String name, String city, int bankId) {
        try {
            name.toLowerCase();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            ps_add_customer.setString(1, name);
            ps_add_customer.setString(2, city);
            ps_add_customer.setInt(3, bankId);
            ps_add_customer.executeUpdate();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Creates account
    public static void createAccountInDatabase(String name, String city, int customer_id) {
        try {
            name.toLowerCase();
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            ps_add_account.setString(1, name);
            ps_add_account.setString(2, city);
            ps_add_account.setInt(3, customer_id);
            ps_add_account.executeUpdate();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Creates Transaction
    public static void createTransactionInDatabase(int amount, int account_id) {
        try {
            ps_add_transaction.setInt(1, amount);
            ps_add_transaction.setInt(2, account_id);
            ps_add_transaction.executeUpdate();
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Returns all Banks
    public static ArrayList<Bank> returnBanks() {
        ArrayList<Bank> banks = new ArrayList<>();
        Bank bank = null;
        try (ResultSet rs = ps_get_bank.executeQuery()){
            while (rs.next()) {
                bank = new Bank(rs.getInt(1), rs.getString(2), rs. getString(3));
                banks.add(bank);
            }
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return banks;
    }

    //Returns all Customers
    public static ArrayList<Customer> returnAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer = null;
        try (ResultSet rs = ps_get_all_customers.executeQuery()){
            while (rs.next()) {
                customer = new Customer(rs.getInt(1), rs.getString(2), rs. getString(3));
                customers.add(customer);
            }
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    //Returns all Customers
    public static Customer returnCustomer(int id) {
        Customer customer = null;
        try {
            ps_get_customer.setInt(1, id);
            try (ResultSet rs = ps_get_customer.executeQuery()) {
                while (rs.next()) {
                    customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3));
                }
            }
        }   catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        return customer;
    }

    public static HashMap<String, Account> returnAccounts(int customer_id) {
        HashMap<String, Account> HashAccounts = new HashMap<>();
        Account account = null;
        try {
            ps_get_account.setInt(1, customer_id);
            try (ResultSet rs = ps_get_account.executeQuery()) {
                while (rs.next()) {
                    account = new Account(rs.getInt(1), rs.getString(2), rs.getInt(4));
                    HashAccounts.put(account.getName(),account);
                }
            }
        }   catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return HashAccounts;
    }

    public static ArrayList<Transaction> returnTransactions(int account_id) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction = null;
        try {
            ps_get_transaction.setInt(1, account_id);
            try (ResultSet rs = ps_get_transaction.executeQuery()) {
                while (rs.next()) {
                    transaction = new Transaction(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                    transactions.add(transaction);
                }
            }
        }   catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return transactions;
    }

    public static ArrayList<Transaction> returnAllTransactionsFromUser(int customer_id) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction = null;
        try {
            ps_get_all_customer_info.setInt(1, customer_id);
            try (ResultSet rs = ps_get_all_customer_info.executeQuery()) {
                while (rs.next()) {
                    transaction = new Transaction(rs.getInt(5), rs.getInt(6), rs.getString(7), rs.getInt(8));
                    transactions.add(transaction);
                }
            }
        }   catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return transactions;
    }
  /*  public static Customer returnAllCustomerInfo() {
        Customer customer = null;
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction = null;
        ArrayList<Account> accounts = new ArrayList<>();
        Account account = null;
        try (ResultSet rs = ps_get_all_customer_info.executeQuery()){
            int accountID = 0;
            while (rs.next()) {
                if (customer == null) {
                    customer = new Customer(rs.getInt(9), rs.getString(10), rs.getString(11));
                    accountID = rs.getInt(1);
                } else {
                    transaction = new Transaction(rs.getInt(5),rs.getInt(6),rs.getString(7), rs.getInt(8));
                    transactions.add(transaction);
                }
            }
        }  catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }*/

    public static void prepareConnection() {
        try
        {
            con = DriverManager.getConnection(URL,USER,PASS);
            ps_add_bank = con.prepareStatement("insert into bank (name, city) values (?,?)");
            ps_get_bank = con.prepareStatement("select * from bank");
            ps_add_customer = con.prepareStatement("insert into customer (name, city, bank_id) values (?,?,?)");
            ps_get_all_customers = con.prepareStatement("select * from customer");
            ps_get_customer = con.prepareStatement("select * from customer where customer_id = ?");
            ps_add_account = con.prepareStatement("insert into accounts (name, city, customer_id) values (?,?,?)");
            ps_add_transaction = con.prepareStatement("insert into transactions (amount, account_id) values (?,?)");
            ps_get_transaction = con.prepareStatement("select * from transactions where account_id = ?");
            ps_get_all_customer_info = con.prepareStatement("select * from accounts join transactions on accounts.account_id = transactions.account_id join customer on accounts.customer_id = customer.customer_id  where customer.customer_id = ? order by transactions.account_id");
            ps_get_account = con.prepareStatement("select * from accounts where customer_id = ?");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
