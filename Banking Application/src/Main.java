import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s= new Scanner(System.in);
        CustomerFileNameReader customerFileNameReader = new CustomerFileNameReader();
        customerFileNameReader.initialize();
        boolean selection = true;
        while(selection){
            System.out.println("Welcome to ABC bank");
            System.out.println("1.Add Customer\n2.User Login\n3.Exit");
            int choice = s.nextInt();
            switch (choice)
            {
                case 1:
                {
                    System.out.println("Enter the following details");
                    String nameofNewCustomer = s.next();
                    String newCustomerPassword = s.next();
                    if(Customer.passwordChecker(newCustomerPassword))
                    {
                        Customer.addCustomer(nameofNewCustomer,newCustomerPassword);
                        //System.out.println(Bank.customers.get(3).customerId+" "+Bank.customers.get(3).accountNumber);
                        //int lastEntry = Bank.customers.size()-1;
                    }
                    else
                    {
                        System.out.println("Password does not match, Try Again");
                    }

                }
                break;
                case 2:
                {
                    System.out.println("Enter the customer id, Account number and password");
                    int validateCustomerId = s.nextInt();
                    int validateCustomerAccountNumber = s.nextInt();
                    String validateCustomerPassword = s.next();
                    if(Customer.validateExistingUser(validateCustomerId,validateCustomerAccountNumber,validateCustomerPassword))
                    {
                        boolean customerOperations =true;
                        while(customerOperations){
                            System.out.println("1.Cash Deposit\n2.Withdrawal\n3.Money Transfer\n4.Logout");
                            int userChoice = s.nextInt();
                            switch (userChoice)
                            {
                                case 1:
                                {
                                    System.out.println("Enter the amount to be deposited");
                                    double cashDepositAmount = s.nextDouble();
                                    Customer.cashDepositFunction(cashDepositAmount,validateCustomerId);

                                }
                                break;
                                case 2:
                                {
                                    System.out.println("Entert the withdrawal amount");
                                    double casWithdrawalAmount = s.nextDouble();
                                    Customer.cashWithdrawalFunction(casWithdrawalAmount,validateCustomerId);
                                }
                                break;
                                case 3:
                                {
                                    System.out.println("Enter the customer id to transfer the amount");
                                    int transfertoCustomerId = s.nextInt();
                                    System.out.println("Enter the amount to be transfered");
                                    double amounttobeTransfered = s.nextDouble();
                                    Customer.moneyTransferFunction(transfertoCustomerId,amounttobeTransfered,validateCustomerId);
                                }
                                break;
                                case 4:
                                {
                                    System.out.println("------------Logout Success------------");
                                    customerOperations =false;
                                }
                                break;
                                default:
                                {
                                    System.out.println("Enter a valid option");
                                }
                                break;
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Invalid Customer Details-------- Login Failed, Try Again");
                    }

                }
                break;
                case 3:
                {
                    selection = false;
                }
                break;
                default:
                {
                    System.out.println("Enter a valid option");
                }
                break;
            }
        }
    }
}
