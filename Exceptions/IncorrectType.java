package Exceptions;

import javax.swing.JOptionPane;

import UserInterface.Messages;

public class IncorrectType extends Exception {
  public IncorrectType(String message) {
    super(message);
  }

  public void show() {
    JOptionPane.showMessageDialog(null, this.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
  }
}
