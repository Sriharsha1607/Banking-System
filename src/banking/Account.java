package banking;

public class Account {
    private int accountId;
    private String name;
    private String email;
    private double balance;

    public Account(int accountId, String name, String email, double balance) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }
}
