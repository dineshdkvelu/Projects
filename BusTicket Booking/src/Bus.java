import java.util.ArrayList;

public class Bus {
    public String coach;
    public String tier;
    public int lowerTier;
    public int upperTier;
    char seats[][] = new char[3][5];
    //ArrayList<String> seats = new ArrayList<>();

    static ArrayList<Bus> bus = new ArrayList<>();

    public Bus(String coach,String tier)
    {
        this.coach = coach;
        this.tier = tier;
        this.lowerTier = 6;
        this.upperTier =6;
        this.seats=initialCharArray();
    }
    public static void initialize()
    {
        bus.add(new Bus("AC","Seater"));
        bus.add(new Bus("AC","Sleeper"));
        bus.add(new Bus("Non-AC","Seater"));
        bus.add(new Bus("Non-AC","Sleeper"));

    }
    private char[][] initialCharArray()
    {
        int count=0;
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<5; j++)
            {
                if(j==2)
                {
                    seats[i][j]=' ';
                }
                else
                {
                    count++;
                    seats[i][j] =(char)count;
                }
            }
        }
        return seats;
    }
    public static void printSeats(char[][] seats)
    {
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<5; j++)
            {
                if(seats[i][j]!=' ' && seats[i][j]!='X')
                {
                    System.out.print((int)seats[i][j]);
                }else System.out.print(seats[i][j]);
            }
            System.out.println();
        }

    }

}
