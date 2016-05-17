package library;

import java.util.TreeSet;

class MusicAlbum extends Item
{
    public MusicAlbum(String title, String band, int nSongs,
            String... keywords) {
        super(title, band, nSongs, keywords);
        return;
    }

    public String getBand() {
        return getOriginator();
    }

    public int getSongs() {
        return getQuantity();
    }

    public TreeSet<String> getMembers() {
        return getCollaborators();
    }

    public void setMembers(String... members) {
        setCollaborators(members);
    }

    public String toString() {
        return "-Music Album-\n" +
               "band:     " + getBand() + "\n" + 
               "# songs:  " + Integer.toString(getSongs()) + "\n" +
               "members:  " + concatTreeSet(getMembers()) + "\n" +
               "title:    " + getTitle() + "\n" +
               "keywords: " + concatTreeSet(getKeywords()) + "\n";
    }
}             
