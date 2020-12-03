package fe.panels;

import be.domain.DiscursiveQuestion;
import be.domain.base.Question;
import be.domain.base.TypeQuestion;
import be.services.QuestionService;
import be.utils.ValidateTextArea;
import be.utils.Validation;
import fe.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FormDicursiveQuestionPanel extends FormQuestionPanel {

  private Question question;
  private JTextField typeQuestionTxt;
  private JButton saveBtn;

  public FormDicursiveQuestionPanel(MainFrame frame) {
    super(frame);
    this.question = null;

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent arg0) {
        if (question == null) {
          getIdTxt().setText("");
          typeQuestionTxt.setText("");
          getQuestionTxt().setText("");
        } else {
          getIdTxt().setText(Integer.toString(question.getId()));
          typeQuestionTxt.setText(question.getTypeQuestion().getType());
          getQuestionTxt().setText(question.getQuestion());
        }
      }
    });
  }

  @Override
  public void setQuestion(Question question) {
    this.question = question;
  }

  @Override
  public Question getQuestion() {
    return question;
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
        if (validateTxtArea()) {
          Question quest = new DiscursiveQuestion();
          quest.setQuestion(getQuestionTxt().getText());
          quest.setTypeQuestion(TypeQuestion.DISCURSIVE);

          if (question == null) {
            boolean create = quest.createQuestion();
            if (create) {
              JOptionPane
                  .showMessageDialog(FormDicursiveQuestionPanel.this, "Questão criado com sucesso!",
                      MainFrame.TITLE,
                      JOptionPane.INFORMATION_MESSAGE);
              getFrame().showQuestionPanel();
              return;
            }
            JOptionPane
                .showMessageDialog(FormDicursiveQuestionPanel.this,
                    "Não foi possivel criar sua questão =(, por favor"
                        + "entre em contato com o suporte ^^",
                    MainFrame.TITLE,
                    JOptionPane.INFORMATION_MESSAGE);
            getFrame().showQuestionPanel();
            return;
          } else {
            quest.setId(Integer.parseInt(getIdTxt().getText()));
            quest.setTypeQuestion(typeQuestionTxt.getText());
            boolean update = QuestionService.updateQuestion(quest);

            if (!update) {
              JOptionPane
                  .showMessageDialog(FormDicursiveQuestionPanel.this,
                      "Não foi possivel alterar sua questão,"
                          + "Por favor entre em contato com o suporte.",
                      MainFrame.TITLE,
                      JOptionPane.INFORMATION_MESSAGE);
              getFrame().showQuestionPanel();
              return;
            }

              JOptionPane
                  .showMessageDialog(FormDicursiveQuestionPanel.this,
                      "Questão Alterada com sucesso!",
                      MainFrame.TITLE,
                      JOptionPane.INFORMATION_MESSAGE);
              getFrame().showQuestionPanel();
          }
        } else {
          JOptionPane.showMessageDialog(FormDicursiveQuestionPanel.this, "Preencha todos os campos",
              MainFrame.TITLE
              , JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    setSaveBtn(saveBtn);
  }

  private boolean validateTxtArea() {
    Validation validation = new ValidateTextArea();
    return validation.validate(getQuestionTxt());
  }
}
