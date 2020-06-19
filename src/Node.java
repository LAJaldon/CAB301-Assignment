class Node {
    Movie movie;
    Node left;
    Node right;

    Node(Movie movie) {
        this.movie = movie;
        right = null;
        left = null;
    }
}