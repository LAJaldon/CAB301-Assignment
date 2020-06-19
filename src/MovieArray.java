import java.util.Scanner;

/* ********************************************************************
 * The MovieArray class stores the objects of the movies inside an
 * array with the purpose of counting the number of copies borrowed
 * by the member in order to display the Top 10 Movies
 *********************************************************************/
public class MovieArray {
    public static int movieCount;
    static Movie[] movies = new Movie[50];

    public static void addToArray(String movieName, String duration, String releaseDate, int copies){
        movies[movieCount] = new Movie(movieName);
        movies[movieCount].setDuration(duration);
        movies[movieCount].setReleaseDate(releaseDate);
        movies[movieCount].setCopies(copies);
    }

    public static void addMovie(String movieName, String duration, String releaseDate, int copies){
        if(movieCount == movies.length-1){
            System.out.println("Maximum number of movies added\n");
        }
        else {
            addToArray(movieName, duration, releaseDate, copies);
            movieCount++;
        }
    }

    /* ********************************************************************
     * Func: A function used to change the value of the movie object in
     * the array to null
     *
     * param movieName: The name of the movie being deleted
     *********************************************************************/
    public static void deleteMovie(String movieName){
        for(int i =0; i <= movies.length - 1; i++){
            if (movies[i] != null) {
                if (movies[i].getMovie().equals(movieName)) {
                    movies[i] = null;
                }
            }
        }
        switchNull(movies);
    }

    /* ********************************************************************
     * Func: A function used to check if the name of the movie being
     *  borrowed is already in the array
     *
     * param movieName: The name of the movie being borrowed
     * return duplicate: true or false depending if the movie is already in the
     * array
     *********************************************************************/
    public static boolean checkIfDuplicate(String movieName){
        boolean duplicate = false;
        for (int i = 0; i <= movieCount; i++){
            if(movies[i] != null) {
                if (movies[i].getMovie().equals(movieName)) {
                    duplicate = true;
                }
            }
        }
        return duplicate;
    }

    /* ********************************************************************
     * Func: A function used to add more dvd copies of the movie entered
     *
     * param movieName: The name of the movie which will have more copies
     * added
     *
     * param copies: number of copies to add
     *********************************************************************/
    public static void addMoreCopies(String movieName, int copies){
        for(int i =0; i <= movies.length - 1; i++){
            if (movies[i] != null) {
                if (movies[i].getMovie().equals(movieName)) {
                    movies[i].addCopies(copies);
                }
            }
        }
    }


    /* ********************************************************************
     * Func: A function used to check if the number of copies that the
     *  member wants to borrow is greater than zero
     *
     * param movieName: The name of the movie being borrowed
     * return noOfCopies: the number of copies that the movie object has
     *********************************************************************/
    public static int checkCopies(String movieName){
        int noOfCopies = 0;
        for (int i =0; i< movies.length; i++){
            if(movies[i] != null){
                if(movies[i].getMovie().equals(movieName)){
                    noOfCopies = movies[i].getCopies();
                }
            }
        }
        return noOfCopies;
    }

    /* ********************************************************************
     * Func: A function used to count the number of movies in the array
     *
     * param Arr: The array which contains the movie objects
     * return movieCount: The value of number of movies in the array
     *********************************************************************/
    public static int countMovies(Movie[] Arr){
        movieCount = 0;
        for (int i =0; i<Arr.length; i++){
            if (Arr[i] != null){
                movieCount++;
            }
        }
        return movieCount;
    }

    /* ********************************************************************
     * Func: A function used to shift the null objects from movie array to
     * the right so it is no longer included when displaying the Top 10
     * movies
     *
     * param movieName: The array which contains the movie objects
     *********************************************************************/
    public static void switchNull(Movie[] Arr){
        int movieCount = countMovies(Arr);

        for (int i =0; i<movieCount; i++)
            if (Arr[i] == null){
                while (Arr[i+1]!= null){
                    if (Arr[i+1] != null){
                        Arr[i] = Arr[i+1];
                        Arr[i+1] = null;
                    }
                }
            }
    }

    public static void borrowMovie(){
        System.out.println("Enter movie title: ");
        Scanner newInput = new Scanner(System.in);
        String borrowedMovie = newInput.nextLine();
        if(!MemberCollection.members[Main.latest_key].checkIfBorrowed(borrowedMovie)) {
            if (MovieArray.checkCopies(borrowedMovie) > 0) {
                MemberCollection.members[Main.latest_key].addMoviesBorrowed(borrowedMovie);
                for (int i = 0; i <= MovieArray.movies.length - 1; i++) {
                    if (MovieArray.movies[i] != null) {
                        if (MovieArray.movies[i].getMovie().equals(borrowedMovie)) {
                            MovieArray.movies[i].borrowMovie();
                            MovieArray.movies[i].countTimesRented();
                        }
                    }
                }
                System.out.println("\nYou borrowed " + borrowedMovie + "\n");
            } else {
                System.out.println("\nAll available copies have been borrowed\n");
            }
        }
        else {
            System.out.println("You have already borrowed a copy of this movie");
        }
    }

    public static void printTopMovies(Movie[] Arr){
        System.out.println("Top 10 Movies:");
        for (int i =0; i<= movieCount ; i++){
            if(Arr[i] != null) {
                System.out.println(Arr[i].getMovie() + " borrowed " + Arr[i].getTimesRented() + " times.");
            }
        }
        System.out.println("\n");
    }

    /* ********************************************************************
     * Func: A function that uses insertion sort to sort the array that
     * contains the movie objects in decreasing order from most borrowed
     * to least borrowed
     *
     *********************************************************************/
    public static void displayTopMovies() {
        for (int i = 1; i < movieCount; i++) {
            if(movies[i] != null) {
                Movie v = movies[i];
                int j = i - 1;
                while (j >= 0 && movies[j].getTimesRented() < v.getTimesRented()) {
                    movies[j + 1] = movies[j];
                    j = j - 1;
                }
                movies[j + 1] = v;
            }
        }
       printTopMovies(movies);
    }
}
