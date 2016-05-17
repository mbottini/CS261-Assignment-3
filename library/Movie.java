package library;

import java.util.TreeSet;

class Movie extends Item
{
    public Movie(String title, String director, int nScenes,
            String... keywords) {
        super(title, director, nScenes, keywords);
        return;
    }   

    public String getDirector() {
        return getOriginator();
    }   

    public int getScenes() {
        return getQuantity();
    }   

    public TreeSet<String> getCast() {
        return getCollaborators();
    }   

    public void setCast(String... actors) {
        setCollaborators(actors);
    }   

    public String toString() {
        return "-Movie-\n" +
               "director: " + getDirector() + "\n" + 
               "# scenes: " + Integer.toString(getScenes()) + "\n" +
               "cast:     " + concatTreeSet(getCast()) + "\n" +
               "title:    " + getTitle() + "\n" +
               "keywords: " + concatTreeSet(getKeywords()) + "\n";
    }
}
