public class Movie {
    private String movieName;
    private String actor;
    private String director;
    private String genre;
    private String classification;
    private String duration;
    private String releaseDate;
    private int copies;
    private int timesRented = 0;

    public Movie(String movieName){
        this.movieName = movieName;
    }

    public String getMovie(){
        return movieName;
    }

    public String setActor(String actor){
        this.actor = actor;
        return actor;
    }

    public String getActor(){
        return actor;
    }

    public String setDirector(String director){
        this.director = director;
        return director;
    }

    public String getDirector(){
        return director;
    }

    public String setGenre(String genre){
        this.genre = genre;
        return genre;
    }

    public String getGenre(){
        return genre;
    }

    public String setClassification(String classification){
        this.classification = classification;
        return classification;
    }

    public String getClassification(){
        return classification;
    }

    public String setDuration(String duration){
        this.duration = duration;
        return duration;
    }

    public String getDuration(){
        return duration;
    }

    public String setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
        return releaseDate;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public int setCopies(int copies){
        this.copies = copies;
        return copies;
    }

    public int getCopies(){
        return copies;
    }

    public void addCopies(int moreCopies){
        copies += moreCopies;
    }

    public void borrowMovie(){
        copies --;
    }

    public void returnMovie(){
        copies ++;
    }

    public void countTimesRented(){
        timesRented++;
    }

    public int getTimesRented(){
        return timesRented;
    }

}
