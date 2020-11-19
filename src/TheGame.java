import BE.services.QuestionService;
import javax.swing.SwingUtilities;

import FE.MainFrame;

public class TheGame {

  public static void main(String[] args) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        QuestionService.createQuestion("1", "saijsaopsioasia");
        System.out.println(QuestionService.getQuestions());
        MainFrame frame = new MainFrame();
        frame.draw();
      }
    });

  }
}
