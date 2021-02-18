import java.util.ArrayList;

public class Account
{
    private int accountId;
    private double balance;
    private String name;
    private int customerId;
    private ArrayList<Transaction> transactionList;

    public Account(int accountId, String name, int customerId) {
        this.accountId = accountId;
        this.name = name;
        this.customerId = customerId;
        getTransactions();
    }

    //Methods
    private void getTransactions(){
        ArrayList<Transaction>transactions = new ArrayList<>();
        transactions = Utility.returnTransactions(accountId);
        this.transactionList = transactions;
    }

    public int getAccountId() {
        return accountId;
    }

    private void updateBalance() {
        transactionList = Utility.returnTransactions(accountId);
        int amount = 0;
        for (int i = 0; i<transactionList.size(); i++) {
            amount += transactionList.get(i).getAmount();
        }
        balance = amount;
    }

    public double getBalance() {
        updateBalance();
        return balance;
    }

    public String getName() {
        return name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                ", customerId=" + customerId +
                ", transactionList=" + transactionList +
                '}';
    }
}
