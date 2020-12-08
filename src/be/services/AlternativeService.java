package be.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.domain.Alternative;
import be.repository.ConnectionDataBase;

public class AlternativeService {
    public static List<Alternative> getListAlternativeById(int idQuestion) {
        List<Alternative> alternatives = new ArrayList<Alternative>();

        final String query = "SELECT a.alternative, a.rigth_alternative FROM question q INNER JOIN alternative a ON q.id = a.id_question WHERE q.id = ? AND a.deleted = false";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionDataBase.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idQuestion);
            statement.execute();
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Alternative alternative = new Alternative(resultSet.getString("alternative"),
                        resultSet.getBoolean("rigth_alternative"));
                alternatives.add(alternative);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return alternatives;
    }
}
