import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends JFrame {

    JPanel  mainPanel          = new JPanel(new BorderLayout());
    JPanel  buttonPanel        = new JPanel(new GridLayout(1,1));
    JPanel  gamePanel          = new JPanel(new GridLayout(4,4));

    JButton newGameButton     = new JButton("New Game");
    List<JButton> buttons = new ArrayList<>();
    ArrayList<String> buttonStrings = new ArrayList();
    boolean firstRender = true;


    Main() {
        newGame();
        buttonPanel.add(newGameButton);
        newGameButton.addActionListener(l -> {
            System.out.println("Clicked");
            newGame();
        });

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(mainPanel);
        pack();
        setVisible(true);
        setSize(400,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void render() {
        int i = 0;
        for (String buttonString : buttonStrings) {
            System.out.println(buttonString);
            if (firstRender) {
                JButton button = new JButton(buttonString);
                gamePanel.add(button);
                buttons.add(button);
            } else {
                buttons.get(i).setText(buttonString);
            }
            i++;
        }
        firstRender = false;
    }

    void newGame() {
        buttonStrings.clear();
        for (int i = 0; i <= 15; i++) {
            buttonStrings.add(String.valueOf(i));
        }
        Collections.shuffle(buttonStrings);
        render();
    }

    public static void main(String[] args) {
        new Main();
    }
}