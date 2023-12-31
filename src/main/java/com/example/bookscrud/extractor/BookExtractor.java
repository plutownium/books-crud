package com.example.bookscrud.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import com.example.bookscrud.book.Book;

public class BookExtractor {

    public static Book[] extract(ResultSet rs) throws SQLException {
        ArrayList<Book> found = new ArrayList<Book>();
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnCount = rsmd.getColumnCount();
        System.out.println("Starting book extractor");
        while (rs.next()) {
            String title = "";
            Integer year = 0;
            Integer id = 0;
            Boolean rented = false;
            Integer authorId = 0;
            for (int i = 1; i <= columnCount; i++) {
                String columnValue = rs.getString(i);
                String colName = rsmd.getColumnName(i);
                if (colName.equals("title")) {
                    title = columnValue;
                } else if (colName.equals("year")) {
                    year = Integer.parseInt(columnValue);
                } else if (colName.equals("id")) {
                    id = Integer.parseInt(columnValue);
                } else if (colName.equals("rented")) {
                    System.out.println("in rented:" + columnValue + " ::from col:: " + colName);
                    if (columnValue == null) {
                        rented = false;
                    } else {
                        rented = Boolean.parseBoolean(columnValue);
                    }
                } else if (colName.equals("authorid")) {
                    authorId = Integer.parseInt(columnValue);
                } else {
                    System.out.println("Unexpected:" + columnValue + " ::from col:: " + colName);
                }

            }
            Book newBook = new Book(id, title, year, rented, authorId);
            found.add(newBook);
        }

        Book[] foundBooks = new Book[found.size()];
        foundBooks = found.toArray(foundBooks);

        return foundBooks;
    }
}
