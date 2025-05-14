package com.pluralsight.service;

import com.pluralsight.model.Item;
import com.pluralsight.model.Book;
import com.pluralsight.model.Movie;
import com.pluralsight.model.Magazine;
import com.pluralsight.model.Member;
import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Library {
    private Map<String, Item> items;
    private Map<String, Member> members;
    private static int memberCounter = 1000;
    private Logger logger = Logger.getInstance();

    public Library() {
        this.items = new HashMap<>();
        this.members = new HashMap<>();
        logger.info("Library system initialized");
    }

    // Item management
    public boolean addItem(Item item) {
        if (!items.containsKey(item.getId())) {
            items.put(item.getId(), item);
            logger.info("Item added: " + item.getTitle() + " (ID: " + item.getId() + ")");
            return true;
        }
        logger.warn("Attempted to add duplicate item: " + item.getId());
        return false; // Item already exists
    }

    public Item getItem(String id) {
        Item item = items.get(id);
        if (item == null) {
            logger.warn("Item not found: " + id);
        }
        return item;
    }

    public List<Item> getAllItems() {
        logger.debug("Retrieved all items, count: " + items.size());
        return new ArrayList<>(items.values());
    }

    public List<Item> getAvailableItems() {
        List<Item> available = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.isAvailable()) {
                available.add(item);
            }
        }
        logger.debug("Retrieved available items, count: " + available.size());
        return available;
    }

    // Backward compatibility methods for books
    public boolean addBook(Book book) {
        return addItem(book);
    }

    public Book getBook(String isbn) {
        Item item = getItem(isbn);
        return (item instanceof Book) ? (Book) item : null;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        for (Item item : items.values()) {
            if (item instanceof Book) {
                books.add((Book) item);
            }
        }
        return books;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Item item : items.values()) {
            if (item instanceof Book && item.isAvailable()) {
                availableBooks.add((Book) item);
            }
        }
        return availableBooks;
    }

    // Member management
    public boolean addMember(Member member) {
        if (!members.containsKey(member.getMemberId())) {
            members.put(member.getMemberId(), member);
            logger.info("Member registered: " + member.getName() + " (ID: " + member.getMemberId() + ")");
            return true;
        }
        logger.warn("Attempted to add duplicate member: " + member.getMemberId());
        return false; // Member already exists
    }

    public Member getMember(String memberId) {
        Member member = members.get(memberId);
        if (member == null) {
            logger.warn("Member not found: " + memberId);
        }
        return member;
    }

    public static String generateMemberId() {
        return "M" + (++memberCounter);
    }

    // Search functionality
    public List<Item> searchItems(String query) {
        List<Item> results = new ArrayList<>();
        query = query.toLowerCase();

        for (Item item : items.values()) {
            if (item.getTitle().contains(query) ||
                    item.getCreator().contains(query) ||
                    item.getGenre().contains(query) ||
                    item.getId().contains(query)) {
                results.add(item);
            }
        }
        logger.info("Search performed for: '" + query + "', results: " + results.size());
        return results;
    }

    public List<Item> searchByGenre(String genre) {
        List<Item> results = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getGenre().contains(genre)) {
                results.add(item);
            }
        }
        logger.info("Genre search for '" + genre + "', results: " + results.size());
        return results;
    }

    public List<Item> searchByCreator(String creator) {
        List<Item> results = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getCreator().toLowerCase().contains(creator.toLowerCase())) {
                results.add(item);
            }
        }
        logger.info("Creator search for '" + creator + "', results: " + results.size());
        return results;
    }

    // Backward compatibility for book searches
    public List<Book> searchBooks(String query) {
        List<Book> bookResults = new ArrayList<>();
        List<Item> allResults = searchItems(query);
        for (Item item : allResults) {
            if (item instanceof Book) {
                bookResults.add((Book) item);
            }
        }
        return bookResults;
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> bookResults = new ArrayList<>();
        List<Item> allResults = searchByCreator(author);
        for (Item item : allResults) {
            if (item instanceof Book) {
                bookResults.add((Book) item);
            }
        }
        return bookResults;
    }

    // Borrow/Return operations
    public boolean borrowItem(String memberId, String itemId) {
        Member member = members.get(memberId);
        Item item = items.get(itemId);

        if (member == null) {
            logger.error("Borrow attempt failed - Member not found: " + memberId);
            return false;
        }

        if (item == null) {
            logger.error("Borrow attempt failed - Item not found: " + itemId);
            return false;
        }

        item.setAvailable(false);

        if (!item.isAvailable()) {
            logger.warn("Borrow attempt failed - Item not available: " + item.getTitle() + " (ID: " + itemId + ")");
            return false; // Item not available
        }

        if (member.hasBorrowedBook(itemId)) {
            logger.warn("Borrow attempt failed - Member already has item: " + memberId + ", ID: " + itemId);
            return false; // Member already has this item
        }

        member.borrowBook(itemId);
        logger.info("Item borrowed successfully - Member: " + member.getName() + " (" + memberId + "), Item: " + item.getTitle() + " (" + itemId + ")");
        return true;
    }

    public boolean returnItem(String memberId, String itemId) {
        Member member = members.get(memberId);
        Item item = items.get(itemId);

        if (member == null) {
            logger.error("Return attempt failed - Member not found: " + memberId);
            return false;
        }

        if (item == null) {
            logger.error("Return attempt failed - Item not found: " + itemId);
            return false;
        }

        if (!member.hasBorrowedBook(itemId)) {
            logger.warn("Return attempt failed - Member doesn't have item: " + memberId + ", ID: " + itemId);
            return false; // Member doesn't have this item
        }

        item.setAvailable(true);
        member.returnBook(itemId);
        logger.info("Item returned successfully - Member: " + member.getName() + " (" + memberId + "), Item: " + item.getTitle() + " (" + itemId + ")");
        return true;
    }

    // Backward compatibility for book operations
    public boolean borrowBook(String memberId, String isbn) {
        return borrowItem(memberId, isbn);
    }

    public boolean returnBook(String memberId, String isbn) {
        return returnItem(memberId, isbn);
    }

    // File I/O operations
    public void saveToCSV() throws IOException {
        logger.info("Starting data save operation");

        // Create data directory if it doesn't exist
        java.io.File dataDir = new java.io.File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
            logger.info("Created data directory");
        }

        try {
            // Save all items (unified format)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/items.csv"))) {
                writer.write("ID,Type,Title,Creator,Genre,Available,Extra1,Extra2");
                writer.newLine();

                for (Item item : items.values()) {
                    String extra1 = "", extra2 = "";
                    if (item instanceof Movie) {
                        Movie movie = (Movie) item;
                        extra1 = String.valueOf(movie.getDuration());
                    } else if (item instanceof Magazine) {
                        Magazine mag = (Magazine) item;
                        extra1 = mag.getIssueNumber();
                        extra2 = mag.getPublicationDate();
                    }

                    writer.write(String.format("%s,%s,%s,%s,%s,%b,%s,%s",
                            item.getId(), item.getType(), item.getTitle(),
                            item.getCreator(), item.getGenre(), item.isAvailable(),
                            extra1, extra2));
                    writer.newLine();
                }
            }

            // Save members
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/members.csv"))) {
                writer.write("MemberID,Name,Email");
                writer.newLine();

                for (Member member : members.values()) {
                    writer.write(String.format("%s,%s,%s",
                            member.getMemberId(), member.getName(), member.getEmail()));
                    writer.newLine();
                }
            }

            // Save borrowed items relationships
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/borrowed.csv"))) {
                writer.write("MemberID,ItemID");
                writer.newLine();

                for (Member member : members.values()) {
                    for (String itemId : member.getBorrowedBooks()) {
                        writer.write(String.format("%s,%s", member.getMemberId(), itemId));
                        writer.newLine();
                    }
                }
            }

            logger.info("Data save operation completed successfully");
        } catch (IOException e) {
            logger.error("Failed to save data", e);
            throw e;
        }
    }

    // Validate email format
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Basic email regex pattern
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}