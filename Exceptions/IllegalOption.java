package Exceptions;

import javax.swing.JOptionPane;

public class IllegalOption extends Exception {
  public IllegalOption(String message) {
    super(message);
  }

  public void show() {
    JOptionPane.showMessageDialog(null, this.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
  }
}
