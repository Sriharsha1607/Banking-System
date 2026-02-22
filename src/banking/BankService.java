package banking;

import java.sql.*;

public class BankService {

    public void createAccount(String name, String email, String password) throws Exception{
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO accounts(name, email, password) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()) {
        	int account_Id = rs.getInt(1);
        	System.out.println("Account created successfully!");
        	System.out.println("Your Account ID is: "+ account_Id);
        }
    }

    public void deposit(int accountId, double amount) throws Exception{
        Connection con = DBConnection.getConnection();
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, amount);
        ps.setInt(2, accountId);
        ps.executeUpdate();
        System.out.println("Money deposited!");
    }

    public void withdraw(int accountId, double amount) throws Exception{
        Connection con = DBConnection.getConnection();

        String check = "SELECT balance FROM accounts WHERE account_id = ?";
        PreparedStatement ps1 = con.prepareStatement(check);
        ps1.setInt(1, accountId);
        ResultSet rs = ps1.executeQuery();

        if(rs.next() && rs.getDouble("balance") >= amount){
            String sql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
            PreparedStatement ps2 = con.prepareStatement(sql);
            ps2.setDouble(1, amount);
            ps2.setInt(2, accountId);
            ps2.executeUpdate();
            System.out.println("Withdrawl successful!");
        }
        else{
            System.out.println("Insufficient balance!");
        }
    }

    //Transaction
    public void transfer(int fromId, int toId, double amount) throws Exception{
        Connection con = DBConnection.getConnection();
        con.setAutoCommit(false);

        try{
            String debit = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
            PreparedStatement ps1 = con.prepareStatement(debit);
            ps1.setDouble(1, amount);
            ps1.setInt(2, fromId);
            ps1.executeUpdate();

            String credit = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
            PreparedStatement ps2 = con.prepareStatement(credit);
            ps2.setDouble(1, amount);
            ps2.setInt(2, toId);
            ps2.executeUpdate();

            con.commit();
            System.out.println("Transfer successful!");
        }
        catch(Exception e){
            con.rollback();
            System.out.println("Transaction failed!");
        }
    }
    //Login
    public static int login(String email, String password){
        try{
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT account_id FROM accounts WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt("account_id"); // login success
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }
    //AccoutDetails
    public void viewAccountDetails(int accountId) {
        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM accounts WHERE account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("----- Account Details -----");
                System.out.println("Account ID : " + rs.getInt("account_id"));
                System.out.println("Name       : " + rs.getString("name"));
                System.out.println("Email      : " + rs.getString("email"));
                System.out.println("Balance    : " + rs.getDouble("balance"));
            } else {
                System.out.println("Account not found!");
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
