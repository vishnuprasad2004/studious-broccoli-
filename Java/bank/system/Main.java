package bank.system;

import java.util.Scanner;

public class Main {
    static void main() {

        Scanner sc = new Scanner(System.in);
        BankDriver driver = BankDriver.getBankDriverInstance();
        System.out.println("  ____              _      ____            \n" +
                " | __ )  __ _ _ __ | | __ / ___| _   _ ___ \n" +
                " |  _ \\ / _` | '_ \\| |/ / \\___ \\| | | / __|\n" +
                " | |_) | (_| | | | |   <   ___) | |_| \\__ \\\n" +
                " |____/ \\__,_|_| |_|_|\\_\\ |____/ \\__, |___/\n" +
                "                                 |___/     ");
        while (true) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Get Account by Customer ID");
            System.out.println("3. Get All Accounts");
            System.out.println("4. Get Balance");
            System.out.println("5. Withdraw");
            System.out.println("6. Deposit");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Customer Name: ");
                    sc.nextLine(); // consume newline
                    String name = sc.nextLine();

                    System.out.print("Branch Code: ");
                    int branch = sc.nextInt();

                    System.out.print("IFSC Code: ");
                    String ifsc = sc.next();

                    System.out.print("Account Type: ");
                    String type = sc.next();

                    System.out.print("Initial Balance: ");
                    double balance = sc.nextDouble();

                    driver.createAccount(name, branch, ifsc, type, balance);
                }

                case 2 -> {
                    System.out.print("Customer ID: ");
                    driver.getAccountByCustomerId(sc.nextLong());
                }

                case 3 -> driver.getAllAccounts();

                case 4 -> {
                    System.out.print("Customer ID: ");
                    driver.getBalanceByCustomerId(sc.nextLong());

                }

                case 5 -> {
                    System.out.print("Customer ID: ");
                    long id = sc.nextLong();
                    System.out.print("Amount: ");
                    driver.withdrawal(id, sc.nextDouble());
                }

                case 6 -> {
                    System.out.print("Customer ID: ");
                    long id = sc.nextLong();
                    System.out.print("Amount: ");
                    driver.deposit(id, sc.nextDouble());
                }

                case 7 -> {
                    System.out.println("Thank you for using the bank system!");
                    sc.close();
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }

    }
}
