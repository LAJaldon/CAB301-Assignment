import java.util.Scanner;

/* ********************************************************************
 * The MovieCollection class creates the binary search tree to allow
 * the user to add movie objects as nodes as well as delete them
 *********************************************************************/
public class MovieCollection {
    static Node root;

    private static Node addRecursive(Node current, Movie movie) {
        if (current == null) {
            return new Node(movie);
        }

        int compareMovies = movie.getMovie().compareTo(current.movie.getMovie());

        if (compareMovies < 0) {
            current.left = addRecursive(current.left, movie);
        } else if (compareMovies > 0) {
            current.right = addRecursive(current.right, movie);
        } else {
            // value already exists
            return current;
        }
        return current;
    }

    public static void add(Movie movie) {
        root = addRecursive(root, movie);
    }

    public static Node deleteRecursive(Node current, Movie movie) {
        if (current == null) {
            return null;
        }

        if (movie.getMovie().equals(current.movie.getMovie())) {
            //when there's no children
            if (current.left == null && current.right == null) {
                return null;
            }
            //When there's one child
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            //When there's two children
            Movie smallestValue = findSmallestValue(current.right);
            current.movie = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        int compareMovies = movie.getMovie().compareTo(current.movie.getMovie());

        if (compareMovies < 0) {
            current.left = deleteRecursive(current.left, movie);
            return current;
        }
        current.right = deleteRecursive(current.right, movie);
        return current;
    }

    public static void delete(Movie movie) {
        root = deleteRecursive(root, movie);
    }

    private static Movie findSmallestValue(Node node) {
        Movie minv = node.movie;
        while (node.left != null){
            minv = node.left.movie;
            node = node.left;
        }
        return minv;
    }

    public static void displayDuration(Node node){
        for (int i =0; i< MovieArray.movieCount; i++){
            if(MovieArray.movies[i] != null){
                if (MovieArray.movies[i].getMovie().equals(node.movie.getMovie())){
                    System.out.println("Duration: " + MovieArray.movies[i].getDuration() + " minutes");
                }
            }
        }
    }

    public static void displayReleaseDate(Node node){
        for (int i =0; i< MovieArray.movieCount; i++){
            if(MovieArray.movies[i] != null){
                if (MovieArray.movies[i].getMovie().equals(node.movie.getMovie())){
                    System.out.println("Release Date: " + MovieArray.movies[i].getReleaseDate());
                }
            }
        }
    }

    public static void displayCopies(Node node){
        for (int i = 0; i< MovieArray.movieCount; i++){
            if(MovieArray.movies[i] != null) {
                if (MovieArray.movies[i].getMovie().equals(node.movie.getMovie())) {
                    System.out.println("Copies Available: " + MovieArray.movies[i].getCopies());
                }
            }
        }
    }

    public static void displayTimesRented(Node node){
        for (int i = 0; i< MovieArray.movieCount; i++){
            if(MovieArray.movies[i] != null){
                if(MovieArray.movies[i].getMovie().equals(node.movie.getMovie())){
                    System.out.println("Times Borrowed: " + MovieArray.movies[i].getTimesRented() );
                }
            }
        }
    }

    /* ********************************************************************
     * Func: A function used to traverse the binary tree in order and
     * display the movies in alphabetical order
     *
     * param node: a node of the Binary Search Tree
     *********************************************************************/
    public static void displayMovie(Node node) {
        if (node != null) {
            displayMovie(node.left);
            System.out.println("\nTitle: " + node.movie.getMovie());
            System.out.println("Starring: "+ node.movie.getActor());
            System.out.println("Director: " + node.movie.getDirector());
            System.out.println("Genre: " + node.movie.getGenre());
            System.out.println("Classification: " + node.movie.getClassification());
            displayDuration(node);
            displayReleaseDate(node);
            displayCopies(node);
            displayTimesRented(node);
            displayMovie(node.right);
        }
    }

    public static void setActor(Node current, Movie movie, String actor){
        if (current != null) {
            setActor(current.right, movie, actor);
            setActor(current.left, movie, actor);

            if (movie.getMovie().equals(current.movie.getMovie())) {
                current.movie.setActor(actor);
            }
        }
    }

    public static void setDirector(Node current, Movie movie, String director){
        if (current != null) {
            setDirector(current.right, movie, director);
            setDirector(current.left, movie, director);

            if (movie.getMovie().equals(current.movie.getMovie())) {
                current.movie.setDirector(director);
            }
        }
    }

    public static void setGenre(Node current, Movie movie, String genre){
        if (current != null) {
            setGenre(current.right, movie, genre);
            setGenre(current.left, movie, genre);
            if (movie.getMovie().equals(current.movie.getMovie())) {
                current.movie.setGenre(genre);
            }
        }
    }

    public static void setClassification(Node current, Movie movie, String classification){
        if (current != null) {
            setClassification(current.right, movie, classification);
            setClassification(current.left, movie, classification);
            if (movie.getMovie().equals(current.movie.getMovie())) {
                current.movie.setClassification(classification);
            }
        }
    }

    public static void printGenreOptions(){
        System.out.println("\nSelect the genre: ");
        System.out.println("1. Drama");
        System.out.println("2. Adventure");
        System.out.println("3. Family");
        System.out.println("4. Action");
        System.out.println("5. Sci-Fi");
        System.out.println("6. Comedy");
        System.out.println("7. Thriller");
        System.out.println("8. Other");
        System.out.println("Make selection (1-8): ");
    }

    public static void printClassificationOptions(){
        System.out.println("\nSelect the classification:");
        System.out.println("1. General (G)");
        System.out.println("2. Parental Guidance (PG)");
        System.out.println("3. Mature (M15+)");
        System.out.println("4. Mature Accompanied (MA15+)");
        System.out.println("Make selection(1-4): ");

    }

    /* ********************************************************************
     * Func: A function used to set the duration, release date and number
     * of copies that a movie has and include the information in the
     * array of movie objects
     *
     * param movieName: The name of the movie being added
     *********************************************************************/
    public static void addMovieDetails(String movieName){
        Scanner movieInput = new Scanner(System.in);
        System.out.println("Enter the duration (minutes): ");
        String duration = movieInput.nextLine();
        System.out.println("Enter the release date (year): ");
        String releaseDate = movieInput.nextLine();
        System.out.println("Enter the number of copies available: ");
        int copies = movieInput.nextInt();
        MovieArray.addMovie(movieName, duration, releaseDate, copies);
    }

    public static void addMovie(){
        Scanner movieInput = new Scanner(System.in);
        System.out.println("Enter the movie title: ");
        String movieName = movieInput.nextLine();
        if(!MovieArray.checkIfDuplicate(movieName)) {
            MovieCollection.add(new Movie(movieName));
            System.out.println("Enter the starring actor(s): ");
            String actor = movieInput.nextLine();
            MovieCollection.setActor(MovieCollection.root, new Movie(movieName), actor);
            System.out.println("Enter the director(s): ");
            String director = movieInput.nextLine();
            MovieCollection.setDirector(MovieCollection.root, new Movie(movieName), director);
            printGenreOptions();
            int genreInput = movieInput.nextInt();
            switch (genreInput) {
                case 1:
                    setGenre(root, new Movie(movieName), "Drama");
                    break;
                case 2:
                    setGenre(root, new Movie(movieName), "Adventure");
                    break;
                case 3:
                    setGenre(root, new Movie(movieName), "Family");
                    break;
                case 4:
                    setGenre(root, new Movie(movieName), "Action");
                    break;
                case 5:
                    setGenre(root, new Movie(movieName), "Sci-Fi");
                    break;
                case 6:
                    setGenre(root, new Movie(movieName), "Comedy");
                    break;
                case 7:
                    setGenre(root, new Movie(movieName), "Thriller");
                    break;
                case 8:
                    setGenre(root, new Movie(movieName), "Other");
                    break;
            }
            printClassificationOptions();
            int classification = movieInput.nextInt();
            switch (classification) {
                case 1:
                    setClassification(root, new Movie(movieName), "General (G)");
                    break;
                case 2:
                    setClassification(root, new Movie(movieName), "Parental Guidance (PG)");
                    break;
                case 3:
                    setClassification(root, new Movie(movieName), "Mature (M15+)");
                    break;
                case 4:
                    setClassification(root, new Movie(movieName), "Mature Accompanied (MA15+)");
                    break;
            }
            addMovieDetails(movieName);
        }
        else {
            System.out.println("Enter the number of copies you would like to add: ");
            int extraCopies = movieInput.nextInt();
            MovieArray.addMoreCopies(movieName, extraCopies);
            System.out.println("Added " + extraCopies + " new copies of " + movieName);

        }
    }
}
