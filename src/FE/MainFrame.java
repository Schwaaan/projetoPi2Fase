package FE;

import BE.domain.base.Question;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

  private CardLayout layout;
  private JPanel cardsPanel;
  private HomePanel homePanel;
  private QuestionPanel questionPanel;
  private ObjectiveCreatQuest objectiveCreatQuest;
  private DiscursiveCreatQuest discursiveCreatQuest;
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
    // panel
    this.objectiveCreatQuest = new ObjectiveCreatQuest(this);
    this.cardsPanel.add(this.objectiveCreatQuest, ObjectiveCreatQuest.class.getName());

    this.questionDecisivePanel = new QuestionDecisivePanel(this);
    this.cardsPanel.add(this.questionDecisivePanel, QuestionDecisivePanel.class.getName());

    this.discursiveCreatQuest = new DiscursiveCreatQuest(this);
    this.cardsPanel.add(this.discursiveCreatQuest, DiscursiveCreatQuest.class.getName());

  }

  public void showHomePanel() {
    this.layout
        .show(this.cardsPanel, HomePanel.class.getName()); // mostrar cards que esta no layout
    // tem que ter um para cada card
  }

  public void showObjectiveCreatQuest(Question question) {
    this.objectiveCreatQuest.setQuestion(question);
    this.layout.show(this.cardsPanel, ObjectiveCreatQuest.class.getName());
  }

  public void showDiscursiveCreatQuest(Question question) {
    this.discursiveCreatQuest.setQuestion(question);
    this.layout.show(this.cardsPanel, DiscursiveCreatQuest.class.getName());
  }

  public void showQuestionPanel() {
    questionPanel.reload();
    this.layout.show(this.cardsPanel, QuestionPanel.class.getName());
  }

  public void showQuestionDecisivePanel() {
    this.layout.show(this.cardsPanel, QuestionDecisivePanel.class.getName());
  }
}
