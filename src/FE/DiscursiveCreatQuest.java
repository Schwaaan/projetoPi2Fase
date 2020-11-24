package FE;

import BE.domain.DiscursiveQuestion;
import BE.domain.base.Question;
import BE.services.QuestionService;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DiscursiveCreatQuest extends FormQuestionPanel {

  private static final Insets FIELD_INSETS = new Insets(5, 10, 0, 0);

  private Question question;
  private JTextField typeQuestionTxt;
  private JButton saveBtn;

  public DiscursiveCreatQuest(MainFrame frame) {
    super(frame);
    this.question = null;
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent arg0) {
      }
    });
  }

  private void createAlternatives() {
    JLabel label;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  @Override
  public void createTypeQuestionComponent() {
    JLabel label;
    label = new JLabel("Tipo da Questão");
    addComponent(label, 1, 0);
    typeQuestionTxt = new JTextField(1);
    typeQuestionTxt.setEditable(false);
    addComponent(typeQuestionTxt, 1, 1);
  }

  @Override
  public void createSaveButton() {
    saveBtn = new JButton("Salvar");
    saveBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        if (!getQuestionTxt().getText().isEmpty()) {
          Question quest = new DiscursiveQuestion(getQuestionTxt().getText());
          if (question == null) {
            quest.createQuestion();
            JOptionPane.showMessageDialog(DiscursiveCreatQuest.this, "Questão criado com sucesso!",
                "The Game",
                JOptionPane.INFORMATION_MESSAGE);
            getFrame().showQuestionPanel();
          } else {
            quest.setId(question.getId());
            QuestionService.updateQuestion(quest);
            JOptionPane
                .showMessageDialog(DiscursiveCreatQuest.this, "Questão Alterada com sucesso!",
                    "The Game",
                    JOptionPane.INFORMATION_MESSAGE);
            getFrame().showQuestionPanel();

          }
        } else {
          JOptionPane.showMessageDialog(DiscursiveCreatQuest.this, "Preencha todos os campos",
              "Erro ao criar questão",
              JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });

    setSaveBtn(saveBtn);
  }
}
