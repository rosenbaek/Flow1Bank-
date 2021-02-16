import java.util.ArrayList;

public class Account
{
    private int accountId;
    private double balance;
    private String name;
    private int customerId;
    private ArrayList transactionList;
    private String printTransactions;

    public Account(int accountId, double balance, String name, int customerId, ArrayList transactionList, String printTransactions) {
        this.accountId = accountId;
        this.balance = balance;
        this.name = name;
        this.customerId = customerId;
        this.transactionList = transactionList;
        this.printTransactions = printTransactions;
    }

    public void setAccountId(int accountId)
    {
        this.accountId = accountId;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public void setTransactionList(ArrayList transactionList)
    {
        this.transactionList = transactionList;
    }

    public void setPrintTransactions(String printTransactions)
    {
        this.printTransactions = printTransactions;
    }
}
