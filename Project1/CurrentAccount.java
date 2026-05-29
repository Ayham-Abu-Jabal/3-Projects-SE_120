package banking;

public class CurrentAccount extends Account {
    private double overdraftLimit;
    private double serviceFee;
    
    public CurrentAccount(String accountNumber, String customerName, double initialDeposit) {
        super(accountNumber, customerName, initialDeposit);
        this.overdraftLimit = 1000.0;
        this.serviceFee = 50.0;
    }
    
    public CurrentAccount(String accountNumber, String customerName, double initialDeposit,
                         double overdraftLimit, double serviceFee) {
        super(accountNumber, customerName, initialDeposit);
        this.overdraftLimit = overdraftLimit;
        this.serviceFee = serviceFee;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive!");
            return false;
        }
        
        double maxWithdrawal = getBalance() + overdraftLimit;
        
        if (amount > maxWithdrawal) {
            System.out.println("Error: Withdrawal amount exceeds limit!");
            System.out.println("Overdraft limit: $" + String.format("%.2f", overdraftLimit));
            System.out.println("Maximum withdrawal possible: $" + String.format("%.2f", maxWithdrawal));
            return false;
        }
        
        setBalance(getBalance() - amount);
        System.out.println("Withdrawn: $" + String.format("%.2f", amount));
        System.out.println("Current Balance: $" + String.format("%.2f", getBalance()));
        
        if (getBalance() < 0) {
            System.out.println("Warning: You are using overdraft. Amount owed: $" + 
                             String.format("%.2f", Math.abs(getBalance())));
        }
        return true;
    }
    
    public void applyServiceFee() {
        System.out.println("Applying service fee: $" + String.format("%.2f", serviceFee));
        setBalance(getBalance() - serviceFee);
        System.out.println("Current Balance: $" + String.format("%.2f", getBalance()));
    }
    
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
    public double getServiceFee() {
        return serviceFee;
    }
}
