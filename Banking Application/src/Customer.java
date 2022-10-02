import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    public int customerId;
    public int accountNumber;
    public String name;
    public double accountBalance;
    public String password;
    public String encryptedPassword;
    public int transactionId;

    public Customer(int customerId,int accountNumber, String name, double accountBalance, String password,String encryptedPassword,int transactionId){
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.name = name;
        this.accountBalance = accountBalance;
        this.password = password;
        this.encryptedPassword = encryptedPassword;
        this.transactionId = transactionId;
    }

    public static void addCustomer(String nameofNewCustomer, String newCustomerPassword) throws IOException {
        int lastEntry = Bank.customers.size()-1;
        int newCustId = Bank.customers.get(lastEntry).customerId+1;
        int newAccountNumber = Bank.customers.get(lastEntry).accountNumber+1;
        double newAccountBalance = 10000;
        Bank.customers.add( new Customer(newCustId,newAccountNumber,nameofNewCustomer,newAccountBalance,newCustomerPassword,encyptCustomerPassword(newCustomerPassword),0));
        //CustomerFileNameReader addNewCustomer = new CustomerFileNameReader();
        CustomerFileNameReader.writeNewCustomerinFile();
        CustomerFileNameReader.createCustomerFile();
    }

    public static boolean passwordChecker(String password){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the password again to confirm");
        String passwordConfirm = s.next();
        return (password.equals(passwordConfirm));
    }
    public static String encyptCustomerPassword(String passwordtoEncrypt){
        char c[] = passwordtoEncrypt.toCharArray();
        for(int i=0; i<c.length; i++){
            if(c[i]>=48 && c[i]<=57){
                c[i] = (char)((((c[i]-48) +1)%10)+48);
            }else if(c[i]>=65 && c[i]<=90){
                c[i] = (char)((((c[i]+1)%65)%26)+65);
            }else{
                c[i] = (char)((((c[i]+1)%97)%26)+97);
            }
        }
        return new String(c);
    }
    public static boolean validateExistingUser(int valdiateCustomerId,int validateCustomerAccountNumber,String validateCustomerPassword){
        if(Bank.customers.get(valdiateCustomerId-1).customerId!=valdiateCustomerId)
        {
            System.out.println("Customer id is not valid, create new customer");

        }
        if(Bank.customers.get(valdiateCustomerId-1).accountNumber!=validateCustomerAccountNumber)
        {
            System.out.println("Account number is not valid,Try Again");

        }
        if(!Bank.customers.get(valdiateCustomerId-1).password.equals(validateCustomerPassword))
        {
            //System.out.println(Bank.customers.get(valiateCustomerId-1).password);
            System.out.println("Password does not match");
        }
        else
        {
            System.out.println("------------Login Success------------");
            System.out.println("Welcome to ABC Bank");
            return true;
        }
        return false;
    }
    public static void cashDepositFunction(double cashDepositAmount,int validateCustomerId) throws IOException {
        System.out.println("Account Balance is "+Bank.customers.get(validateCustomerId-1).accountBalance);
        Bank.customers.get(validateCustomerId-1).accountBalance = Bank.customers.get(validateCustomerId-1).accountBalance +cashDepositAmount;
        System.out.println("New Account Balance is "+Bank.customers.get(validateCustomerId-1).accountBalance);
        CustomerFileNameReader.writeCustomerTransactions(validateCustomerId,"Deposit",cashDepositAmount);
    }
    public static void cashWithdrawalFunction(double casWithdrawalAmount,int validateCustomerId) throws IOException {
        if(Bank.customers.get(validateCustomerId-1).accountBalance<=1000 || (Bank.customers.get(validateCustomerId-1).accountBalance - casWithdrawalAmount)<=1000){
            System.out.println("Withdrawal amount is more than the required minimum balance\nWithdrawal not possible");
        }
        else
        {
            System.out.println("Account Balance is "+Bank.customers.get(validateCustomerId-1).accountBalance);
            Bank.customers.get(validateCustomerId-1).accountBalance = Bank.customers.get(validateCustomerId-1).accountBalance - casWithdrawalAmount;
            System.out.println("New Account Balance is "+Bank.customers.get(validateCustomerId-1).accountBalance);
            CustomerFileNameReader.writeCustomerTransactions(validateCustomerId,"Withdrawal",casWithdrawalAmount);
        }
    }
    public static void moneyTransferFunction(int transfertoCustomerId, double amounttobeTransfered, int validateCustomerId) throws IOException {
        System.out.println("Account Balance is "+Bank.customers.get(validateCustomerId-1).accountBalance);
        if(Bank.customers.get(validateCustomerId-1).accountBalance-1000>=amounttobeTransfered)
        {
            Bank.customers.get(transfertoCustomerId-1).accountBalance = Bank.customers.get(transfertoCustomerId-1).accountBalance+amounttobeTransfered;
            Bank.customers.get(validateCustomerId-1).accountBalance = Bank.customers.get(validateCustomerId-1).accountBalance - amounttobeTransfered;
            System.out.println("New Account balance is "+Bank.customers.get(validateCustomerId-1).accountBalance);
            CustomerFileNameReader.writeCustomerTransactions(validateCustomerId,"Money Transfer",amounttobeTransfered);
        }
        else
        {
            System.out.println("Insufficient funds to transfer money, Deposit money or Try a different amount");
        }
    }
}
