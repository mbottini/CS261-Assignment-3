package library;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public class Library
{
    // Different hashes to look up items by different criteria.

    // Keyword hash for all items.
    HashMap<String, Vector<Item>> itemKeywordHash =
        new HashMap<String, Vector<Item>>();

    // Book-related hashes.
    HashMap<String, Book> bookTitleHash = 
        new HashMap<String, Book>();

    HashMap<String, Vector<Book>> bookAuthorHash =
        new HashMap<String, Vector<Book>>();

    // Music-related hashes.
    HashMap<String, MusicAlbum> albumTitleHash =
        new HashMap<String, MusicAlbum>();

    HashMap<String, Vector<MusicAlbum>> albumBandHash =
        new HashMap<String, Vector<MusicAlbum>>();

    HashMap<String, Vector<MusicAlbum>> albumMusicianHash =
        new HashMap<String, Vector<MusicAlbum>>();

    //Movie-related hashes.

    HashMap<String, Movie> movieTitleHash =
        new HashMap<String, Movie>();

    HashMap<String, Movie> movieDirectorHash =
        new HashMap<String, Vector<Movie>>();

    HashMap<String, Movie> movieActorHash =
        new HashMap<String, Vector<Movie>>();


	// general methods
	
	// returns all of the items which have the specified keyword
	public Collection<Item> itemsForKeyword(String keyword)
	{
        return itemKeywordHash.get(keyword);
	}
	
	// print an item from this library to the output stream provided
	public void printItem(PrintStream out, Item item) {
        out.println(item);
        return;
	}
	
	// book-related methods
	
	// adds a book to the library
	public Item addBook(String title, String author, int nPages, String... keywords)
	{
        
        // Check to see if the library already contains the book in question.
        if(bookTitleHash.containsKey(title)) {
            System.out.println("Library already contains " + title + ".");
            return null;
        }

        // Otherwise, add it to the library, which contains the following
        // hashes:

        // Title hash.
        Book newBook = new Book(title, author, nPages, keywords);
        bookTitleHash.put(title, newTitle);

        // Author hash, which may or may not already have books by that author.
        if(bookAuthorHash.containsKey(author)) {
            bookAuthorHash.get(author).add(newBook);
        }

        else {
            bookAuthorHash.put(author, new Vector<Book>());
            bookAuthorHash.get(author).add(newBook);
        }


        // Keyword hash, which will add an entry for each keyword that's
        // added.
        for(String s : keywords) {
            if(itemKeywordHash.containsKey(s)) {
                itemKeywordHash.get(s).add(newBook);
            }

            else {
                itemKeywordHash.put(keyword, new Vector<Item>());
                itemKeywordHash.get(s).add(newBook);
            }
        }

		return newItem;
	}
	
	// removes a book from the library
	public boolean removeBook(String title)
	{
        // If it's not in the library, return false.
        if(!(bookTitleHash.contains(title))) {
            return false;
        }

        // Otherwise, we remove it from every hash we have.
        // Also, for all vector collections, we check to see if the vector is
        // empty after removal. If so, we remove that vector entirely.

        Book foundBook = bookTitleHash.get(title);

        for(String s : foundBook.getKeywords()) {
            itemKeywordHash.get(s).remove(foundBook);
            if(itemKeywordHash.get(s).isempty()) {
                itemKeywordHash.remove(s);
            }
        }

        bookAuthorHash.get(foundBook.getAuthor()).remove(foundBook);

        if(bookAuthorHash.get(foundBook.getAuthor()).isempty()) {
            bookAuthorHash.remove(foundBook.getAuthor());
        }

        bookTitleHash.remove(foundBook);

        return true;
	}
	
	// returns all of the books by the specified author
	public Collection<Item> booksByAuthor(String author)
	{
		return bookAuthorHash.get(author);
	}
	
	// returns all of the books in the library
	public Collection<Item> books()
	{
		return bookTitleHash.values();
	}
	
	// music-related methods
	
	// adds a music album to the library
	public Item addMusicAlbum(String title, String band, int nSongs, String... keywords)
	{
        if(albumTitleHash.contains(title)) {
            System.out.println("Library already contains " + title + ".");
            return null;
        }

        MusicAlbum newAlbum = new MusicAlbum(title, band, nSongs, keywords);

        // We need to add to the titlehash, bandhash, and keywordhash.

        albumTitleHash.put(title, newAlbum);

        if(albumBandHash.contains(band)) {
            albumBandHash.get(band).add(newAlbum);
        }

        else {
            albumBandHash.put(band, new Vector<MusicAlbum>());
            albumBandHash.get(band).add(newAlbum);
        }

        for(String s : keywords) {
            if(itemKeywordHash.containsKey(s)) {
                itemKeywordHash.get(s).add(newAlbum);
            }

            else {
                itemKeywordHash.put(keyword, new Vector<Item>());
                itemKeywordHash.get(s).add(newAlbum);
            }
        }

		return newAlbum;
	}

	// adds the specified band members to a music album
	public void addBandMembers(Item album, String... members)
	{
        if(!(album instanceof MusicAlbum)) {
            return;
        }

        album.addMembers(members);

        for(String s : members) {
            if(albumMusicianHash.contains(s)) {
                albumMusicianHash.get(s).add(album);
            }

            else {
                albumMusicianHash.put(s, new Vector<MusicAlbum>());
                albumMusicianHash.get(s).add(album);
            }
        return;
	}
	
	// removes a music album from the library
	public boolean removeMusicAlbum(String title)
	{
        // If it's not in the library, return false.
        if(!(albumTitleHash.contains(title))) {
            return false;
        }

        // Otherwise, we remove it from every hash we have.
        // Also, for all vector collections, we check to see if the vector is
        // empty after removal. If so, we remove that vector entirely.

        MusicAlbum foundAlbum = albumTitleHash.get(title);

        for(String s : foundAlbum.getKeywords()) {
            itemKeywordHash.get(s).remove(foundAlbum);
            if(itemKeywordHash.get(s).isempty()) {
                itemKeywordHash.remove(s);
            }
        }

        albumBandHash.get(foundAlbum.getBand()).remove(foundAlbum);

        if(albumBandHash.get(foundAlbum.getBand()).isempty()) {
            albumBandHash.remove(foundAlbum.getBand());
        }

        for(String s : foundAlbum.getMusicians()) {
            albumMusicianHash.get(s).remove(foundAlbum);
            if(albumMusicianHash.get(s).isEmpty()) {
                albumMusicianHash.remove(s);
            }
        }

        albumTitleHash.remove(foundAlbum);

        return true;
	}

	// returns all of the music albums by the specified band
	public Collection<Item> musicByBand(String band)
	{
		return albumBandHash.get(band);
	}
	
	// returns all of the music albums by the specified musician
	public Collection<Item> musicByMusician(String musician)
	{
		return albumMusicianHash.get(musician);
	}
	
	// returns all of the music albums in the library
	public Collection<Item> musicAlbums()
	{
		return albumTitleHash.values();
	}
	
	// movie-related methods
	
	// adds a movie to the library
	public Item addMovie(String title, String director, int nScenes, String... keywords)
	{
        if(movieTitleHash.contains(title)) {
            System.out.println("Library already contains " + title + ".");
            return null;
        }

        Movie newMovie = new Movie(title, director, nScenes, keywords);

        // We need to add to the titlehash, directorhash, and keywordhash.

        movieTitleHash.put(title, newAlbum);

        if(movieDirectorHash.contains(director)) {
            movieDirectorHash.get(director).add(newMovie);
        }

        else {
            movieDirectorHash.put(director, new Vector<Album>());
            movieDirectorHash.get(director).add(newMovie);
        }

        for(String s : keywords) {
            if(itemKeywordHash.containsKey(s)) {
                itemKeywordHash.get(s).add(newMovie);
            }

            else {
                itemKeywordHash.put(keyword, new Vector<Item>());
                itemKeywordHash.get(s).add(newMovie);
            }
        }

		return newAlbum;
	}

	// adds the specified actors to a movie
	public void addCast(Item movie, String... members)
	{
        if(!(movie instanceof Movie)) {
            return;
        }

        movie.addActors(members);

        for(String s : members) {
            if(movieActorHash.contains(s)) {
                movieActorHash.get(s).add(album);
            }

            else {
                movieActorHash.put(s, new Vector<Album>());
                movieActorHash.get(s).add(album);
            }
        return;

	}

	// removes a movie from the library
	public boolean removeMovie(String title)
	{
        // If it's not in the library, return false.
        if(!(movieTitleHash.contains(title))) {
            return false;
        }

        // Otherwise, we remove it from every hash we have.
        // Also, for all vector collections, we check to see if the vector is
        // empty after removal. If so, we remove that vector entirely.

        Movie foundMovie = movieTitleHash.get(title);

        for(String s : foundMovie.getKeywords()) {
            itemKeywordHash.get(s).remove(foundMovie);
            if(itemKeywordHash.get(s).isempty()) {
                itemKeywordHash.remove(s);
            }
        }

        movieDirectorHash.get(foundMovie.getDirector()).remove(foundMovie);

        if(movieDirectorHash.get(foundMovie.getDirector()).isempty()) {
            movieDirectorHash.remove(foundMovie.getDirector());
        }

        for(String s : foundAlbum.getActors()) {
            movieActorHash.get(s).remove(foundMovie);
            if(movieActorHash.get(s).isEmpty()) {
                movieActorHash.remove(s);
            }
        }

        movieTitleHash.remove(foundMovie);

        return true;
	}
	
	// returns all of the movies by the specified director
	public Collection<Item> moviesByDirector(String director)
	{
		return movieDirectorHash.get(director);
	}
	
	// returns all of the movies by the specified actor
	public Collection<Item> moviesByActor(String actor)
	{
		return movieActorHash.get(actor);
	}
	
	// returns all of the movies in the library
	public Collection<Item> movies()
	{
		return movieTitleHash.values();
	}	
}
