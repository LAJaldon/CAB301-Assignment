import java.util.Scanner;

public class Main {
    public static String staffUserName = "staff";
    public static String staffPassword = "today123";

    //determines which member is logged in
    public static int latest_key = 0;

    public static boolean staffLogin(String userNameInput, String passwordInput){
        boolean hasAccess = false;

        if (userNameInput.equals(staffUserName) && passwordInput.equals(staffPassword)){
            hasAccess = true;
        }
        return hasAccess;
    }

    public static void staffLoginProcess(){
        System.out.println("Username: ");
        Scanner staffInput = new Scanner(System.in);
        String userName = staffInput.nextLine();
        System.out.println("Password: ");
        String password = staffInput.nextLine();
        if (!staffLogin(userName,password)){
            System.out.println("Invalid username or password\n\n");
            mainMenu();
        }
        else {
            staffMenu();
        }
    }

    public static boolean memberLogin(String userNameInput, int passwordInput){
        boolean hasAccess = false;

        if(MemberCollection.members[0] != null) {
            int passwordKey = MemberCollection.findPasswordKey(userNameInput);
            latest_key = MemberCollection.findPasswordKey(userNameInput);
            if (userNameInput.equals(MemberCollection.members[passwordKey].getUserName())
                    && passwordInput == MemberCollection.members[passwordKey].getPassword()) {
                hasAccess = true;
            }
        }
        return hasAccess;
    }

    public static void memberLoginProcess(){
        System.out.println("Enter your Username(LastnameFirstname): ");
        Scanner staffInput = new Scanner(System.in);
        String userName = staffInput.nextLine();
        System.out.println("Password: ");
        int password = staffInput.nextInt();
        if (!memberLogin(userName,password)){
            System.out.println("Invalid username or password\n\n");
            mainMenu();
        }
        else {
            memberMenu();
        }
    }

    public static void mainMenu(){
        System.out.println("\nWelcome to the Community library");
        System.out.println("=========Main Menu=========");
        System.out.println("1. Staff Login");
        System.out.println("2. Member Login");
        System.out.println("0. Exit");
        System.out.println("===================\n");
        System.out.println("Please make a selection (1-2, or 0 to exit): ");

        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        switch(num){
            case 0:
                System.exit(0);
                break;
            case 1:
                staffLoginProcess();
                staffMenu();
                break;
            case 2:
                memberLoginProcess();
                memberMenu();
                break;
        }
    }

    public static int setPassword(){
        boolean valid = false;
        int password = 0;
        while (!valid){
            System.out.println("Enter member's password(4 digits): ");
            Scanner input = new Scanner(System.in);
            if(input.hasNextInt()) {
                int num = input.nextInt();
                if (num > 999 & num < 10000) {
                    password = num;
                    valid = true;
                } else {
                    System.out.println("Password must be 4 digits");
                }
            }
            else {
                System.out.println("Password must be 4 digits");
            }
        }
        return password;
    }

    public static void staffMenu() {
        System.out.println("\n=========Staff Menu=========");
        System.out.println("1. Add a new movie DVD");
        System.out.println("2. Remove a movie DVD");
        System.out.println("3. Register a new member");
        System.out.println("4. Find a registered member's phone number");
        System.out.println("0. Return to main menu");
        System.out.println("===================\n");
        System.out.println("Please make a selection (1-4, or 0 to return to main menu): ");

        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        switch (num) {
            case 0:
                mainMenu();
                break;
            case 1:
                MovieCollection.addMovie();
                staffMenu();
                break;
            case 2:
                Scanner deleteInput = new Scanner(System.in);
                System.out.println("Enter the Movie title: ");
                String movieDelete = deleteInput.nextLine();
                MovieArray.deleteMovie(movieDelete);
                MovieCollection.delete(new Movie(movieDelete));
                for (int i =0; i<= MemberCollection.noOfMembers; i++){
                    if(MemberCollection.members[i] != null) {
                        MemberCollection.members[i].deleteBorrowedMovie(movieDelete);
                    }
                }
                System.out.println(movieDelete + " has been deleted");
                staffMenu();
            case 3:
                Scanner userInput = new Scanner(System.in);
                System.out.println("Enter member's first name: ");
                String firstName = userInput.nextLine();
                System.out.println("Enter member's last name:");
                String lastName = userInput.nextLine();
                if(!MemberCollection.checkIfDuplicate(firstName,lastName)) {
                    System.out.println("Enter member's address: ");
                    String address = userInput.nextLine();
                    System.out.println("Enter member's phone number:");
                    String phoneNumber = userInput.nextLine();
                    int password = setPassword();
                    MemberCollection.registerMember(firstName, lastName, address, phoneNumber, password);
                }
                else {
                    System.out.println(firstName + " " + lastName + " has already registered.\n");
                }
                staffMenu();
                break;
            case 4:
                Scanner newUserInput = new Scanner(System.in);
                System.out.println("Enter member's first name: ");
                String newFirstName = newUserInput.nextLine();
                System.out.println("Enter member's last name:");
                String newLastName = newUserInput.nextLine();
               if (MemberCollection.checkIfDuplicate(newFirstName,newLastName)) {
                   MemberCollection.grabPhoneNumber(newFirstName, newLastName);
                   staffMenu();
               }
               else {
                   System.out.println(newFirstName + " "+ newLastName + " is not a member\n");
                   staffMenu();
               }

        }
    }

    public static void memberMenu(){
        System.out.println("\n==========Member Menu==========");
        System.out.println("1. Display all movies");
        System.out.println("2. Borrow a movie DVD");
        System.out.println("3. Return a movie DVD");
        System.out.println("4. List current borrowed movie DVDs");
        System.out.println("5. Display top 10 most popular movies");
        System.out.println("0. Return to main menu");
        System.out.println("===================\n");
        System.out.println("Please make a selection (1-5, or 0 to return to main menu): ");

        Scanner input = new Scanner(System.in);
        if (input.hasNextInt()) {
            int num = input.nextInt();
            switch (num) {
                case 0:
                    mainMenu();
                    break;
                case 1:
                    System.out.println("List of Movies: ");
                    MovieCollection.displayMovie(MovieCollection.root);
                    memberMenu();
                    break;
                case 2:
                    if (MemberCollection.members[latest_key].getMoviesRented() < 10) {
                        MovieArray.borrowMovie();
                    } else {
                        System.out.println("You have borrowed the maximum number of movies");
                    }
                    memberMenu();
                    break;
                case 3:
                    System.out.println("Enter movie title: ");
                    Scanner movieInput = new Scanner(System.in);
                    String returnedMovie = movieInput.nextLine();
                    MemberCollection.members[latest_key].returnMovie(returnedMovie);
                    for (int i = 0; i <= MovieArray.movies.length - 1; i++) {
                        if (MovieArray.movies[i] != null) {
                            if (MovieArray.movies[i].getMovie().equals(returnedMovie)) {
                                MovieArray.movies[i].returnMovie();
                            }
                        }
                    }
                    System.out.println("\nMovie DVD has been returned");
                    memberMenu();
                    break;
                case 4:
                    System.out.println("You are currently borrowing: ");
                    MemberCollection.members[latest_key].printBorrowedMovies();
                    memberMenu();
                    break;
                case 5:
                    try {
                        MovieArray.switchNull(MovieArray.movies);
                        MovieArray.displayTopMovies();
                        memberMenu();
                    }
                    catch (Exception e){
                        System.out.println("Something went wrong.");
                        memberMenu();
                    }
                    break;
            }
        }
        else {
            System.out.println("Wrong input");
            memberMenu();
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
