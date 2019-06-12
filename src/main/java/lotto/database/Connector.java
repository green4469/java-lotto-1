package lotto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static Connection getConnection() {
        Connection con = null;
        String server = "localhost";
        String database = "JAVA_LOTTO";
        String userName = "yumin";
        String password = "1234";

        // 드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println((" !! JDBC Driver load 오류: " + e.getMessage()));
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server +"/"
                    + database + "?serverTimezone=UTC&useSSL=false", userName, password);
            System.out.println("데이터베이스에 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("데이터베이스 연결 오류: " + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
