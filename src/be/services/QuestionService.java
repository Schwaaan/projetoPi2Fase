package be.services;

import be.domain.Alternative;
import be.domain.DiscursiveQuestion;
import be.domain.ObjectiveQuestion;
import be.domain.base.Question;
import be.repository.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionService {

  public static Question insertQuestionObjective(ObjectiveQuestion question) throws SQLException {
    final String query = "insert into question(question,idTeacher,deleted, alternativeId) VALUES (?,?,?,?)";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

      connection = DataBase.getConnection();

      preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

      resultSet = preparedStatement.getGeneratedKeys();

      while (resultSet.next()) {
        question.setId(resultSet.getInt(1));
      }

      preparedStatement.setString(1, question.getQuestion());
      preparedStatement.setInt(2, question.getIdTeacher());
      preparedStatement.setBoolean(3, question.isDeleted());
      for (Alternative alternative : question.getAlternativeList()) {
        createAlternative(alternative, question.getId());
        preparedStatement.setInt(4, alternative.getId());
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();

    } catch (SQLException e) {
      System.out.println(e.toString());
      return null;
    }
    return null;
  }

  public static Alternative createAlternative(Alternative alternative, Integer questionId)
      throws SQLException {
    final String query = "Insert into alternative(id_question,alternative,rightalternative,deleted) VALUES (?,?,?,?)";

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      connection = DataBase.getConnection();

      preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

      resultSet = preparedStatement.getGeneratedKeys();
      preparedStatement.setInt(1, questionId);
      preparedStatement.setString(2, alternative.getAlternative());
      preparedStatement.setBoolean(3, alternative.getRigthAlternative());
      preparedStatement.setBoolean(4, alternative.isDeleted());

      while (resultSet.next()) {
        alternative.setId(resultSet.getInt(1)); // nao sei o q faz.
      }


      preparedStatement.execute();

    } catch (SQLException e) {
      System.out.println(e.toString());
      return null;
    }
    return null;

  }

  public static Question insertQuestionDiscursive(DiscursiveQuestion question) {
    final String query = "Insert into question(question,idTeacher,deleted) VALUES (?,?,?)";

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = DataBase.getConnection();

      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, question.getQuestion());
      preparedStatement.setInt(2, question.getIdTeacher());
      preparedStatement.setBoolean(3, question.isDeleted());

      preparedStatement.execute();

    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println(e.toString());
      return null;
    } finally {
      try {
        if (preparedStatement != null) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}