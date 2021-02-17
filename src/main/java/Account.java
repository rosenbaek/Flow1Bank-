import java.util.ArrayList;

public class Account
{
    private int accountId;
    private double balance;
    private String name;
    private int customerId;
    private ArrayList<Transaction> transactionList;

    public Account(int accountId, String name, int customerId, ArrayList<Transaction> transactionList) {
        this.accountId = accountId;
        this.name = name;
        this.customerId = customerId;
        this.transactionList = transactionList;
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ArrayList getTransactionList() {
        return transactionList;
    }
}
