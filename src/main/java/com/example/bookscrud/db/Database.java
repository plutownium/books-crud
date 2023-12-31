package com.example.bookscrud.db;

import com.example.bookscrud.PrettyPrinter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String dbName = "bookscrud"; // note lowercase
    private final String username = "postgres";
    private final String password = "postgres";

    private Connection connection;

    private Statement statement;

    private static final String DATABASE_DRIVER = "org.postgresql.Driver";

    private PrettyPrinter printer;

    public Database() {
        this.printer = new PrettyPrinter();
    }


    public Connection connect() {
        boolean noConnectionYet = connection == null;
        if (noConnectionYet)
        {
            try {
                Class.forName(DATABASE_DRIVER);
                System.out.println("library app db connection established");
                connection = (Connection) DriverManager.getConnection(url + dbName, username, password);
                // Statement stmt = connection.createStatement();
                // String sql = "CREATE DATABASE " + dbName;
                // stmt.executeUpdate(sql);
                // System.out.println("Database created successfully...");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("PROBLEM\n########");
                System.out.println(e.getMessage());
                e.printStackTrace();
                System.out.println("########\n");
            }
        }
        return connection;
    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver version: " + org.postgresql.Driver.getVersion());
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

//        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");

//        driverManagerDataSource.setDriverClassName(DATABASE_DRIVER);
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/bookscrud");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("postgres");
        return driverManagerDataSource;
    }


    public void disconnect()
    {
        boolean establishedConnection = connection != null;
        if (establishedConnection)
        {
            try
            {
                connection.close();

                connection = null;

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public ResultSet operate(String query) throws SQLException {
        statement = connection.createStatement();
        System.out.println("Executing: " + query);
        ResultSet resultSet = statement.executeQuery(query);
//        System.out.println("Here is resultset:");
//        System.out.println(resultSet);
        return resultSet;
    }

    public Integer operateUpdate(String query) throws SQLException {
        // https://stackoverflow.com/questions/21276059/no-results-returned-by-the-query-error-in-postgresql
        // Use executeUpdate instead of executeQuery if no data will be returned (i.e. a non-SELECT operation).
        statement = connection.createStatement();
        this.printer.prettyYellow("Executing update: " + query);
        Integer thing = statement.executeUpdate(query);

        return thing;
    }
}