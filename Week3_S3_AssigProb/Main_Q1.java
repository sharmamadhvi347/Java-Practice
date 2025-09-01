import java.util.*;

class PersonalAccount {
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    private static int totalAccounts = 0;
    private static String bankName;
    private static int accountCounter = 1000;

    public PersonalAccount(String name) {
        this.accountHolderName = name;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0;
        this.totalIncome = 0;
        this.totalExpenses = 0;
        totalAccounts++;
    }

    public void addIncome(double amount, String description) {
        currentBalance += amount;
        totalIncome += amount;
    }

    public void addExpense(double amount, String description) {
        if (amount <= currentBalance) {
            currentBalance -= amount;
            totalExpenses += amount;
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("Bank Name: " + bankName);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + currentBalance);
        System.out.println("Total Income: " + totalIncome);
        System.out.println("Total Expenses: " + totalExpenses);
        System.out.println("Savings: " + calculateSavings());
        System.out.println();
    }

    public static void setBankName(String name) {
        bankName = name;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static String generateAccountNumber() {
        return "ACC" + (accountCounter++);
    }
}

public class Main_Q1 {
    public static void main(String[] args) {
        PersonalAccount.setBankName("Global Bank");

        PersonalAccount acc1 = new PersonalAccount("Alice");
        acc1.addIncome(5000, "Salary");
        acc1.addExpense(1200, "Rent");

        PersonalAccount acc2 = new PersonalAccount("Bob");
        acc2.addIncome(7000, "Salary");
        acc2.addExpense(2000, "Shopping");
        acc2.addExpense(1000, "Groceries");

        PersonalAccount acc3 = new PersonalAccount("Charlie");
        acc3.addIncome(10000, "Business");
        acc3.addExpense(3000, "Bills");

        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        System.out.println("Total Accounts: " + PersonalAccount.getTotalAccounts());
    }
}

