import java.util.ArrayList;

public class Booking {
    public int tier;
    public int numberofSeatsBooked;
    ArrayList<Character> seatsBooked = new ArrayList<>();
    public int bookedSeatCost =0;
    public static final int  availableSeatCost = 300;
    public static int customerId;

    public Booking(int tier, int numberofSeatsBooked, ArrayList<Character> seatsBooked, int customerId)
    {
        this.tier = tier;
        this.numberofSeatsBooked =numberofSeatsBooked;
        this.seatsBooked = seatsBooked;
        this.bookedSeatCost = numberofSeatsBooked*availableSeatCost;
        this.customerId =customerId;
    }
    public static void bookSeatsInSelectedTier(int optionTierSelected, ArrayList<Character> a)
    {
        int numberofBookings = a.size();
        System.out.println(Customer.bookingDetails.get(customerId).bookedSeatCost);
        System.out.println(customerId);
        for(int i=0; i<numberofBookings; i++)
        {
            int s= a.get(i)-48;
            for(int k=0; k<3; k++)
            {
                for(int j=0; j<5; j++)
                {
                    int b =Bus.bus.get(optionTierSelected-1).seats[k][j];
                    if(b==s)
                    {
                        Bus.bus.get(optionTierSelected-1).seats[k][j]='X';
                    }
                }
            }
        }
        System.out.println("----------Booking Success----------");
        System.out.println("Booking cost is "+Customer.bookingDetails.get(customerId).bookedSeatCost);
    }

    public static boolean checkAvailabilityofSeat(int optionTierSelected,ArrayList<Character> a)
    {
        int count =0;
        for(int i=0; i< a.size(); i++)
        {
            int s= a.get(i)-48;
            System.out.println(a.get(i));
            for(int j=0; j<3; j++)
            {
                for (int k=0; k<5; k++)
                {
                    int b = Bus.bus.get(optionTierSelected-1).seats[j][k];
                    if(s==b)
                    {
                        count++;
                    }
                }
            }
        }
        if(count== a.size()){
            System.out.println("Chosen seats are available");
            return true;
        }else
        {
            System.out.println(" Chosen seats are not available");
        }
        return false;
    }
    public static boolean cancelationOfBookedSeats(int numberofSeatstoCancel, ArrayList<Character> seatstobeCanceled, int tier)
    {
        int i=0;
            for(int k=0; k<3; k++)
            {
                for(int j=0; j<5; j++)
                {
                    char b =Bus.bus.get(tier-1).seats[k][j];
                    if(b=='X')
                    {
                        Bus.bus.get(tier-1).seats[k][j]=(char)(seatstobeCanceled.get(i++)-48);
                        if(i==seatstobeCanceled.size()){
                            return true;
                        }
                    }
                }
            }
            return false;
    }
}
