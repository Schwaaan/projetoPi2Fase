package fe.panels.login;

import be.domain.user.User;
import be.services.UserService;
import fe.MainFrame;
import fe.panels.FormObjectiveQuestionPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountPanel extends JPanel {

  private static Insets FIELD_INSETS = new Insets(5, 5, 5, 5);

  private MainFrame frame;
  private GridBagLayout layout;
  private GridBagConstraints constraints;

  private JTextField nameTxt;
  private JTextField lastNameTxt;
  private JTextField typeTxt;
  private JTextField passwordTxt;

  private JButton saveBtn;
  private JButton cancelBtn;

  public CreateAccountPanel(MainFrame frame) {
    this.frame = frame;

    layout = new GridBagLayout();
    constraints = new GridBagConstraints();

    setLayout(layout);
    setBackground(new Color(118, 206, 166));

    createForm();

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent arg0) {
        nameTxt.setText("");
        lastNameTxt.setText("");
        passwordTxt.setText("");
      }
    });
  }

  private void createForm() {
    JLabel label;
    label = new JLabel("Nome");
    nameTxt = new JTextField();
    addComponent(label, 0, 0);
    addComponent(nameTxt, 0, 1);

    label = new JLabel("Sobrenome");
    lastNameTxt = new JTextField();
    addComponent(label, 1, 0);
    addComponent(lastNameTxt, 1, 1);

    label = new JLabel("Tipo");
    typeTxt = new JTextField("ADMIN");
    typeTxt.setEditable(false);
    addComponent(label, 2, 0);
    addComponent(typeTxt, 2, 1);

    label = new JLabel("Senha");
    passwordTxt = new JPasswordField();
    addComponent(label, 3, 0);
    addComponent(passwordTxt, 3, 1);

    createBtn();

  }

  private void createBtn() {
    JPanel panel = new JPanel();
    panel.setBackground(new Color(118, 206, 166));

    saveBtn = new JButton("Salvar");
    saveBtn.setForeground(new Color(255, 255, 255));
    saveBtn.setBackground(new Color(4, 141, 95));
    saveBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        User user = new User();
        user.setName(nameTxt.getText());
        user.setLastName(lastNameTxt.getText());
        user.setPassword(passwordTxt.getText());

        boolean insert = UserService.insert(user);
        if (!insert) {
          JOptionPane.showMessageDialog(CreateAccountPanel.this, "Não foi possivel criar seu "
                  + "usuário por favor entre em contato com o suporte >:", MainFrame.TITLE,
              JOptionPane.INFORMATION_MESSAGE);
          return;
        }
        JOptionPane
            .showMessageDialog(CreateAccountPanel.this, "Usuário criado com sucesso!",
                MainFrame.TITLE,
                JOptionPane.INFORMATION_MESSAGE);
        frame.showHomePanel();
      }
    });
    panel.add(saveBtn);

    cancelBtn = new JButton("Cancelar");
    cancelBtn.setForeground(new Color(255, 255, 255));
    cancelBtn.setBackground(new Color(4, 141, 95));
    cancelBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        frame.showHomePanel();
      }
    });
    panel.add(cancelBtn);

    addComponent(panel, 4, 1);
  }

  public void addComponent(JComponent comp, int row, int col) {
    addComponent(comp, row, col, 1, 1);
  }

  public void addComponent(JComponent comp, int row, int col, int width, int height) {
    constraints.gridx = col;
    constraints.gridy = row;
    constraints.gridwidth = width;
    constraints.gridheight = height;

    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.insets = FIELD_INSETS;

    layout.setConstraints(comp, constraints);
    add(comp);
  }

}