package concise.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    public void performDatabaseInsert(String SQL) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres",
                    "root");
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> performDatabaseSelect(String SQL) {
        List<String> dataList = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres",
                    "root");
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String data = rs.getString("data");
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
