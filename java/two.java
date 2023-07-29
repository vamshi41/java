package javacbp;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private double balance;
    private double minbalance=1000;

    public Account() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount+"/-");
        } else {
            System.out.println("Invalid amount.zero rupees cannott be added.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
        	if(balance <=minbalance)
        	{
        		System.out.println("Your bank amount doesn't have minimum balance");
        	}
        	else if (amount <= balance && (balance-amount)>=minbalance) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount+"/-");
            } else if(amount>balance || (balance-amount)<minbalance){
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }
}

public class ATMSimulator {
    private static Map<Integer, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        // For simplicity, let's create two accounts for demonstration purposes.
        accounts.put(1234, new Account());
        accounts.put(5678, new Account());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the ATM Simulator!");
            System.out.print("Enter your account number (or -1 to exit): ");
            int accountNumber = scanner.nextInt();

            if (accountNumber == -1) {
                System.out.println("Thank you for using the ATM Simulator. Goodbye!");
                break;
            }
            //checking account is present in it or not

            Account account = accounts.get(accountNumber);
              // if account is not present
            if (account == null) {
                System.out.println("Invalid account number. Please try again.");
                continue;
            }
            //if account exists
            else {

            System.out.println("Hello, Account Holder #" + accountNumber);

            while (true) {
                System.out.println("\nPlease select an option:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Logout");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Balance: " + account.getBalance()+"/-");
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }

                if (option == 4) {
                    break; // Logout from the inner loop
                }
            }
        }
        }

        scanner.close();
    }
}
