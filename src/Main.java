import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    JPanel  mainPanel          = new JPanel(new BorderLayout());
    JPanel  buttonPanel        = new JPanel(new GridLayout(1,1));
    JPanel  gamePanel          = new JPanel(new GridLayout(4,4));

    JButton newGameButton     = new JButton("New Game");

    JLabel empty = new JLabel("");
    JLabel one = new JLabel("1");
    JLabel two = new JLabel("2");
    JLabel three = new JLabel("3");
    JLabel four = new JLabel("4");
    JLabel five = new JLabel("5");
    JLabel six = new JLabel("6");
    JLabel seven = new JLabel("7");
    JLabel eight = new JLabel("8");
    JLabel nine = new JLabel("9");
    JLabel ten = new JLabel("10");
    JLabel eleven = new JLabel("11");
    JLabel twelve = new JLabel("12");
    JLabel thirteen = new JLabel("13");
    JLabel fourteen = new JLabel("14");
    JLabel fifteen = new JLabel("15");


    Main(){
        gamePanel.add(one);
        gamePanel.add(two);
        gamePanel.add(three);
        gamePanel.add(four);
        gamePanel.add(five);
        gamePanel.add(six);
        gamePanel.add(seven);
        gamePanel.add(eight);
        gamePanel.add(nine);
        gamePanel.add(ten);
        gamePanel.add(eleven);
        gamePanel.add(twelve);
        gamePanel.add(thirteen);
        gamePanel.add(fourteen);
        gamePanel.add(fifteen);
        gamePanel.add(empty);

        buttonPanel.add(newGameButton);

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        pack();
        setVisible(true);
        setSize(400,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main();
    }
}