package Models.ChatClients.Database;

import Models.ChatClients.Message;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.List;

public class JdbcDatabaseOperations implements DatabaseOperations{
    private final Connection connection;

    public JdbcDatabaseOperations(String driver, String url) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        this.connection = DriverManager.getConnection(url);
    }

    @Override
    public void addMessage(Message message) {
        try {
            Statement statement = connection.createStatement();
            String sql =
                "INSERT INTO ChatMessages (author, text, created) "
                + "VALUES ("
                    +"'" + message.getAuthor() + ","
                    +"'" + message.getText() + ","
                    +"'" + Timestamp.valueOf(message.getTime()) + "'"
                + ")";
            statement.executeUpdate(sql);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getMessages() {
        return null;
    }
}
