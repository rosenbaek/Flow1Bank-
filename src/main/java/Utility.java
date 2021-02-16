import java.lang.*;
import java.sql.*;


public abstract class Utility {
    private static String URL = "jdbc:mysql://localhost:3306/ebberodBank?serverTimezone=UTC";
    private static String USER = "bank";
    private static String PASS = "Bank1234";
    private static Connection con;
    private static PreparedStatement ps_add_customer;
    private static PreparedStatement ps_add_bank;
    private static PreparedStatement ps_add_transaction;
    private static PreparedStatement ps_add_account;

    //Creates bank
    public static void createBankInDatabase(String name, String city) {
        try {
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


    public static void prepareConnection() {
        try
        {
            con = DriverManager.getConnection(URL,USER,PASS);
            ps_add_bank = con.prepareStatement("insert into bank (name, city) values (?,?)");
            ps_add_customer = con.prepareStatement("insert into customer (name, city, bank_id) values (?,?,?)");
            ps_add_account = con.prepareStatement("insert into accounts (name, city, customer_id) values (?,?,?)");
            ps_add_transaction = con.prepareStatement("insert into transactions (amount, account_id) values (?,?)");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
