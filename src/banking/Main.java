package banking;

import java.util.Scanner;

public class Main {
	@SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
    	@SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();
        System.out.println("!-----WELCOME TO BANKING SYSTEM SERVICES-----!");
        System.out.println("HOW CAN WE HELP YOU !");
        System.out.println("---- LOGIN ----");
        System.out.print("Email: ");
        String email = sc.next();
        System.out.print("Password: ");
        String pass = sc.next();
        int loggedInId = BankService.login(email, pass);
        if(loggedInId == -1){
            System.out.println("Invalid login!");
            return;
        }
        System.out.println("Login Successful! Account ID: " + loggedInId);
        while (true) {
            System.out.println("\n1.Create Account\n2.Deposit\n3.Withdraw\n4.Transfer\n5.View Account details\n6.Exit");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Email: ");
                    String email1 = sc.next();
                    System.out.print("Password: ");
                    String pass1 = sc.next();
                    service.createAccount(name, email1, pass1);
                    break;

                case 2:
                    System.out.print("Account ID: ");
                    int id = sc.nextInt();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    service.deposit(id, amt);
                    break;

                case 3:
                    System.out.print("Account ID: ");
                    id = sc.nextInt();
                    System.out.print("Amount: ");
                    amt = sc.nextDouble();
                    service.withdraw(id, amt);
                    break;

                case 4:
                    System.out.print("From Account: ");
                    int from = sc.nextInt();
                    System.out.print("To Account: ");
                    int to = sc.nextInt();
                    System.out.print("Amount: ");
                    amt = sc.nextDouble();
                    service.transfer(from, to, amt);
                    break;

                case 5:
                	System.out.print("Enter Account ID: ");
                	int accountId = sc.nextInt();
                	service.viewAccountDetails(accountId);
                	break;
                    
                case 6:
                    System.exit(0);
            }
            sc.close();
        }
    }
}
