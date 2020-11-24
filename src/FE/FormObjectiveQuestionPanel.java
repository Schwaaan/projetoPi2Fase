package FE;

import BE.domain.Alternative;
import BE.domain.ObjectiveQuestion;
import BE.domain.base.Question;
import BE.services.QuestionService;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FormObjectiveQuestionPanel extends FormQuestionPanel {

  private Question question;
  private JTextField textFieldA;
  private JTextField textFieldB;
  private JTextField textFieldC;

  private JTextField typeQuestionTxt;
  private JCheckBox checkBoxATrue;
  private JCheckBox checkBoxAFalse;
  private JCheckBox checkBoxBTrue;
  private JCheckBox checkBoxBFalse;
  private JCheckBox checkBoxCFalse;
  private JCheckBox checkBoxCTrue;

  private JButton saveBtn;
  private ButtonGroup btnGroupAltC;
  private ButtonGroup btnGroupAltB;
  private ButtonGroup btnGroupAltA;

  public FormObjectiveQuestionPanel(MainFrame frame) {
    super(frame);
    this.question = null;

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent arg0) {
        if (Objects.isNull(question)) {
          getIdTxt().setText("");
          typeQuestionTxt.setText("");
          getQuestionTxt().setText("");
          textFieldA.setText("");
          textFieldB.setText("");
          textFieldC.setText("");

          btnGroupAltA.clearSelection();
          btnGroupAltB.clearSelection();
          btnGroupAltC.clearSelection();

        } else {
          ObjectiveQuestion objectiveQuestion = (ObjectiveQuestion) question;

          getIdTxt().setText(Integer.toString(question.getId()));
          typeQuestionTxt.setText(String.valueOf(objectiveQuestion.getTypeQuestion()));
          getQuestionTxt().setText(objectiveQuestion.getQuestion());

          textFieldA.setText(objectiveQuestion.getAlternativeList().get(0).getAlternative());
          textFieldB.setText(objectiveQuestion.getAlternativeList().get(1).getAlternative());
          textFieldC.setText(objectiveQuestion.getAlternativeList().get(2).getAlternative());

          checkBoxAFalse.setSelected(!objectiveQuestion.getAlternativeList().get(0).getRigthAlternative());
          checkBoxATrue.setSelected(objectiveQuestion.getAlternativeList().get(0).getRigthAlternative());
          checkBoxBFalse.setSelected(!objectiveQuestion.getAlternativeList().get(1).getRigthAlternative());
          checkBoxBTrue.setSelected(objectiveQuestion.getAlternativeList().get(1).getRigthAlternative());
          checkBoxCFalse.setSelected(!objectiveQuestion.getAlternativeList().get(2).getRigthAlternative());
          checkBoxCTrue.setSelected(objectiveQuestion.getAlternativeList().get(2).getRigthAlternative());
        }
      }
    });
  }

  private void createAlternatives() {
    JLabel label;

    label = new JLabel("Alternativas: ");
    label.setForeground(Color.BLACK);
    addComponent(label, 7, 0, 1, 1);
    label = new JLabel("V");
    addComponent(label, 7, 2, 1, 1);
    label = new JLabel("F");
    addComponent(label, 7, 3, 1, 1);
    btnGroupAltA = new ButtonGroup();
    btnGroupAltB = new ButtonGroup();
    btnGroupAltC = new ButtonGroup();

    label = new JLabel("1) ");
    label.setForeground(Color.BLACK);
    addComponent(label, 8, 0);
    textFieldA = new JTextField(1);
    addComponent(textFieldA, 8, 1);

    checkBoxATrue = new JCheckBox();
    btnGroupAltA.add(checkBoxATrue);
    addComponent(checkBoxATrue, 8, 2);

    checkBoxAFalse = new JCheckBox();
    btnGroupAltA.add(checkBoxAFalse);
    addComponent(checkBoxAFalse, 8, 3);

    label = new JLabel("2) ");
    label.setForeground(Color.BLACK);
    addComponent(label, 9, 0);
    textFieldB = new JTextField(1);
    addComponent(textFieldB, 9, 1);

    checkBoxBTrue = new JCheckBox();
    btnGroupAltB.add(checkBoxBTrue);
    addComponent(checkBoxBTrue, 9, 2);

    checkBoxBFalse = new JCheckBox();
    btnGroupAltB.add(checkBoxBFalse);
    addComponent(checkBoxBFalse, 9, 3);

    label = new JLabel("3) ");
    label.setForeground(Color.BLACK);
    addComponent(label, 10, 0);
    textFieldC = new JTextField(1);
    addComponent(textFieldC, 10, 1);

    checkBoxCTrue = new JCheckBox();
    btnGroupAltC.add(checkBoxCTrue);
    addComponent(checkBoxCTrue, 10, 2);

    checkBoxCFalse = new JCheckBox();
    btnGroupAltC.add(checkBoxCFalse);
    addComponent(checkBoxCFalse, 10, 3);

    label = new JLabel();
    addComponent(label, 14, 0, 1, 1);

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
    createAlternatives();
  }

  @Override
  public void createSaveButton() {
    this.saveBtn = new JButton("Salvar");
    saveBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        if (validator()) {

          List<Alternative> alternativeList = this.getListAlternative(
              new Alternative(textFieldA.getText(), checkBoxATrue.isSelected()),
              new Alternative(textFieldB.getText(), checkBoxBTrue.isSelected()),
              new Alternative(textFieldC.getText(), checkBoxCTrue.isSelected()));

          System.out.println(alternativeList.get(0).getRigthAlternative());
          
              Question quest = new ObjectiveQuestion(getQuestionTxt().getText(), alternativeList);

          if (Objects.isNull(getQuestion())) {
            quest.createQuestion();
            JOptionPane.showMessageDialog(FormObjectiveQuestionPanel.this, "Questão criado com sucesso!", "The Game",
                JOptionPane.INFORMATION_MESSAGE);
            getFrame().showQuestionPanel();
          } else {
            quest.setId(question.getId());
            quest.setTypeQuestion(typeQuestionTxt.getText());
            QuestionService.updateQuestion(quest);
            JOptionPane.showMessageDialog(FormObjectiveQuestionPanel.this, "Questão Alterada com sucesso!", "The Game",
                JOptionPane.INFORMATION_MESSAGE);
            getFrame().showQuestionPanel();
          }
        } else {
          JOptionPane.showMessageDialog(FormObjectiveQuestionPanel.this, "Preencha todos os campos",
              "Erro ao criar questão", JOptionPane.INFORMATION_MESSAGE);
        }
      }

      private List<Alternative> getListAlternative(Alternative alternative, Alternative alternative1,
          Alternative alternative2) {
        List<Alternative> alternatives = new ArrayList<>();
        alternatives.add(alternative);
        alternatives.add(alternative1);
        alternatives.add(alternative2);
        return alternatives;
      }
    });
    setSaveBtn(saveBtn);
  }

  private boolean validator() {
    return 
        !getQuestionTxt().getText().isEmpty() && 
        !textFieldA.getText().isEmpty() && 
        !textFieldB.getText().isEmpty() && 
        !textFieldC.getText().isEmpty() &&
        (checkBoxATrue.isSelected() || checkBoxAFalse.isSelected()) &&
        (checkBoxBTrue.isSelected() || checkBoxBFalse.isSelected()) &&
        (checkBoxCTrue.isSelected() || checkBoxCFalse.isSelected());
  }
}
