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
  private QuestionTablePanel questionPanel;

  private FormQuestionPanel[] formQuestionPanels = new FormQuestionPanel[2];

  public static int INDEX_OBJECTIVE = 0; // é public para outras classes usarem
  public static int INDEX_DISCURSIVE = 1; // é public para outras classes usarem

  private DecideTypeQuestionPanel questionDecisivePanel;

  public MainFrame() {
    this.layout = new CardLayout();
    this.cardsPanel = new JPanel();
    this.cardsPanel.setLayout(layout);
    add(cardsPanel);
    this.createCards();

    this.showHomePanel();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public FormQuestionPanel[] getFormQuestionPanels() {
    return this.formQuestionPanels;
  }

  public void draw() {
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void createCards() {
    this.homePanel = new HomePanel(this);
    this.cardsPanel.add(this.homePanel, HomePanel.class.getName());

    this.questionPanel = new QuestionTablePanel(this);
    this.cardsPanel.add(this.questionPanel, QuestionTablePanel.class.getName());// criar cards e adicionar dentro dos

    this.questionDecisivePanel = new DecideTypeQuestionPanel(this);
    this.cardsPanel.add(this.questionDecisivePanel, DecideTypeQuestionPanel.class.getName());

    this.formQuestionPanels[INDEX_OBJECTIVE] = new FormObjectiveQuestionPanel(this);
    this.cardsPanel.add(this.formQuestionPanels[INDEX_OBJECTIVE],
        formQuestionPanels[INDEX_OBJECTIVE].getClass().getName());

    this.formQuestionPanels[INDEX_DISCURSIVE] = new FormDicursiveQuestionPanel(this);
    this.cardsPanel.add(this.formQuestionPanels[INDEX_DISCURSIVE],
        formQuestionPanels[INDEX_DISCURSIVE].getClass().getName());

  }

  public void showHomePanel() {
    this.layout.show(this.cardsPanel, HomePanel.class.getName()); // mostrar cards que esta no layout
    // tem que ter um para cada card
  }

  public void showForm(Question question, int index) {
    // aqui estaria a proposta do polimorfismo
    formQuestionPanels[index].setQuestion(question); // método chamado da classe pai e executado na filha
    this.layout.show(this.cardsPanel, formQuestionPanels[index].getClass().getName()); // aqui chama pela classe pai uma filha
  }

  public void showQuestionPanel() {
    questionPanel.reload();
    this.layout.show(this.cardsPanel, QuestionTablePanel.class.getName());
  }

  public void showQuestionDecisivePanel() {
    this.layout.show(this.cardsPanel, DecideTypeQuestionPanel.class.getName());
  }
}
