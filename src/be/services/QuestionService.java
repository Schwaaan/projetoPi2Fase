package be.services;

import be.domain.Alternative;
import be.domain.DiscursiveQuestion;
import be.domain.ObjectiveQuestion;
import be.domain.base.Question;
import be.domain.base.TypeQuestion;
import be.repository.ConnectionDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {

  public static boolean insert(Question question) {
    final String query1 = "INSERT INTO question (type_question, question, deleted) VALUES (?, ?, ?)";
    final String query2 = "INSERT INTO alternative (id_question, alternative, rigth_alternative, deleted) VALUES (?, ?, ?, ?)";

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = ConnectionDataBase.getConnection();
      statement = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);

      statement.setString(1, question.getTypeQuestion().getType());
      statement.setString(2, question.getQuestion());
      statement.setBoolean(3, question.isDeleted());
      statement.execute();

      resultSet = statement.getGeneratedKeys();
      while (resultSet.next()) {
        question.setId(resultSet.getInt(1));
      }

      statement.close();

      if (question instanceof ObjectiveQuestion) {
        ObjectiveQuestion objectiveQuestion = (ObjectiveQuestion) question;
        statement = connection.prepareStatement(query2);
        statement.setInt(1, objectiveQuestion.getId());
        for (Alternative alternative : objectiveQuestion.getAlternativeList()) {
          statement.setString(2, alternative.getAlternative());
          statement.setBoolean(3, alternative.getRigthAlternative());
          statement.setBoolean(4, alternative.isDeleted());
          statement.addBatch();
        }
        statement.executeBatch();
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        if (resultSet != null) {
          resultSet.close();
        }

        if (connection != null) {
          statement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }

  public static List<Question> getQuestions() {
    List<Question> questions = new ArrayList<>();

    final String query = "SELECT * FROM question WHERE deleted = false ORDER BY id";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try {
      connection = ConnectionDataBase.getConnection();
      statement = connection.createStatement();
      resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        String type = resultSet.getString("type_question");
        if (type.equals(TypeQuestion.OBJECTIVE.getType())) {
          Question question = new ObjectiveQuestion();
          question.setId(resultSet.getInt("id"));
          question.setTypeQuestion(type);
          question.setQuestion(resultSet.getString("question"));
          question.setAlternativeList(AlternativeService.getListAlternativeById(question.getId()));
          question.setDeleted(resultSet.getBoolean("deleted"));

          questions.add(question);
        } else {
          Question question = new DiscursiveQuestion();
          question.setId(resultSet.getInt("id"));
          question.setTypeQuestion(type);
          question.setQuestion(resultSet.getString("question"));
          question.setDeleted(resultSet.getBoolean("deleted"));

          questions.add(question);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
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
        return null;
      }
    }
    return questions;
  }

  public static boolean updateQuestion(Question question) {
    final String queryUpdate = "UPDATE question SET type_question = ?, question = ?, deleted = ?  where id = ?";

    final String query2 = "UPDATE alternative SET deleted = true WHERE id_question = ?";

    final String query3 = "INSERT INTO alternative(id_question, alternative, rigth_alternative, deleted) VALUES (?, ?, ?, ?)";

    Connection connection = null;
    PreparedStatement statement = null;

    try {

      connection = ConnectionDataBase.getConnection();
      statement = connection.prepareStatement(queryUpdate);
      statement.setString(1, question.getTypeQuestion().getType());
      statement.setString(2, question.getQuestion());
      statement.setBoolean(3, question.isDeleted());
      statement.setInt(4, question.getId());
      statement.execute();
      statement.close();

      statement = connection.prepareStatement(query2);
      statement.setInt(1, question.getId());
      statement.execute();
      statement.close();

      if (question instanceof ObjectiveQuestion) {
        statement = connection.prepareStatement(query3);
        statement.setInt(1, question.getId());
        ObjectiveQuestion objectiveQuestion = (ObjectiveQuestion) question;
        for (Alternative alternative : objectiveQuestion.getAlternativeList()) {
          statement.setString(2, alternative.getAlternative());
          statement.setBoolean(3, alternative.getRigthAlternative());
          statement.setBoolean(4, alternative.isDeleted());
          statement.execute();
        }
      }
    } catch (Exception e) {
      System.out.println(e.toString());
      return false;
    } finally {
      try {
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

  public static boolean deleteQuestion(Question question) {
    final String query1 = "UPDATE alternative SET deleted = true WHERE id_question = ?";
    final String query2 = "UPDATE question SET deleted = true WHERE id = ?";

    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = ConnectionDataBase.getConnection();

      if (question instanceof ObjectiveQuestion) {
        statement = connection.prepareStatement(query1);
        statement.setInt(1, question.getId());
        statement.execute();
        statement.close();
      }

      statement = connection.prepareStatement(query2);
      statement.setInt(1, question.getId());
      statement.execute();
      statement.close();

    } catch (Exception e) {
      System.out.println(e.toString());
      return false;
    } finally {
      try {
        if (statement != null) {
          statement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return true;
  }
}