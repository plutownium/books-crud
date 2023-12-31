package com.example.bookscrud.extractor;

import com.example.bookscrud.author.Author;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorExtractor {

    public static Author[] extract(ResultSet rs) throws SQLException {
        ArrayList<Author> found = new ArrayList<Author>();
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnCount = rsmd.getColumnCount();
        System.out.println("Starting author extractor");
        while (rs.next()) {
            Integer id = 0;
            String firstName = "";
            String lastName = "";
            Integer published = 0;
            for (int i = 1; i <= columnCount; i++) {
                String columnValue = rs.getString(i);
                String colName = rsmd.getColumnName(i);
                if (colName.equals("id")) {
                    id = Integer.parseInt(columnValue);
                } else if (colName.equals("firstname")) {
                    firstName = columnValue;
                } else if (colName.equals("lastname")) {
                    lastName = columnValue;
                } else if (colName.equals("published")) {
                    published = Integer.parseInt(columnValue);
                } else {
                    System.out.println("Unexpected:" + columnValue + " ::from col:: " + colName);
                }

            }
            Author newAuthor = new Author(id, firstName, lastName, published);
            found.add(newAuthor);
        }

        // turn the arraylist into an array so I don't have to import arrayList everywhere.
        Author[] foundAuthors = new Author[found.size()];
        foundAuthors = found.toArray(foundAuthors);
        return foundAuthors;
    }
}
