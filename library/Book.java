package library;

class Book extends Item
{
    public Book(String title, String author, int nPages, 
            String... keywords) {
        super(title, author, nPages, keywords);
        return;
    }

    public String getAuthor() {
        return getOriginator();
    }

    public int getPages() {
        return getQuantity();
    }

    public String toString() {

        return "-Book-\n" +
               "author:   " + getAuthor() + "\n" +
               "# pages:  " + Integer.toString(getPages()) + "\n" +
               "title:    " + getTitle() + "\n" +
               "keywords: " + concatTreeSet(getKeywords()) + "\n";
    }
}
