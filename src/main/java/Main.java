public class Main {
    public static void main(String[] args)
    {
        //Testing customer
        Customer c1 = new Customer(01,"Mr. Tester","England");
        c1.withdrawMoney();
        c1.depositMoney();
        c1.checkBalance();

        System.out.println("Test completed.");
    }
}
