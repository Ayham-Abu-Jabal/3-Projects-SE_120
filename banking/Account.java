package banking;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private String customerName;
    
    public Account(String accountNumber, String customerName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        if (initialDeposit > 0) {
            this.balance = initialDeposit;
        } else {
            this.balance = 0;
        }
    }
    
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getCustomerName() { return customerName; }
    
    protected void setBalance(double balance) { this.balance = balance; }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + String.format("%.2f", amount));
            System.out.println("Current Balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("Error: Deposit amount must be positive!");
        }
    }
    
    public abstract boolean withdraw(double amount);
    
    public void checkBalance() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
    }
    
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Balance: $" + String.format("%.2f", balance));
    }
}
