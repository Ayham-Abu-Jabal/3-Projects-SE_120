package banking;

import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystem {
    private ArrayList<Customer> customers;
    private ArrayList<Account> allAccounts;
    private ArrayList<Transaction> transactions;
    private Scanner scanner;
    
    public BankingSystem() {
        customers = new ArrayList<>();
        allAccounts = new ArrayList<>();
        transactions = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Helper methods
    private Customer findCustomerById(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
    
    private Account findAccountByNumber(String accountNumber) {
        for (Account account : allAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    
    private boolean isCustomerIdExists(String customerId) {
        return findCustomerById(customerId) != null;
    }
    
    private boolean isAccountNumberExists(String accountNumber) {
        return findAccountByNumber(accountNumber) != null;
    }
    
    private void addTransaction(String accountNumber, String type, double amount) {
        Transaction transaction = new Transaction(accountNumber, type, amount);
        transactions.add(transaction);
        System.out.println("Transaction recorded: " + transaction.getTransactionId());
    }
    
    // Menu operations
    public void createCustomer() {
        System.out.println("\n--- Create New Customer ---");
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        
        if (isCustomerIdExists(customerId)) {
            System.out.println("Error: Customer ID already exists!");
            return;
        }
        
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        
        Customer customer = new Customer(customerId, name, phone);
        customers.add(customer);
        System.out.println("Customer created successfully!");
        customer.displayCustomerInfo();
    }
    
    public void createAccount() {
        System.out.println("\n--- Create New Account ---");
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Error: Customer not found!");
            return;
        }
        
        System.out.println("Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.print("Choice: ");
        int type = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        if (isAccountNumberExists(accountNumber)) {
            System.out.println("Error: Account number already exists!");
            return;
        }
        
        System.out.print("Enter Initial Deposit: $");
        double initialDeposit = Double.parseDouble(scanner.nextLine());
        
        if (initialDeposit < 0) {
            System.out.println("Error: Initial deposit cannot be negative!");
            return;
        }
        
        Account account = null;
        String accountType = "";
        
        if (type == 1) {
            account = new SavingsAccount(accountNumber, customer.getName(), initialDeposit);
            accountType = "Savings";
        } else if (type == 2) {
            account = new CurrentAccount(accountNumber, customer.getName(), initialDeposit);
            accountType = "Current";
        } else {
            System.out.println("Invalid account type!");
            return;
        }
        
        customer.addAccount(account);
        allAccounts.add(account);
        if (initialDeposit > 0) {
            addTransaction(accountNumber, "INITIAL_DEPOSIT", initialDeposit);
        }
        
        System.out.println(accountType + " Account Created Successfully!");
        account.displayAccountInfo();
    }
    
    public void deposit() {
        System.out.println("\n--- Deposit Money ---");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found!");
            return;
        }
        
        System.out.print("Enter Deposit Amount: $");
        double amount = Double.parseDouble(scanner.nextLine());
        
        account.deposit(amount);
        if (amount > 0) {
            addTransaction(accountNumber, "DEPOSIT", amount);
        }
    }
    
    public void withdraw() {
        System.out.println("\n--- Withdraw Money ---");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found!");
            return;
        }
        
        System.out.print("Enter Withdrawal Amount: $");
        double amount = Double.parseDouble(scanner.nextLine());
        
        if (account.withdraw(amount)) {
            addTransaction(accountNumber, "WITHDRAWAL", amount);
        }
    }
    
    public void checkBalance() {
        System.out.println("\n--- Check Balance ---");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found!");
            return;
        }
        
        account.checkBalance();
    }
    
    public void displayAllAccounts() {
        System.out.println("\n=== All Accounts in System ===");
        if (allAccounts.isEmpty()) {
            System.out.println("No accounts in the system.");
        } else {
            for (Account account : allAccounts) {
                System.out.println("\n---");
                account.displayAccountInfo();
            }
        }
    }
    
    public void displayCustomerAccounts() {
        System.out.println("\n--- Display Customer Accounts ---");
        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine();
        
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Error: Customer not found!");
            return;
        }
        
        customer.displayCustomerInfo();
        customer.displayAllAccounts();
    }
    
    public void addInterestToSavings() {
        System.out.println("\n--- Add Interest to Savings Account ---");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found!");
            return;
        }
        
        if (account instanceof SavingsAccount) {
            SavingsAccount savings = (SavingsAccount) account;
            savings.addInterest();
            addTransaction(accountNumber, "INTEREST", savings.getInterestRate() * savings.getBalance());
        } else {
            System.out.println("Error: Interest can only be added to Savings Accounts!");
        }
    }
    
    public void viewTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        
        Account account = findAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Error: Account not found!");
            return;
        }
        
        System.out.println("\nTransaction History for Account: " + accountNumber);
        boolean found = false;
        for (Transaction transaction : transactions) {
            if (transaction.toString().contains(accountNumber)) {
                transaction.displayTransaction();
                System.out.println("---");
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No transactions found for this account.");
        }
    }
    
    // Main menu
    public void run() {
        System.out.println("=================================");
        System.out.println("  BANKING MANAGEMENT SYSTEM");
        System.out.println("=================================");
        
        while (true) {
            System.out.println("1. Create Customer");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Display Customer Accounts");
            System.out.println("8. Add Interest (Savings Only)");
            System.out.println("9. View Transaction History");
            System.out.println("10. Exit");
            System.out.print("Choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number!");
                continue;
            }
            
            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    displayAllAccounts();
                    break;
                case 7:
                    displayCustomerAccounts();
                    break;
                case 8:
                    addInterestToSavings();
                    break;
                case 9:
                    viewTransactionHistory();
                    break;
                case 10:
                    System.out.println("Thank you for using the Banking Management System!");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    public static void main(String[] args) {
        BankingSystem system = new BankingSystem();
        system.run();
    }
}
