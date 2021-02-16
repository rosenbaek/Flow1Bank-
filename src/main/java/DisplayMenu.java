import java.util.Scanner;

public class DisplayMenu {
    public static void displayMenu(){
        Scanner scan = new Scanner(System.in);
        String[] menu = {"Create Bank", "Create Customer", "Create Employee", "Enter Customer", "Exit"};

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
                    //TODO save bank in java variable
                    Utility.createBankInDatabase(Utility.promptForAnswer("Enter Bank Name"), Utility.promptForAnswer("Enter Bank City"));
                    break;
                case "2":
                    //Todo save customer in java variable
                    Utility.createCustomerInDatabase(Utility.promptForAnswer("Enter Customer Name"), Utility.promptForAnswer("Enter Customer City"), Utility.promptForAnswerInt("Enter Bank Id") );
                    break;
                case "3":
                    //Todo create empolyee interface and database call
                    break;
                case "4":
                    //Todo Make customer interface
                    break;
                case "5":
                    finish = true;
                    break;

            }
        }
    }
}
