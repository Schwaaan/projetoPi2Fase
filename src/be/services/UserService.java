package be.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.domain.user.User;
import be.repository.ConnectionDataBase;

public class UserService {

    public static boolean insert(User user) {
        final String INSERT_USER = "INSERT INTO users (name, last_name, type, deleted, password) VALUES (?, ?, ?, ?, ?);";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionDataBase.getConnection();
            statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getType());
            statement.setBoolean(4, user.isDeleted());
            statement.setString(5, user.getPassword());

            result = statement.getGeneratedKeys();
            while (result.next()) {
                user.setId(result.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (result != null) {
                    result.close();
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;

    }

	public static List<User> getNameList() {
        List<User> users = new ArrayList<>();
        
        final String SELECT_NAMES = "SELECT name, password FROM users";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = ConnectionDataBase.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(SELECT_NAMES);

            while (result.next()) {
                User user = new User();
                user.setName(result.getString("name"));
                user.setPassword(result.getString("password"));
                users.add(user);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
	}

}
