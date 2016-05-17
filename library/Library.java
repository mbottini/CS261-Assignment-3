package library;

import java.io.PrintStream;
import java.util.Collection;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Iterator;

public class Library
{
    // Book-related hashes.
    private TreeMap<String, Book> bookTitleHash = 
        new TreeMap<String, Book>();
    private TreeMap<String, TreeSet<Book>> bookAuthorHash =
        new TreeMap<String, TreeSet<Book>>();

    // Music album-related hashes.
    private TreeMap<String, MusicAlbum> albumTitleHash =
        new TreeMap<String, MusicAlbum>();
    private TreeMap<String, TreeSet<MusicAlbum>> albumBandHash =
        new TreeMap<String, TreeSet<MusicAlbum>>();
    private TreeMap<String, TreeSet<MusicAlbum>> albumMemberHash =
        new TreeMap<String, TreeSet<MusicAlbum>>();

    // Movie-related hashes.
    private TreeMap<String, Movie> movieTitleHash = 
        new TreeMap<String, Movie>();
    private TreeMap<String, TreeSet<Movie>> movieDirectorHash =
        new TreeMap<String, TreeSet<Movie>>();
    private TreeMap<String, TreeSet<Movie>> movieActorHash =
        new TreeMap<String, TreeSet<Movie>>();

    // General hash for keywords.
    private TreeMap<String, TreeSet<Item>> keywordHash =
        new TreeMap<String, TreeSet<Item>>();

	// general methods
	
	// returns all of the items which have the specified keyword
	public Collection<Item> itemsForKeyword(String keyword)
	{
		return keywordHash.get(keyword);
	}
	
	// print an item from this library to the output stream provided
	public void printItem(PrintStream out, Item item)
	{
        out.println(item);
        return;
	}
	
	// book-related methods
	
	// adds a book to the library
	public Item addBook(String title, String author, int nPages, String... keywords)
	{
        if(bookTitleHash.containsKey(title)) {
            return null;
        }

        Book newBook = new Book(title, author, nPages, keywords);
        bookTitleHash.put(title, newBook);
        hashTreeSetAdd(bookAuthorHash, author, newBook);

        for(String s : keywords) {
            hashTreeSetAdd(keywordHash, s, newBook);
        }

		return newBook;
	}
	
	// removes a book from the library
	public boolean removeBook(String title)
	{
        Book foundBook = bookTitleHash.get(title);

        if(foundBook == null) {
            return false;
        }

        bookTitleHash.remove(title);
        hashTreeSetRemove(bookAuthorHash, foundBook.getAuthor(), foundBook);

        for(String s : foundBook.getKeywords()) {
            hashTreeSetRemove(keywordHash, s, foundBook);
        }

        return true;
	}
	
	// returns all of the books by the specified author
	public Collection<Item> booksByAuthor(String author)
	{
		return getTreeSet(bookAuthorHash, author);
	}
	
	// returns all of the books in the library
	public Collection<Item> books()
	{
        return getTreeSet(bookTitleHash);
	}
	
	// music-related methods
	
	// adds a music album to the library
	public Item addMusicAlbum(String title, String band, int nSongs, String... keywords)
	{
		if(albumTitleHash.containsKey(title)) {
            return null;
        }

        MusicAlbum newAlbum = new MusicAlbum(title, band, nSongs, keywords);

        albumTitleHash.put(title, newAlbum);
        hashTreeSetAdd(albumBandHash, band, newAlbum);

        for(String s : keywords) {
            hashTreeSetAdd(keywordHash, s, newAlbum);
        }

        return newAlbum;
	}

	// adds the specified band members to a music album
	public void addBandMembers(Item album, String... members)
	{
        // Gloriously terrible hack. Grab the album's title, search the
        // albumTitleHash for that title to get the actual MusicAlbum, and
        // modify *that*. This gets around the "can't cast Item as MusicAlbum"
        // problem.

        MusicAlbum foundAlbum = albumTitleHash.get(album.getTitle());

        if(foundAlbum != null) {
            foundAlbum.setMembers(members);

            for(String s : members) {
                hashTreeSetAdd(albumMemberHash, s, foundAlbum);
            }
        }
        return;
	}
	
	// removes a music album from the library
	public boolean removeMusicAlbum(String title)
	{
        MusicAlbum foundAlbum = albumTitleHash.get(title);

        if(foundAlbum == null) {
            return false;
        }

        albumTitleHash.remove(title);
        hashTreeSetRemove(albumBandHash, foundAlbum.getBand(), foundAlbum);
        
        for(String s : foundAlbum.getKeywords()) {
            hashTreeSetRemove(keywordHash, s, foundAlbum);
        }

        for(String s : foundAlbum.getMembers()) {
            hashTreeSetRemove(albumMemberHash, s, foundAlbum);
        }

        return true;
	}

	// returns all of the music albums by the specified band
	public Collection<Item> musicByBand(String band)
	{
		return getTreeSet(albumBandHash, band);
	}
	
	// returns all of the music albums by the specified musician
	public Collection<Item> musicByMusician(String musician)
	{
		return getTreeSet(albumMemberHash, musician);
	}
	
	// returns all of the music albums in the library
	public Collection<Item> musicAlbums()
	{
        return getTreeSet(albumTitleHash);
	}
	
	// movie-related methods
	
	// adds a movie to the library
	public Item addMovie(String title, String director, int nScenes, String... keywords)
	{
		if(movieTitleHash.containsKey(title)) {
            return null;
        }

        Movie newMovie = new Movie(title, director, nScenes, keywords);

        movieTitleHash.put(title, newMovie);
        hashTreeSetAdd(movieDirectorHash, director, newMovie);

        for(String s : keywords) {
            hashTreeSetAdd(keywordHash, s, newMovie);
        }

        return newMovie;
	}

	// adds the specified actors to a movie
	public void addCast(Item movie, String... members)
	{
        // Same as the Musician hack. Find the movie by searching the
        // movieTitleHash, set a Movie object equal to the result, and add the
        // members to *that*.

        Movie foundMovie = movieTitleHash.get(movie.getTitle());

        if(foundMovie != null) {
            foundMovie.setCast(members);

            for(String s : members) {
                hashTreeSetAdd(movieActorHash, s, foundMovie);
            }
        }
        return;
	}

	// removes a movie from the library
	public boolean removeMovie(String title)
	{
        Movie foundMovie = movieTitleHash.get(title);

        if(foundMovie == null) {
            return false;
        }

        movieTitleHash.remove(title);
        hashTreeSetRemove(movieDirectorHash, foundMovie.getDirector(), foundMovie);
        
        for(String s : foundMovie.getKeywords()) {
            hashTreeSetRemove(keywordHash, s, foundMovie);
        }

        for(String s : foundMovie.getCast()) {
            hashTreeSetRemove(movieActorHash, s, foundMovie);
        }

        return true;
	}
	
	// returns all of the movies by the specified director
	public Collection<Item> moviesByDirector(String director)
	{
		return getTreeSet(movieDirectorHash, director);
	}
	
	// returns all of the movies by the specified actor
	public Collection<Item> moviesByActor(String actor)
	{
		return getTreeSet(movieActorHash, actor);
	}
	
	// returns all of the movies in the library
	public Collection<Item> movies()
	{
        return getTreeSet(movieTitleHash);
	}	

    // Private functions. Not accessible to user, but automate some tedious
    // tasks.

    private <T extends Item> void hashTreeSetAdd(TreeMap<String, TreeSet<T>> hash, 
            String key, T value) {
        if(!(hash.containsKey(key))) {
            hash.put(key, new TreeSet<T>());
        }

        hash.get(key).add(value);
        return;
    }

    private <T extends Item> void hashTreeSetRemove(TreeMap<String, TreeSet<T>> hash,
            String key, T value) {
        hash.get(key).remove(value);

        if(hash.get(key).isEmpty()) {
            hash.remove(key);
        }
        return;
    }

    private <T extends Item> TreeSet<Item> getTreeSet(TreeMap<String, TreeSet<T>> hash,
            String searchTerm) {
        TreeSet<Item> returnTreeSet;

        if(hash.containsKey(searchTerm)) {
		    returnTreeSet = new TreeSet<Item>(hash.get(searchTerm));
        }

        else {
            returnTreeSet = null;
        }

        return returnTreeSet;
    }

    private <T extends Item> TreeSet<Item> getTreeSet(TreeMap<String, T> hash) {
        TreeSet<Item> returnTreeSet;

        if(hash.isEmpty()) {
            returnTreeSet = null;
        }

        else {
            returnTreeSet = new TreeSet<Item>(hash.values());
        }

        return returnTreeSet;
    }

}
