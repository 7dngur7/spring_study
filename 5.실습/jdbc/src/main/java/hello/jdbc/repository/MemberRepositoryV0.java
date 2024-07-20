package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getMemberId());
            preparedStatement.setInt(2, member.getMoney());
            preparedStatement.executeUpdate();

            return member;
        }
        catch (SQLException e){
            log.error("db error", e);
            throw e;
        }
        finally {
            close(connection, preparedStatement, null);
        }

    }


    public Member findById(String member_id){
        String sql = "select * from member where member_id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member_id);

           resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
               Member member = new Member();
               member.setMemberId(resultSet.getString("member_id"));
               member.setMoney(resultSet.getInt("money"));
               return member;
           }
           else{
               throw new NoSuchElementException("element not found");
           }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(connection, preparedStatement, resultSet);
        }
    }
    public void updateById(String member_id, int money){
        String sql = "update member set money = ? where member_id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, member_id);

            int resultSize = preparedStatement.executeUpdate();
            log.info("resultSize={}", resultSize);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(connection, preparedStatement, resultSet);
        }


    }

    public void deleteById(String member_id){
        String sql = "delete from member where member_id = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member_id);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(connection, preparedStatement, null);
        }


    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
             try {
        rs.close();
    } catch (SQLException e) {
        log.info("error", e);
    } }
         if (stmt != null) {
        try {
        stmt.close();
             } catch (SQLException e) {
        log.info("error", e);
             }
                     }
                     if (con != null) {
        try {
        con.close();
             } catch (SQLException e) {
        log.info("error", e);
             }
                     } }

    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
