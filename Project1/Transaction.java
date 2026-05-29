package banking;

public class Transaction {
    private static int transactionCounter = 1000;
    private String transactionId;
    private String accountNumber;
    private String type;
    private double amount;
    private String dateText;
    
    public Transaction(String accountNumber, String type, double amount) {
        this.transactionId = "TXN" + (++transactionCounter);
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.dateText = java.time.LocalDateTime.now().toString();
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void displayTransaction() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Account: " + accountNumber);
        System.out.println("Type: " + type);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Date: " + dateText);
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
