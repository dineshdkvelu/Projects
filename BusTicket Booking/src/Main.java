import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        boolean selection = true;
        Scanner s = new Scanner(System.in);
        Bus.initialize();
        //Bus.printSeats(Bus.bus.get(0).seats);
        while(selection)
        {
            System.out.println();
            System.out.println("1.Customer Sign-up\n2.Customer Login\n3.Exit");
            int choice = s.nextInt();
            switch (choice)
            {
                case 1:
                {
                    System.out.println("Enter the name, age, gender & password to create account");
                    String name = s.next();
                    int age = s.nextInt();
                    String gender = s.next();
                    String password = s.next();
                    customers.add(new Customer(name,age,gender,password));
                    System.out.println("----------New Customer Created----------");
                    System.out.println("Customer id:"+Customer.customerId);
                    int i =Customer.customerId-1;
                    System.out.println(customers.get(i).name+" "+customers.get(i).age);
                }
                break;
                case 2:
                {
                    //customerLogin
                    System.out.println("Enter the Id, name and password to login");
                    int loginCustomerId = s.nextInt();
                    String loginCustomerName = s.next();
                    String loginCustomerPassword =s.next();
                    if(Customer.validateCustomerDetails(loginCustomerId,loginCustomerName,loginCustomerPassword))
                    {
                        boolean customerSelection = true;
                        while(customerSelection)
                        {
                            System.out.println();
                            System.out.println("1.View Available Tickets\n2.Book Tickets\n3.Cancel Tickets\n4.Booking Details\n5.Logout");
                            int customerChoice = s.nextInt();
                            switch (customerChoice)
                            {
                                case 1:
                                {
                                    System.out.println("Select the type of coach and tier");
                                    System.out.println("1.AC and Seater\n2.AC and Sleeper\n3.Non-AC and Seater\n4.Non-Ac and Sleeper");
                                    int busChoice = s.nextInt();
                                    switch (busChoice)
                                    {
                                        case 1:
                                            System.out.println("Selected AC and Seater");
                                            Bus.printSeats(Bus.bus.get(0).seats);
                                            break;
                                        case 2:
                                            System.out.println("Selected AC and Sleeper");
                                            Bus.printSeats(Bus.bus.get(1).seats);
                                            break;
                                        case 3:
                                            System.out.println("Selected Non-AC and Seater");
                                            Bus.printSeats(Bus.bus.get(2).seats);
                                            break;
                                        case 4:
                                            System.out.println("Selected Non-AC and Sleeper");
                                            Bus.printSeats(Bus.bus.get(3).seats);
                                            break;
                                        default:
                                            System.out.println("Enter a valid option");
                                            break;
                                    }
                                }
                                break;
                                case 2:
                                {
                                    //bookTickets();
                                    System.out.println("Enter the Number of seats to be booked");
                                    int numberofseatstobeBooked = s.nextInt();
                                    System.out.println("Enter the tier of bus");
                                    int optionTierSelected = s.nextInt();
                                    // int a[] = new int[numberofseatstobeBooked];
                                    ArrayList<Character> seatstobeBooked = new ArrayList<>();
                                    for(int i=0; i<numberofseatstobeBooked; i++){
                                        System.out.println("Enter the seat number");
                                        seatstobeBooked.add(s.next().charAt(0));
                                    }
                                    if(Booking.checkAvailabilityofSeat(optionTierSelected,seatstobeBooked))
                                    {
                                        Customer.bookingDetails.put(loginCustomerId, (new Booking(optionTierSelected,numberofseatstobeBooked,seatstobeBooked,loginCustomerId)));
                                        Booking.bookSeatsInSelectedTier(optionTierSelected,seatstobeBooked);
                                    }else
                                    {
                                        System.out.println("Try selecting different seats");
                                    }

                                }
                                break;
                                case 3:
                                {
                                    //cancelTickets();
                                    System.out.println("Enter the customer id");
                                    System.out.println("You have selected the seats");
                                    System.out.println(Customer.bookingDetails.get(loginCustomerId).seatsBooked);
                                    System.out.println("Enter the number of tickets to cancel");
                                    int numberofSeatstoCancel = s.nextInt();
                                    ArrayList<Character> seatsToBeCanceled = new ArrayList<>();
                                    for(int i=0; i<numberofSeatstoCancel; i++){
                                        System.out.println("Enter the seat number");
                                        seatsToBeCanceled.add(s.next().charAt(0));
                                    }
                                    if(Booking.cancelationOfBookedSeats(numberofSeatstoCancel, seatsToBeCanceled,Customer.bookingDetails.get(loginCustomerId).tier))
                                    {
                                        System.out.println("----------Cancellation Done----------");
                                    }
                                }
                                break;
                                case 4:
                                {
                                    //bookingDetails();
                                    System.out.println("----------Booking details----------");
                                    System.out.println("Customer ID: "+loginCustomerId);
                                    System.out.println("Tier chosen "+Customer.bookingDetails.get(loginCustomerId).tier);
                                    System.out.println("Number of Tickets Booked "+Customer.bookingDetails.get(loginCustomerId).numberofSeatsBooked);
                                    System.out.println("Seats Booked are "+Customer.bookingDetails.get(loginCustomerId).seatsBooked);
                                    System.out.println("Cost of tickets "+Customer.bookingDetails.get(loginCustomerId).bookedSeatCost);
                                }
                                break;
                                case 5:
                                {
                                    customerSelection =false;
                                    System.out.println("----------Logout Success----------");
                                }
                                break;
                                default:
                                {
                                    System.out.println("----------Select a valid option----------");
                                }
                                break;
                            }
                        }
                    }
                }
                break;
                case 3:
                {
                    selection = false;
                    System.out.println("----------Exiting program----------");
                }
                break;
                default:
                {
                    System.out.println("----------Select a valid option----------");
                }
                break;
            }
        }
    }
}
