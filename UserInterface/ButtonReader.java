package UserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;

import javax.swing.JTextField;

public class ButtonReader implements ActionListener {
  Function<Integer, ?> function;
  private JTextField textField;

  public ButtonReader(Function<Integer, ?> function, JTextField textField) {
    this.function = function;
    this.textField = textField;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      int value = Integer.parseInt(textField.getText());
      function.apply(value);
    } catch (NumberFormatException ex) {
      System.err.println("Invalid input: " + textField.getText());
    }
  }
}