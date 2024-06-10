package UserInterface;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;

public class UserPanel extends JPanel {
  private static final int ALTURA_BARRA_TITULO = 20;
  private JFrame frame;
  ArrayList<String> messages;
  Function<Integer, ?> function;

  public UserPanel(String name, ArrayList<String> messages, Function<Integer, ?> function) {
    this.function = function;
    this.messages = messages;

    frame = new JFrame(name);
    frame.add(this);
    frame.setSize(940, 300 + 77 + ALTURA_BARRA_TITULO);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
  }

  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g; // por motivos de compatibilidade com a AWT
    g2d.setColor(Color.GRAY); // define a cor em uso
    g2d.setFont(new Font("Verdana", Font.BOLD, 16)); // define a fonte em uso

    int heightCount = 30;
    for (String message : messages) {
      g2d.drawString(message, 10, heightCount); // escreve uma string
      heightCount += 30;
    }

    insertComponents(this, heightCount);
  }

  private void insertComponents(JPanel painel, int height) {
    painel.setLayout(null);

    JLabel label = new JLabel("Opção");
    label.setBounds(10, height, 50, 25);
    painel.add(label);
    JTextField field = new JTextField(20);
    field.setBounds(70, height, 160, 25);
    painel.add(field);

    JButton button = new JButton("Escolher");
    button.setBounds(250, height, 100, 25);
    painel.add(button);

    ActionListener buttonReader = new ButtonReader(this.function, field);
    button.addActionListener(buttonReader);
  }

  public void start() {
    while (true) {
      repaint();
    }
  }
}
