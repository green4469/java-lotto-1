package lotto.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundDAO {
    private final Connection con;

    public RoundDAO(Connection con) {
        this.con = con;
    }

    public void addRound(int prize, double interestRate) throws SQLException {
        String query = "INSERT INTO round (prize, interest_rate) VALUES(?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(prize));
        pstmt.setString(2, String.valueOf(interestRate));
        pstmt.executeUpdate();
    }

    public int getPrizeOfId(int id) throws SQLException {
        String query = "SELECT prize FROM round WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(id));
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1;

        return rs.getInt("prize");
    }

    public double getInterestRateOfId(int id) throws SQLException {
        String query = "SELECT interestRate FROM round WHERE id=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, String.valueOf(id));
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return -1.0;

        return rs.getDouble("interest_rate");
    }

    public int getLatestRoundId() throws SQLException {
        String query = "SELECT MAX(id) AS ThisId FROM round";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) {
            throw new SQLException("진행한 로또 회차가 하나도 없습니다.");
        }
        return rs.getInt("ThisId");
    }
}
