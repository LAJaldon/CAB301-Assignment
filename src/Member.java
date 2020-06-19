public class Member {
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private int password;
    private String address;
    private int moviesRented = 0;
    private int totalMoviesBorrowed = 0;
    private Movie[] borrowedMovies = new Movie[30];

    public Member(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String setUserName(){
        userName = lastName+firstName;
        return userName;
    }

    public String getUserName(){
        return userName;
    }

    public String setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
        return phoneNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public int setPassword(int password){
        this.password = password;
        return password;
    }

    public int getPassword(){
        return password;
    }

    public String setAddress(String address){
        this.address = address;
        return address;
    }

    public String getAddress(){
        return address;
    }

    public int getMoviesRented(){
        return moviesRented;
    }

    public void addMoviesBorrowed(String movieName){
        borrowedMovies[totalMoviesBorrowed] = new Movie(movieName);
        totalMoviesBorrowed++;
        moviesRented++;
    }

    /* *******************************************************************
      * Func: A function that uses the sequential search algorithm
      * to return a movie that the member has borrowed
      *
      * param movieName: The name of the movie being returned
    *********************************************************************/
    public void returnMovie(String movieName){
        //using Sequential Search Algorithm
        boolean notNull = false;
        int i =0;
        while (i< totalMoviesBorrowed && !notNull){
                if(borrowedMovies[i] != null){
                    if (borrowedMovies[i].getMovie().equals(movieName)){
                        borrowedMovies[i] = null;
                        notNull = true;
                    }
                }
                i++;
            }
        moviesRented --;
        }

    /* *******************************************************************
     * Func: A function used to check if the movie being borrowed has
     *  already been borrowed by the member
     *
     * param movieName: The name of the movie being borrowed
     * return alreadyBorrowed: true or false depending if the movie is
     * already in the array
     *********************************************************************/
        public boolean checkIfBorrowed(String movieName){
            boolean alreadyBorrowed = false;
            int i =0;
            while (i < totalMoviesBorrowed && !alreadyBorrowed){
                if(borrowedMovies[i] != null){
                    if(borrowedMovies[i].getMovie().equals(movieName)){
                        alreadyBorrowed = true;
                    }
                }
                i++;
            }
            return alreadyBorrowed;

        }

    /* *******************************************************************
     * Func: A function used to print the names of the movies that
     *  the member has borrowed
     *
     *********************************************************************/
    public void printBorrowedMovies(){
        for (int i = 0; i< totalMoviesBorrowed; i++){
            if(borrowedMovies[i] != null){
                System.out.println(borrowedMovies[i].getMovie());
            }
        }
    }

    /* *******************************************************************
     * Func: A function used to delete the movie from the array of movies
     *  that the member has borrowed
     *
     * param movieName: The name of the movie being deleted
     *********************************************************************/
    public void deleteBorrowedMovie(String movieName){
        for (int i =0; i< totalMoviesBorrowed; i++){
            if(borrowedMovies[i]!= null){
                if(borrowedMovies[i].getMovie().equals(movieName)){
                    borrowedMovies[i] = null;
                }
            }
        }
    }

}
