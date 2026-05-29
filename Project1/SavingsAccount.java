package banking;

public class SavingsAccount extends Account {
    private double interestRate;
    private double minimumBalance;
    
    public SavingsAccount(String accountNumber, String customerName, double initialDeposit) {
        super(accountNumber, customerName, initialDeposit);
        this.interestRate = 0.04; // 4% annual interest
        this.minimumBalance = 500.0;
    }
    
    public SavingsAccount(String accountNumber, String customerName, double initialDeposit, 
                          double interestRate, double minimumBalance) {
        super(accountNumber, customerName, initialDeposit);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive!");
            return false;
        }
        
        if (amount > getBalance()) {
            System.out.println("Error: Insufficient balance!");
            System.out.println("Available balance: $" + String.format("%.2f", getBalance()));
            return false;
        }
        
        double newBalance = getBalance() - amount;
        if (newBalance < minimumBalance) {
            System.out.println("Error: Cannot withdraw! Minimum balance of $" + 
                             String.format("%.2f", minimumBalance) + " must be maintained.");
            System.out.println("Maximum withdrawal allowed: $" + 
                             String.format("%.2f", getBalance() - minimumBalance));
            return false;
        }
        
        setBalance(newBalance);
        System.out.println("Withdrawn: $" + String.format("%.2f", amount));
        System.out.println("Current Balance: $" + String.format("%.2f", getBalance()));
        return true;
    }
    
    public double calculateInterest() {
        double interest = getBalance() * interestRate;
        System.out.println("Interest earned: $" + String.format("%.2f", interest));
        return interest;
    }
    
    public void addInterest() {
        double interest = calculateInterest();
        deposit(interest);
        System.out.println("Interest added to account!");
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    public double getMinimumBalance() {
        return minimumBalance;
    }
}
