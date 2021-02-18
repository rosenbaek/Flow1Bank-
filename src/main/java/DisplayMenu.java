import java.util.Scanner;

public class DisplayMenu {

    public static Customer customer;

    public static void displayMenu(){
        Scanner scan = new Scanner(System.in);
        String[] menu = {"Create Bank", "Create Customer", "Create Employee", "Enter Customer", "Enter Employee", "Exit"};

        String leftAlignFormat = "| %-4d | %-34s | %n"; //%-4d=4 digits, %-15s= 15 string charactors
        System.out.format("+------+------------------------------------+%n");
        System.out.format("| Nr   | Options                            |%n");
        System.out.format("+------+------------------------------------+%n");
        for (int i = 0; i < menu.length; i++) {
            System.out.format(leftAlignFormat, i + 1, menu[i]);
        }
        System.out.format("+------+------------------------------------+%n");

        boolean finish = false;

        while (!finish) {
            String choice = scan.nextLine();
            switch (choice) {
                case "1":
                    //Create Bank
                    //TODO save bank in java variable
                    Utility.createBankInDatabase(Utility.promptForAnswer("Enter Bank Name"), Utility.promptForAnswer("Enter Bank City"));
                    break;
                case "2":
                    //Create Customer
                    for (Bank tmp:Utility.returnBanks()) {
                        System.out.println(tmp.toString());
                    }
                    Customer cus = null;
                    cus = Utility.createCustomerInDatabase(Utility.promptForAnswer("Enter Customer Name"), Utility.promptForAnswer("Enter Customer City"), Utility.promptForAnswerInt("Enter Bank Id") );
                    cus.customerMenu();
                    break;
                case "3":
                    //Create Employee
                    for (Bank tmp:Utility.returnBanks()) {
                        System.out.println(tmp.toString());
                    }
                    Employee emp = null;
                    emp = Utility.createEmployeeInDatabase(Utility.promptForAnswer("Enter Employee Name"), Utility.promptForAnswer("Enter Employee City"), Utility.promptForAnswerInt("Enter Bank Id"));
                    emp.employeeMenu();
                    break;
                case "4":
                    //Enter Customer
                    //Todo Make customer interface
                    System.out.println("Here is a list of all customers");
                    for (Customer tmp:Utility.returnAllCustomers()) {
                        System.out.println(tmp.toString());
                    }
                    customer = Utility.returnCustomer(Utility.promptForAnswerInt("Write ID on the customer you will choose"));
                    customer.customerMenu();
                    break;
                case "5":
                    //Enter Employee
                    Employee karl = new Employee(1, "karl", "cph");
                    karl.employeeMenu();
                    //Todo Make Employee interface
                    break;
                case "6":
                    finish = true;
                    break;
            }
        }
    }
}
