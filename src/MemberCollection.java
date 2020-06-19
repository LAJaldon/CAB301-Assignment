/* ********************************************************************
 * The MemberCollection class stores the objects of members into an
 * array to allow the user to set and grab the properties of a member
 *********************************************************************/
public class MemberCollection {
    public static int noOfMembers =0;
    static Member[] members = new Member[10];

    /* ********************************************************************
     * Func: A function used to check if the member is already in the array
     *
     * param firstName: The first name of the member
     *
     * param lastName: The last name of the member
     * return duplicate: true or false depending if the username is
     * already in the array
     *********************************************************************/
    public static boolean checkIfDuplicate(String firstName, String lastName){
        String userName = lastName+firstName;

        boolean duplicate = false;
        for (int i = 0; i <= noOfMembers; i++){
            if(members[i] != null) {
                if (members[i].getUserName().equals(userName)) {
                    duplicate = true;
                }
            }
        }
        return duplicate;
    }

    public static void addToArray(String firstName, String lastName, String address, String phoneNumber, int password){
            members[noOfMembers] = new Member(firstName, lastName);
            members[noOfMembers].setAddress(address);
            members[noOfMembers].setPhoneNumber(phoneNumber);
            members[noOfMembers].setPassword(password);
            System.out.println("Member username is: " + members[noOfMembers].setUserName());
            System.out.println(firstName + " " + lastName + " has successfully registered.\n" );
    }

    public static void registerMember(String firstName, String lastName, String address, String phoneNumber, int password){
        if(noOfMembers == members.length-1){
            System.out.println("Maximum number of members exceeded\n");
        }
        else {
            addToArray(firstName, lastName, address, phoneNumber, password);
            noOfMembers++;
        }
    }

    /* ********************************************************************
     * Func: A function used to return the index of the member in the array
     * whose phone number the staff wants to find
     *
     * param firstName: The first name of the member
     *
     * param lastName: The last name of the member
     * return key: the value of the index of the member in the array
     *********************************************************************/
    public static int findPersonKey(String firstName, String lastName){
        String userName = lastName + firstName;
        int key = 0;

        for (int i =0; i < noOfMembers; i++){
            if (members[i].getUserName().equals(userName)){
                key = i;
            }
        }
        return key;
    }

    /* ********************************************************************
     * Func: A function used to return the index of the member in the array
     * whose password is needed to login
     *
     * param userName: The user name of the member
     * return key: the value of the index of the member in the array
     *********************************************************************/
    public static int findPasswordKey(String userName){
        int key = 0;

        for (int i =0; i < noOfMembers; i++){
            if (members[i].getUserName().equals(userName)){
                key = i;
            }
        }
        return key;
    }

    public static void grabPhoneNumber(String firstName, String lastName){
        int key = findPersonKey(firstName,lastName);

        System.out.println(firstName + " " + lastName + "'s phone number is: " + members[key].getPhoneNumber());
    }


}
