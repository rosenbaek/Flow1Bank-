import java.util.ArrayList;

public class Bank
{
    //Variables
    private int bankId;
    private String bankName;
    private String bankCity;

    //Constructor
    public Bank(int bankId, String bankName, String bankCity)
    {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankCity = bankCity;
    }

    //ArrayList for Customers

    public int getBankId() {
        return bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankCity() {
        return bankCity;
    }

    @Override
    public String toString() {
        return "Name: " + bankName + ", City: " + bankCity + ", Id: " + bankId;
    }
}
