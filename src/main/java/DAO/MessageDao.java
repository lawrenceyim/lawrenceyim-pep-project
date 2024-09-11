package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDao {
    public Message insertMessage(Message message) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO message " +
                    "(posted_by, message_text, time_posted_epoch) " +
                    "VALUES (?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            message.setMessage_id(resultSet.getInt(1));

            return message;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
