package demo.spring.data;

import demo.spring.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class DataAccess {

    Connection conn;

    @Autowired
    public DataAccess() {
        try {
            conn = DriverManager.getConnection(Config.URL, Config.USER, Config.PASSWORD);
        } catch (SQLException e) {
            System.out.println("catch connection");
            e.printStackTrace();
        }
    }

    /**
     * SELECT* FROM QUOTES
     *
     * @return the String value of the whole table or null if something goes wrong
     */
    public String selectAll() {

        ArrayList<Quote> quotes = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("Select* from QUOTES;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Quote temp = new Quote();
                temp.setQuote(rs.getString("quote"));
                temp.setId((rs.getInt("id")));
                quotes.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("catch *");
            e.printStackTrace();
            return null;
        }
        return quotes.toString();
    }

    /**
     * SELECT QUOTE FROM QUOTES
     *
     * @param id to specify the quote
     * @return the String value of the specified quote or null if something goes wrong
     */
    public String selectQuote(int id) {

        Quote temp = new Quote();
        temp.setId(id);
        try {
            PreparedStatement ps = conn.prepareStatement("Select QUOTE from QUOTES where id=" +id+ ";");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                temp.setQuote(rs.getString("quote"));
            }

        } catch (SQLException e) {
            System.out.println("catch select");
            e.printStackTrace();
        }

        return temp.toString();
    }

    /**
     * INSERT INTO QUOTES
     * //id is assigned by the database
     *
     * @param quote value to be inserted
     * @return 1 if successful or -1 if something goes wrong
     */
    public int insertQuote(String quote) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into QUOTES (quote) values('" + quote + "');");
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("catch insert");
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    /**
     * INSERT INTO QUOTES
     *
     * @param quote value to be inserted
     * @param id of the quote
     * @return 1 if successful or -1 if something goes wrong
     */
    public int insertQuote(int id, String quote) {
        try {

            PreparedStatement ps = conn.prepareStatement("insert into QUOTES (id, quote) values(" +id+ ",'" +quote+ "');");
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("catch insert");
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    /**
     * UPDATE QUOTES SET QUOTE
     *
     * @param id to specify the quote
     * @param quote value to be inserted
     * @return 1 if successful or -1 if something goes wrong
     */
    public int updateQuote(int id, String quote) {
        try {
            PreparedStatement ps = conn.prepareStatement("update QUOTES set quote='" +quote+ "' where id="+ id + ";");
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("catch update");
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    /**
     * DELETE FROM QUOTES
     *
     * @param id to specify the quote
     * @return 1 if successful or -1 if something goes wrong
     */
    public int deleteQuote(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from QUOTES where id='" + id + "';");
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("catch delete");
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

}