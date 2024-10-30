import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends JFrame {

    JPanel  mainPanel          = new JPanel(new BorderLayout());
    JPanel  buttonPanel        = new JPanel(new GridLayout(1,1));
    JPanel  gamePanel          = new JPanel(new GridLayout(4,4));

    JButton newGameButton      = new JButton("New Game");

    List<JButton>       buttons         = new ArrayList<>();
    ArrayList<String>   buttonStrings   = new ArrayList();

    Font buttonFont = new Font("Arial", Font.BOLD, 42);
    Color buttonBackground = Color.WHITE;
    Color textColor = Color.BLACK;
    Color buttonBorder = Color.GRAY;

    boolean firstRender = true;
    boolean hasWon = false;
    Main() {
        newGame();
        buttonPanel.add(newGameButton);
        newGameButton.addActionListener(l -> {
            System.out.println("Clicked");
            newGame();
        });

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        pack();
        setVisible(true);
        setSize(400,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    void showWinningMessage() {
        JOptionPane.showMessageDialog(this, "You have won the game!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
    }

    boolean hasPlayerWon() {
        for (int i = 0; i < 15; i++) {
            if (!buttons.get(i).getText().equals(String.valueOf(i + 1))) {
                return false;
            }
        }
        return true;
    }

    JButton styleButton(String buttonString){
        JButton button = new JButton(buttonString);
        button.setFont(buttonFont);
        button.setBackground(buttonBackground);
        button.setForeground(textColor);
        button.setBorder(BorderFactory.createLineBorder(buttonBorder,1));
        return button;
    }

    void render() {
        int i = 0;
        for (String buttonString : buttonStrings) {
            System.out.println(buttonString);
            if (firstRender) {
                JButton button = styleButton(buttonString);
                button.addActionListener(l -> {
                    System.out.println("Clicked");
                    checkValidMove(buttons.indexOf(button));
                });
                if (buttonString.equals("0")){
                    button.setOpaque(true);
                    button.setContentAreaFilled(false);
                    button.setBorderPainted(false);
                }
                gamePanel.add(button);
                buttons.add(button);
            } else {
                buttons.get(i).setText(buttonString);
            }
            i++;
        }
        firstRender = false;
    }

    void checkValidMove(int index){
        int verticalRow = index % 4;
        int horizontalRow = index - (index % 4);
        int zeroIndex = -1;
        boolean vertical = false;
        for (int i = verticalRow; i < 16; i += 4){
            if (i == index) continue;
            if (buttons.get(i).getText().equals("0")){
                zeroIndex = i;
                vertical = true;
                break;
            }
        }
        for (int i = horizontalRow; i < horizontalRow + 4; i++){
            if (i == index) continue;
            if (buttons.get(i).getText().equals("0")){
                zeroIndex = i;
                vertical = false;
                break;
            }
        }
        if (zeroIndex != -1){
            moveBricks(index, zeroIndex, vertical);
        }
    }

    void moveBricks(int chosenBrick, int targetBrick, boolean vertical){
        int howManyJumps = -1;
        if (vertical){
            howManyJumps = (targetBrick - chosenBrick) / 4;
            if (howManyJumps > 0){
                for (int i = 0; i < howManyJumps; i++){
                    singleMove(targetBrick - ((i + 1) * 4),targetBrick - (i * 4));
                }
            } else {
                howManyJumps *= -1;
                for (int i = 0; i < howManyJumps; i++){
                    singleMove(targetBrick + ((i + 1) * 4),targetBrick + (i * 4));
                }
            }
        } else {
            howManyJumps = targetBrick - chosenBrick;
            if (howManyJumps > 0){
                for (int i = 0; i < howManyJumps; i++){
                    singleMove(targetBrick - i - 1 , targetBrick - i);
                }
            } else {
                howManyJumps *= -1;
                for (int i = 0; i < howManyJumps; i++){
                    singleMove(targetBrick + i + 1, targetBrick + i);
                }
            }
        }
        hasWon = hasPlayerWon();
        if (hasWon) {
            showWinningMessage();
        }
    }

    void singleMove(int firstBrick, int targetBrick){
        String firstBrickText = buttons.get(firstBrick).getText();
        String targetBrickText = buttons.get(targetBrick).getText();
        buttons.get(firstBrick).setText(targetBrickText);
        buttons.get(targetBrick).setText(firstBrickText);
    }

    void newGame() {
        hasWon = false;
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