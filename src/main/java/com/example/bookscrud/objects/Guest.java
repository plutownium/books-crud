//package com.example.bookscrud.objects;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import com.example.bookscrud.db.Database;
//import com.example.bookscrud.extractor.HighlightExtractor;
//import com.example.bookscrud.sqlMaker.BookSQLMaker;
//import com.example.bookscrud.sqlMaker.GuestSQLMaker;
//import com.example.bookscrud.sqlMaker.HighlightSQLMaker;
//import com.example.bookscrud.book.Book;
//
//
//
//public class Guest {
//
//    private ResultSet guest;
//    private Database pg;
//
//    private Integer id;
//    private String name;
//    private Integer checkouts;
//    private Integer borrowedBookId;
//    private Integer favoriteAuthorId;
//
//
//    public Guest(Database pg, String name) throws SQLException {
//        GuestSQLMaker guestTool = new GuestSQLMaker();
//
//        Integer startingCheckouts = 0;
//        String query = guestTool.createGuest(name, startingCheckouts);
//        System.out.println(query);
//        ResultSet guest = pg.operate(query);
//        this.setGuest(guest);
//        this.setPg(pg);
//    }
//
//    public Guest addFavoriteAuthor(Integer authorId) throws SQLException {
//        GuestSQLMaker guestTool = new GuestSQLMaker();
//        String query = guestTool.addFavoriteAuthor(this.getId(), authorId);
//        this.getPg().operateUpdate(query);
//        return this;
//    }
//
//    public void rentBook(Book book) throws SQLException {
//        BookSQLMaker bookTool = new BookSQLMaker();
//
//        String markRentedQuery = bookTool.rentBookToGuest(book.getId()); // mark book rented
//        this.getPg().operateUpdate(markRentedQuery);
//        //
//        GuestSQLMaker guestTool = new GuestSQLMaker();
//        String updateGuestQuery = guestTool.rentBookToGuest(book.getId(), this.getId());
//        this.getPg().operateUpdate(updateGuestQuery);
//        //
//        this.setBorrowedBookId(book.getId());
//    }
//
//    private void setGuest(ResultSet guest) {
//        this.guest = guest;
//    }
//
//    private void setId(Integer id) {
//        this.id = id;
//    }
//
//    private void setPg(Database pg) {
//        this.pg = pg;
//    }
//
//    private void setBorrowedBookId(Integer id) {
//        this.borrowedBookId = id;
//    }
//
//    private Integer getBorrowedBookId() {
//        return this.borrowedBookId;
//    }
//
//    private Database getPg() {
//        return this.pg;
//    }
//
//    public Integer getId() throws SQLException {
//        if (this.id != null) {
//            return this.id;
//        }
//        this.guest.next();
//        String stringId = this.guest.getString(1);
//        Integer id = Integer.parseInt(stringId);
//        this.setId(id);
//        return id;
//    }
//
//    public void addHighlight(String highlightText) throws SQLException {
//        // only works if the guest has a book! or else how can they add the highlight?
//        boolean hasRentedBook = this.borrowedBookId != null;
//        if (hasRentedBook) {
//            HighlightSQLMaker highlightTool = new HighlightSQLMaker();
//            String highlightUpdate = highlightTool.addHighlight(this.getId(), this.getBorrowedBookId(), highlightText);
//            this.getPg().operateUpdate(highlightUpdate);
//        }
//    }
//
//    public Highlight[] getHighlights() throws SQLException {
//        //
//        System.out.println("hi");
//        HighlightSQLMaker highlightTool = new HighlightSQLMaker();
//        String highlightsQuery = highlightTool.getAllForGuest(this.getId());
//        ResultSet highlights = this.getPg().operate(highlightsQuery);
//        Highlight[] extracted = new HighlightExtractor().extract(highlights);
//        return extracted;
//    }
//
//    public String toString() {
//        return String.format("Guest {name: %s, checkouts: %s, favoriteAuthorId: %s }",
//                name, checkouts, favoriteAuthorId);
//    }
//}