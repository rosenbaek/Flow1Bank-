public class Transaction
{
    // variabler
    private int transactionId;
    private int amount;
    private int date;
    private int accountId;

    public Transaction(int transactionId, int amount, int date, int accountId)
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

    public int getDate()
    {
        return date;
    }

    public int getAccountId()
    {
        return accountId;
    }

}
