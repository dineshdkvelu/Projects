import java.util.ArrayList;
import java.util.HashMap;

public class Customer {
    static int customerId = 0;
    public String name;
    public int age;
    public String gender;
    public String password;

    static HashMap<Integer,Booking> bookingDetails = new HashMap<>();
    public Customer(String name, int age, String gender, String password)
    {
        customerId++;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.bookingDetails = intitialBookingDetails(customerId);
    }

    public static boolean validateCustomerDetails(int loginCustomerId,String loginCustomerName, String loginCustomerPassword)
    {
        if(Main.customers.get(loginCustomerId-1).password.equals(loginCustomerPassword) &&
                Main.customers.get(loginCustomerId-1).name.equals(loginCustomerName))
        {
            System.out.println("----------Login Success----------");
            return true;
        }
        else
        {
            System.out.println("----------Invalid Login Credentials----------");
            return false;
        }
    }
    private HashMap<Integer,Booking> intitialBookingDetails(int customerId)
    {
        HashMap <Integer,Booking> initalBookingDetails = new HashMap<>();
        ArrayList<Character> initial = new ArrayList<>();
        initalBookingDetails.put(customerId,(new Booking(0,0,initial,customerId)));
        return initalBookingDetails;
    }
}
