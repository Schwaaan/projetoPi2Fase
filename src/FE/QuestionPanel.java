package FE;

import BE.domain.base.Question;
import BE.domain.base.Type;
import BE.services.QuestionService;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class QuestionPanel extends JPanel {

  MainFrame frame;
  QuestionTableModel tableModel;

  private JButton btnCreate;
  private JButton btnChange;
  private JButton btnRemove;
  private JButton btnInit;

  private JTable tableQuestions;

  public QuestionPanel(MainFrame frame) {
    this.frame = frame;

    setLayout(new BorderLayout(10, 10));

    createBtns();
    createTable();
  }

  public void reload() {
    tableModel.load(QuestionService.getQuestions());
  }

  private void createTable() {
    tableModel = new QuestionTableModel(QuestionService.getQuestions());
    tableQuestions = new JTable(tableModel);
    tableQuestions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// uma linha de cada vez

    tableQuestions.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
          if (tableQuestions.getSelectedRow() >= 0) {
            ablebtns();
          } else {
            disableBtns();
          }
        }
      }

    });
    JScrollPane scroll = new JScrollPane(tableQuestions);

    add(scroll, BorderLayout.CENTER);
  }

  private void createBtns() {
    JPanel panelBtn = new JPanel();
    FlowLayout layout = (FlowLayout) panelBtn.getLayout();
    layout.setAlignment(FlowLayout.RIGHT);

    btnInit = new JButton("Inicio");

    btnInit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        frame.showHomePanel();
      }
    });

    panelBtn.add(btnInit);

    btnCreate = new JButton("Adicionar");

    btnCreate.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        frame.showQuestionDecisivePanel();
      }
    });

    panelBtn.add(btnCreate);

    btnChange = new JButton("Alterar");

    panelBtn.add(btnChange);

    btnChange.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {

        if (tableModel.getQuestion(tableQuestions.getSelectedRow()).getTypeQuestion().equals(Type.OBJECTIVE)) {
          // se for questão objetiva chama a ObjectiveCreatQuest
          ObjectiveCreatQuest objectiveCreatQuest = new ObjectiveCreatQuest(frame,
              tableModel.getQuestion(tableQuestions.getSelectedRow()));
          frame.showForm(objectiveCreatQuest.getQuestion(), objectiveCreatQuest);

        } else {
          // se for questão discursiva chama a DiscursiveCreatQuest
          DiscursiveCreatQuest discursiveCreatQuest = new DiscursiveCreatQuest(frame);
          frame.showForm(tableModel.getQuestion(tableQuestions.getSelectedRow()), discursiveCreatQuest);

        }
      }
    });

    panelBtn.add(btnChange);

    btnRemove = new JButton("Remover");

    btnRemove.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        Question quest = tableModel.getQuestion(tableQuestions.getSelectedRow());
        int answer = JOptionPane.showConfirmDialog(QuestionPanel.this, "Você deseja remover essa tarefa ?", "The Game",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (answer == JOptionPane.YES_OPTION) {
          QuestionService.deleteQuestion(quest);
          tableModel.delete(quest);
        }
      }
    });
    disableBtns();
    panelBtn.add(btnRemove);

    add(panelBtn, BorderLayout.NORTH);

  }

  private void disableBtns() {
    btnChange.setEnabled(false);
    btnRemove.setEnabled(false);
  }

  private void ablebtns() {
    btnChange.setEnabled(true);
    btnRemove.setEnabled(true);
  }
}
