package javaapplication1;

import javaapplication1.LoginFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
