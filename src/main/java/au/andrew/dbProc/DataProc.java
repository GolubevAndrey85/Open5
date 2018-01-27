package au.andrew.dbProc;



import java.sql.*;
import java.util.ArrayList;

public class DataProc {
    private static final String url = "jdbc:mysql://localhost:3306/foosball";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private PreparedStatement preparedStmt;
    String query = "";


    private Connection getCon(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void conClose(){
        try {
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet getData(String query){
        try {
            con = getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void putData(String query){
        try {
            con = getCon();
            preparedStmt = con.prepareStatement(query);
            preparedStmt.execute();
            preparedStmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




   /* public ArrayList<String> getUserStat(String username){
        ResultSet rs = getData(getCon(), username);
        return null;
    }*/

}
