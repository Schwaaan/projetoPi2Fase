package FE;

import BE.domain.Alternative;
import BE.domain.Question;
import BE.services.AlternativeService;
import BE.services.QuestionService;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class CreatQuestPanel extends JPanel {
  private final int NUM_ALTERNATIVES = 5;

  private static final Insets FIELD_INSETS = new Insets(5, 10, 0, 0);

  private MainFrame frame;
  private Question question;
  private List<Alternative> alternatives = new ArrayList<>();
  private JButton deletebtn;
  private JButton creatbtn;
  private JTextArea questionTxt;
  private JTextField alternativeA;
  private JTextField alternativeB;
  private JTextField alternativeC;
  private JTextField alternativeD;
  private JTextField alternativeE;
  private GridBagLayout layout;
  private GridBagConstraints constraints;
  private JCheckBox checkBoxA;
  private JCheckBox checkBoxB;
  private JCheckBox checkBoxC;
  private JCheckBox checkBoxD;
  private JCheckBox checkBoxE;
  private JLabel labelR;

  public CreatQuestPanel(MainFrame frame) {
    this.frame = frame;
    JPanel contentPane = new JPanel();
    contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    contentPane.setBackground(Color.CYAN);
    this.question = null;
    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent arg0) {
        if (question == null) {
          questionTxt.setText("");

          alternativeA.setText("");
          alternativeB.setText("");
          alternativeC.setText("");
          alternativeD.setText("");
          alternativeE.setText("");
        } else {
          questionTxt.setText(question.getQuestion());

          alternativeA.setText(alternatives.get(0).toString());
          alternativeB.setText(alternatives.get(1).toString());
          alternativeC.setText(alternatives.get(2).toString());
          alternativeD.setText(alternatives.get(3).toString());
          alternativeE.setText(alternatives.get(4).toString());
        }
      }
    });
    // setContentPane(contentPane);
    setSize(1000, 650);
    layout = new GridBagLayout();
    setLayout(layout);

    constraints = new GridBagConstraints();

    init();
  }

  private void init() {
    ActionEventHandler handler = new ActionEventHandler();
    JLabel label;

    /**
     * Componentes de texto
     */
    label = new JLabel("NOVA QUESTÃO");
    addComponent(label, 0, 2, 1, 1);

    label = new JLabel("Questão: ");
    label.setForeground(Color.BLACK);
    addComponent(label, 2, 1, 1, 1);
    questionTxt = new JTextArea(3, 10);
    JScrollPane scroll = new JScrollPane(questionTxt);
    addComponent(scroll, 2, 2, 2, 3);

    /** Alternativas */
    label = new JLabel("Alternativas: ");
    label.setForeground(Color.BLACK);
    addComponent(label, 6, 1, 1, 1);
    labelR = new JLabel("Questão Correta:");
    addComponent(labelR, 6, 4, 1, 1);
    ButtonGroup buttonGroup = new ButtonGroup();

    /** Alternativa 1 */
    label = new JLabel("1) ");
    label.setForeground(Color.BLACK);
    addComponent(label, 8, 1, 1, 1);
    alternativeA = new JTextField(30);
    addComponent(alternativeA, 8, 2, 2, 1);
    checkBoxA = new JCheckBox();
    buttonGroup.add(checkBoxA);
    checkBoxA.setHorizontalAlignment(JCheckBox.CENTER);
    addComponent(checkBoxA, 8, 4, 1, 1);

    /** Alternativa 2 */
    label = new JLabel("2) ");
    label.setForeground(Color.BLACK);
    addComponent(label, 9, 1, 1, 1);
    alternativeB = new JTextField(2);
    addComponent(alternativeB, 9, 2, 2, 1);
    checkBoxB = new JCheckBox();
    buttonGroup.add(checkBoxB);
    checkBoxB.setHorizontalAlignment(JCheckBox.CENTER);
    addComponent(checkBoxB, 9, 4, 1, 1);

    /** Alternativa 3 */
    label = new JLabel("3) ");
    label.setForeground(Color.BLACK);
    addComponent(label, 10, 1, 1, 1);
    alternativeC = new JTextField(2);
    addComponent(alternativeC, 10, 2, 2, 1);
    checkBoxC = new JCheckBox();
    buttonGroup.add(checkBoxC);
    checkBoxC.setHorizontalAlignment(JCheckBox.CENTER);
    addComponent(checkBoxC, 10, 4, 1, 1);

    /** Alternativa 4 */
    label = new JLabel("4) ");
    label.setForeground(Color.BLACK);
    addComponent(label, 11, 1, 1, 1);
    alternativeD = new JTextField(2);
    addComponent(alternativeD, 11, 2, 2, 1);
    checkBoxD = new JCheckBox();
    buttonGroup.add(checkBoxD);
    checkBoxD.setHorizontalAlignment(JCheckBox.CENTER);
    addComponent(checkBoxD, 11, 4, 1, 1);

    /** Alternativa 5 */
    label = new JLabel("5) ");
    label.setForeground(Color.BLACK);
    addComponent(label, 12, 1, 1, 1);
    alternativeE = new JTextField(2);
    addComponent(alternativeE, 12, 2, 2, 1);
    checkBoxE = new JCheckBox();
    buttonGroup.add(checkBoxE);
    checkBoxE.setHorizontalAlignment(JCheckBox.CENTER);
    addComponent(checkBoxE, 12, 4, 1, 1);

    // Adicionando Eventos no CheckBoxes
    addListenerInBtn(checkBoxA);
    addListenerInBtn(checkBoxB);
    addListenerInBtn(checkBoxC);
    addListenerInBtn(checkBoxD);
    addListenerInBtn(checkBoxE);

    label = new JLabel();
    addComponent(label, 14, 0, 1, 1);
    creatbtn = new JButton("Criar");
    creatbtn.addActionListener(handler);
    addComponent(creatbtn, 14, 2, 1, 1);

    deletebtn = new JButton("Cancelar");
    deletebtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        frame.showQuestionPanel();
      }
    });
    addComponent(deletebtn, 14, 3, 1, 1);
  }

  private void addListenerInBtn(JCheckBox checkBox) {
    checkBox.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          System.out.println("Item selecionado");
        }
      }
    });
  };

  public void setQuestion(Question question) {
    this.question = question;

    for (Alternative alt : AlternativeService.getAlternatives()) {
      if (alt.getAlternativeQuestion().equals(question)) {
        this.alternatives.add(alt);
      }
    }
  }

  private void addComponent(JComponent comp, int row, int col, int width, int height) {
    constraints.gridx = col;
    constraints.gridy = row;
    constraints.gridwidth = width;
    constraints.gridheight = height;

    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = FIELD_INSETS;
    constraints.anchor = GridBagConstraints.NORTHWEST;

    layout.setConstraints(comp, constraints);
    add(comp);
  }

  private class ActionEventHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {

      if (event.getSource() == creatbtn) {

        if (!questionTxt.getText().isEmpty() & !alternativeA.getText().isEmpty() & !alternativeB.getText().isEmpty()
            & !alternativeC.getText().isEmpty() & !alternativeD.getText().isEmpty()
            & !alternativeE.getText().isEmpty()) {

          Question quest = new Question("1", questionTxt.getText());

          Alternative altA = new Alternative(quest, alternativeA.getText(), false);
          Alternative altB = new Alternative(quest, alternativeB.getText(), false);
          Alternative altC = new Alternative(quest, alternativeC.getText(), false);
          Alternative altD = new Alternative(quest, alternativeD.getText(), false);
          Alternative altE = new Alternative(quest, alternativeE.getText(), false);

          if (question == null) {
            // TODO FAZER A VALIDÇÃO DO RADIONBUTON
            QuestionService.createQuestion(quest);

            AlternativeService.createAlternative(altA);
            AlternativeService.createAlternative(altB);
            AlternativeService.createAlternative(altC);
            AlternativeService.createAlternative(altD);
            AlternativeService.createAlternative(altE);

            JOptionPane.showMessageDialog(CreatQuestPanel.this, "Questão criado com sucesso!", "The Game",
                JOptionPane.INFORMATION_MESSAGE);

          } else {
            quest.setId(question.getId());
            QuestionService.updateQuestion(quest);

             // tem que fazer updade tanto na lista (this.alternative) do DB quanto na lista dessa classe
             altA.setId(alternatives.get(0).getId());
             AlternativeService.updateAlternative(altA);
             alternatives.set(0, altA);
             
             altB.setId(alternatives.get(1).getId());
             AlternativeService.updateAlternative(altB);
             alternatives.set(1, altB);
             
             altC.setId(alternatives.get(2).getId());
             AlternativeService.updateAlternative(altC);
             alternatives.set(2, altC);
             
            altD.setId(alternatives.get(3).getId());
            AlternativeService.updateAlternative(altD);
            alternatives.set(3, altD);

            altE.setId(alternatives.get(4).getId());
            AlternativeService.updateAlternative(altE);
            alternatives.set(4, altE);
            
            JOptionPane.showMessageDialog(CreatQuestPanel.this, "Questão Alterada com sucesso!", "The Game",
            JOptionPane.INFORMATION_MESSAGE);
          }
          frame.showQuestionPanel();
        } else {
          JOptionPane.showMessageDialog(CreatQuestPanel.this, "Preencha todos os campos", "Erro ao criar questão",
              JOptionPane.INFORMATION_MESSAGE);
        }
      }
    }
  } // fim da classe ActionEventHandler

} // fim da classe ActionEventFrame
