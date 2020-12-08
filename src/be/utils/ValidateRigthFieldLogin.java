package be.utils;

import javax.swing.JComponent;
import javax.swing.JTextField;

import be.domain.user.User;
import be.services.UserService;

public class ValidateRigthFieldLogin extends Validation {
    @Override
    public boolean validate(JComponent jComponents) {
        JTextField field = (JTextField) jComponents;

        for (User user : UserService.getNameList()) {
            if (field.getText().equals(user.getName()) || field.getText().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
