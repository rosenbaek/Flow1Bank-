import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        //Må ikke udkommenteres
        Utility.prepareConnection();

        DisplayMenu.displayMenu();

       /* //Opens connection to Database


        //Test Database Calls
        Utility.createBankInDatabase("Ebberød Bank", "CPH");
        Utility.createCustomerInDatabase("Christian", "CPH", 1);
        Utility.createAccountInDatabase("Lønkonto","CPH",1);
        Utility.createTransactionInDatabase(111, 1);

        //Testing customer
        Customer c1 = new Customer(01,"Mr. Tester","England");
        c1.withdrawMoney();
        c1.depositMoney();
        c1.checkBalance();

        System.out.println("Test completed.");*/
    }
}
