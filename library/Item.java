// Each book, music album, or movie will be an instance of a subclass of this class.
// Instances of this class should not be created. Specifying it as 'abstract' ensures
// that they cannot.

package library;
import java.util.TreeSet;


public abstract class Item implements Comparable<Item>
{
    private String _title = new String();
    private String _originator = new String();
    private int _quantity;
    private TreeSet<String> _keywords = new TreeSet<String>();
    private TreeSet<String> _collaborators = new TreeSet<String>();

    public Item(String title, String originator, int quantity, 
            String... keywords) {
        _title = title;
        _originator = originator;
        _quantity = quantity;

        protectedTreeSetAdd(_keywords, keywords);
    }

    // Getter functions. Some are public and can be used by all classes.
    // Some are protected and must be wrapped by the children classes.

    public String getTitle() {
        return _title;
    }

    protected String getOriginator() {
        return _originator;
    }

    protected int getQuantity() {
        return _quantity;
    }

    public TreeSet<String> getKeywords() {
        return _keywords;
    }

    protected TreeSet<String> getCollaborators() {
        return _collaborators;
    }

    // Setter function for setCollaborators. Again, it's protected and must be
    // wrapped by the children classes if relevant.

    protected void setCollaborators(String... collaborators) {
        protectedTreeSetAdd(_collaborators, collaborators);
    }

    private void protectedTreeSetAdd(TreeSet<String> set, String... contents) {
        for(String element : contents) {
            if(!(set.contains(element))) {
                set.add(element);
            }
        }
        return;
    }

    protected String concatTreeSet(TreeSet<String> set) {
        String returnString = new String();

        if(set.isEmpty()) {
            returnString = "";
        }

        else {
            for(String element : set) {
                returnString += element;
                if(element != set.last()) {
                    returnString += ", ";
                }
            }
        }

        return returnString;
    }

    @Override
    public int compareTo(Item otherItem) {
        return getTitle().compareTo(otherItem.getTitle());
    }

    public abstract String toString();
}

