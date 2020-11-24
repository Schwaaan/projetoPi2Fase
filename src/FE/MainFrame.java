package FE;

import BE.domain.base.Question;
import java.awt.CardLayout;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

  private CardLayout layout;
  private JPanel cardsPanel;
  private HomePanel homePanel;
  private QuestionPanel questionPanel;
  private FormQuestionPanel formQuestionPanel;
  private FormQuestionPanel formQuestionPanel2;
  private QuestionDecisivePanel questionDecisivePanel;

  public MainFrame() {
    this.layout = new CardLayout();
    this.cardsPanel = new JPanel();
    this.cardsPanel.setLayout(layout);
    add(cardsPanel);
    this.createCards();

    this.showHomePanel();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void draw() {
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void createCards() {
    this.homePanel = new HomePanel(this);
    this.cardsPanel.add(this.homePanel, HomePanel.class.getName());

    this.questionPanel = new QuestionPanel(this);
    this.cardsPanel.add(this.questionPanel,
        QuestionPanel.class.getName());// criar cards e adicionar dentro dos

    this.questionDecisivePanel = new QuestionDecisivePanel(this);
    this.cardsPanel.add(this.questionDecisivePanel, QuestionDecisivePanel.class.getName());

    this.formQuestionPanel = new ObjectiveCreatQuest(this);
    this.cardsPanel.add(this.formQuestionPanel, ObjectiveCreatQuest.class.getName());

    this.formQuestionPanel2 = new DiscursiveCreatQuest(this);
    this.cardsPanel.add(this.formQuestionPanel2, DiscursiveCreatQuest.class.getName());

  }

  public void showHomePanel() {
    this.layout
        .show(this.cardsPanel, HomePanel.class.getName()); // mostrar cards que esta no layout
    // tem que ter um para cada card
  }

  public void showForm(Question question, FormQuestionPanel formQuestionPanel) {
    this.formQuestionPanel = formQuestionPanel;
    formQuestionPanel.setQuestion(question);
    formQuestionPanel.validateQuestion();
    this.layout.show(this.cardsPanel, formQuestionPanel.getClass().getName());
  }

  public void showQuestionPanel() {
    questionPanel.reload();
    this.layout.show(this.cardsPanel, QuestionPanel.class.getName());
  }

  public void showQuestionDecisivePanel() {
    this.layout.show(this.cardsPanel, QuestionDecisivePanel.class.getName());
  }
}
