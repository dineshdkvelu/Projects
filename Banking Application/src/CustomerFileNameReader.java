import java.io.*;
import java.util.Scanner;

public class CustomerFileNameReader {
    private static final String filename = "Bank.txt";

    public void initialize() throws IOException {
//        Customer customers[] = new Customer[10];
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
       // String custInfo = reader.readLine();
        while(reader.ready()){
            String custInfo =reader.readLine();
            Bank.customers.add(castStringtoCustomer(custInfo));
        }

    }
    private Customer castStringtoCustomer(String custInfo) throws IOException {
        String[] customer = custInfo.split(" ");
        CustomerFileNameReader.createCustomerFile(Integer.parseInt(customer[0]),customer[2],Double.parseDouble(customer[3]));
        return new Customer(
          Integer.parseInt(customer[0]),
          Integer.parseInt(customer[1]),
          customer[2],
          Double.parseDouble(customer[3]),
          customer[4],
          Customer.encyptCustomerPassword(customer[4]),1
        );
    }

    public static void writeNewCustomerinFile() throws IOException {
        File write = new File(filename);
        BufferedWriter writer = new BufferedWriter(new FileWriter(write,true));
        int size = Bank.customers.size()-1;
        String newCustomer = Bank.customers.get(size).customerId+" "+Bank.customers.get(size).accountNumber+" "+Bank.customers.get(size).name
                +" "+Bank.customers.get(size).accountBalance+" "+Bank.customers.get(size).password;
        writer.newLine();
        writer.write(newCustomer);
        writer.close();
    }

    public static void createCustomerFile() throws IOException {
        int size = Bank.customers.size();
        String name = Bank.customers.get(size-1).name;
        File customerFile = new File("C:\\Users\\dinesh.bh.kumar\\IdeaProjects\\Banking Application\\"+name+".txt");
        customerFile.createNewFile();
        CustomerFileNameReader.writeCustomerTransactions(size,"Opening",10000);
    }
    public static void createCustomerFile(int custID, String name, double initialAmount) throws IOException {
        File customerFile = new File("C:\\Users\\dinesh.bh.kumar\\IdeaProjects\\Banking Application\\"+name+".txt");
        customerFile.createNewFile();
        CustomerFileNameReader.writeCustomerTransactions(1,name,"Opening",initialAmount);
    }
    public static void writeCustomerTransactions(int transactionId, String name, String  transactionType, double amount) throws IOException {
        File transactionWrite = new File("C:\\Users\\dinesh.bh.kumar\\IdeaProjects\\Banking Application\\"+name+".txt");
        String transactionDetails = transactionId+" "+transactionType+" "+amount+" "+amount;
        BufferedWriter writer =new BufferedWriter( new FileWriter(transactionWrite,true));
        writer.newLine();
        writer.write(transactionDetails);
        writer.close();
    }
    public static void writeCustomerTransactions(int validateCustomerId, String  transactionType,double amount) throws IOException {
        Bank.customers.get(validateCustomerId-1).transactionId = Bank.customers.get(validateCustomerId-1).transactionId +1;
        File transactionWrite = new File("C:\\Users\\dinesh.bh.kumar\\IdeaProjects\\Banking Application\\"+Bank.customers.get(validateCustomerId-1).name+".txt");
        String transactionDetails = Bank.customers.get(validateCustomerId-1).transactionId+" "+transactionType+" "+amount+" "+Bank.customers.get(validateCustomerId-1).accountBalance;
        BufferedWriter writer =new BufferedWriter( new FileWriter(transactionWrite,true));
        writer.newLine();
        writer.write(transactionDetails);
        writer.close();
    }

}
