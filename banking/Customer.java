package banking;

import java.util.ArrayList;

public class Customer {
    private String customerId;
    private String name;
    private String phone;
    private ArrayList<Account> accounts;
    
    public Customer(String customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.accounts = new ArrayList<>();
    }
    
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }  // ← Make sure this exists!
    public String getPhone() { return phone; }
    public ArrayList<Account> getAccounts() { return accounts; }
    
    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account " + account.getAccountNumber() + " linked to customer " + name);
    }
    
    public void displayCustomerInfo() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Number of Accounts: " + accounts.size());
    }
    
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found for this customer.");
        } else {
            System.out.println("\n--- Accounts for " + name + " ---");
            for (Account account : accounts) {
                account.displayAccountInfo();
                System.out.println("---");
            }
        }
    }
}
