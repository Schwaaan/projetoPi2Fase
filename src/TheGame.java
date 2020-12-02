import be.domain.Alternative;
import be.domain.DiscursiveQuestion;
import be.domain.ObjectiveQuestion;
import be.domain.base.Question;
import be.services.QuestionService;
import fe.MainFrame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class TheGame {

  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        QuestionService.insertQuestionDiscursive(new DiscursiveQuestion("POTATO", 123, false));
        List<Alternative> alternatives = new ArrayList<>();
        alternatives.add(new Alternative("Batatinha", false));
        try {
          //QuestionService
           //   .insertQuestionObjective(new ObjectiveQuestion("teste", alternatives));
          QuestionService.createAlternative(new Alternative("xxx", false), 1);
        } catch (SQLException e) {
          System.out.println(e.toString());
        }
        MainFrame frame = new MainFrame();
        frame.draw();
      }
    });

  }
}
