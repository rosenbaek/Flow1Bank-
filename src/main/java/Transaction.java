public class Transaction
{
    // variabler
    private int transactionId;
    private int amount;
    private String date;
    private int accountId;

    public Transaction(int transactionId, int amount, String date, int accountId)
    {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.accountId = accountId;
    }

    public int getTransactionId()
    {
        return transactionId;
    }

    public int getAmount()
    {
        return amount;
    }

    public String getDate()
    {
        return date;
    }

    public int getAccountId()
    {
        return accountId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
