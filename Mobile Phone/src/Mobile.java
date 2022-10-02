import java.util.*;

public class Mobile {
    private static ArrayList<Contacts> contacts = new ArrayList<>();
    //Store
    //Modify
    //remove
    //printContacts
    private static void addNewContactToList(String newContactName, String newContactPhoneNumber)
    {
        contacts.add(new Contacts(newContactName,newContactPhoneNumber));
        System.out.println("Contact added successfully");
    }

    private  int searchContactInList(String searchContactName)
    {
        //int positionOfContact = contacts.gindexOf(searchContactName);
        for(int i=0; i<contacts.size();i++)
        {
            Contacts contact = contacts.get(i);
            if(contact.getName().equals(searchContactName) || contact.getPhoneNumber().equals(searchContactName)){
                System.out.println("Contact is present");
                return i;
            }
        }
        System.out.println("Contact doesn't exist");
        return -1;
//        if(positionOfContact>=0)
//        {
//            System.out.println("Contact is present");
//        }else System.out.println("Contact doesn't exist");
//        return positionOfContact;
    }
    private static void contactModifyNameInList(String nameToModify,String oldNameOfContact)
    {
        Mobile a = new Mobile();
        int positionOfName = a.searchContactInList(oldNameOfContact);
        String phoneNumber = contacts.get(positionOfName).getPhoneNumber();
        contacts.set(positionOfName, new Contacts(nameToModify,phoneNumber));
        System.out.println("Name of the contact modified Successfully");
    }
    private static void contactModifyPhoneNumberInList(String phoneNumberToModify, String oldPhoneNumberOfContact)
    {
        Mobile b = new Mobile();
        int positionOfName = b.searchContactInList(oldPhoneNumberOfContact);
        String name = contacts.get(positionOfName).getName();
        contacts.set(positionOfName, new Contacts(name,phoneNumberToModify));
        System.out.println("Phone number of the contact modified Successfully");
    }
    private static void removeContactInList(String nameOrPhoneNumber)
    {
        Mobile c = new Mobile();
        int position = c.searchContactInList(nameOrPhoneNumber);
        if(position>=0)
        {
         contacts.remove(position);
         System.out.println("Contact removed successfully");
        }
    }
    private static void printAvailableContacts()
    {
        for(Contacts contact : contacts)
        {
            System.out.println("----------------------------------");
            System.out.println("Name of the contact: "+ contact.getName());
            System.out.println("Name of the contact: "+ contact.getPhoneNumber());
            System.out.println("----------------------------------");
        }
    }
    public static void main(String args[])
    {
        Mobile m = new Mobile();
        Scanner s = new Scanner(System.in);
        boolean choice = true;
        while(choice)
        {
            System.out.println("Select an option\n1.Add contact\n2.Search contact\n3.Modify contact\n4.Remove contact\n5.Print contacts\n6.Exit");
            int selection = s.nextInt();
            switch (selection)
            {
                case 1:
                    //addContact
                    System.out.println("Enter the name and phone number to add a new contact");
                    String newContactName = s.next();
                    String newContactPhoneNumber = s.next();
                    addNewContactToList(newContactName,newContactPhoneNumber);
                    break;
                case 2:
                    //searchContact
                    System.out.println("Enter the name of contact to search");
                    String searchContactName = s.next();
                    m.searchContactInList(searchContactName);
                    break;
                case 3:
                    System.out.println("Enter the name or phone number of the contact to modify");
                    String modifyOldContactName = s.next();
                    if(m.searchContactInList(modifyOldContactName)>=0)
                    {
                        System.out.println("1.Change Name of the contact\n2.Change Number of the contact");
                        int modifyOptionSelection = s.nextInt();
                        switch (modifyOptionSelection)
                        {
                            case 1:
                                System.out.println("Enter the new Name to modify");
                                String nameToModify =s.next();
                                contactModifyNameInList(nameToModify,modifyOldContactName);
                                break;
                            case 2:
                                System.out.println("Enter the new Phone Number to modify");
                                String phoneNumberToModify =s.next();
                                contactModifyPhoneNumberInList(phoneNumberToModify,modifyOldContactName);
                                break;
                            default:
                                System.out.println("Enter a valid option");
                                break;
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter the name or phone number of the contact to remove");
                    String nameOrPhoneNumber = s.next();
                    removeContactInList(nameOrPhoneNumber);
                    break;
                case 5:
                    printAvailableContacts();
                    break;
                case 6:
                    choice = false;
                    System.out.println("------------Exiting------------");
                    break;
                default:
                    System.out.println("Enter a valid option");
                    break;
            }

        }
    }
}
