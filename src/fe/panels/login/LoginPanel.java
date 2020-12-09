package fe.panels.login;

import be.utils.ValidateRigthFieldLogin;
import be.utils.ValidateText;
import be.utils.Validation;
import fe.MainFrame;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {

  private MainFrame frame;
  private JTextField loginTxt;
  private JPasswordField pswordTxt;
  private JButton loginBtn;
  private JButton createAccountBtn;

  private GridBagLayout layout;
  private GridBagConstraints constraints;
  private static Insets FIELD_INSETS = new Insets(5, 5, 5, 5);

  public LoginPanel(MainFrame frame) {
    this.frame = frame;

    layout = new GridBagLayout();
    constraints = new GridBagConstraints();

    setLayout(layout);
    setBackground(new Color(118, 206, 166));

    createForm();
  }

  private void createForm() {
    JLabel label;

    label = new JLabel("Login");
    addComponent(label, 0, 0);

    loginTxt = new JTextField();
    addComponent(loginTxt, 0, 1);

    label = new JLabel("Senha");
    addComponent(label, 1, 0);

    pswordTxt = new JPasswordField();
    addComponent(pswordTxt, 1, 1);

    createBtn();
  }

  private void createBtn() {
    JPanel panel = new JPanel();

    loginBtn = new JButton("Entrar");
    loginBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        if (validator() && validatorFilds()) {
          frame.showQuestionPanel();
        } else {
          JOptionPane
              .showMessageDialog(LoginPanel.this, "Login/Senha incorreto(s)", MainFrame.TITLE,
                  JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    panel.add(loginBtn);

    createAccountBtn = new JButton("Criar Conta");
    createAccountBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        frame.showFormCreateAccount();
      }
    });
    panel.add(createAccountBtn);

    loginBtn.setForeground(new Color(255, 255, 255));
    loginBtn.setBackground(new Color(4, 141, 95));
    createAccountBtn.setForeground(new Color(255, 255, 255));
    createAccountBtn.setBackground(new Color(4, 141, 95));
    panel.setBackground(new Color(118, 206, 166));

    addComponent(panel, 2, 1);
  }

  protected boolean validatorFilds() {
    Validation validateLogin = new ValidateRigthFieldLogin();

    return validateLogin.validate(loginTxt) && validateLogin.validate(pswordTxt);
  }

  protected boolean validator() {
    Validation validator = new ValidateText();

    return validator.validate(loginTxt) && validator.validate(pswordTxt);
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