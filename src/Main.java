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
        for (int i = verticalRow; i < 16; i += 4){
            if (i == index) continue;
            if (buttons.get(i).getText().equals("0")){
                zeroIndex = i;
                break;
            }
        }
        for (int i = horizontalRow; i < horizontalRow + 4; i++){
            if (i == index) continue;
            if (buttons.get(i).getText().equals("0")){
                zeroIndex = i;
                break;
            }
        }
        if (zeroIndex != -1){
            moveBricks(index, zeroIndex);
        }
    }

    void moveBricks(int firstBrick, int targetBrick){
        int howManyJumps = -1;
        boolean rightOrDown = true;

        // Horizontal
        if (firstBrick - (firstBrick % 4) > targetBrick || firstBrick - (firstBrick % 4) + 3 < targetBrick) {
            howManyJumps = (firstBrick - targetBrick) % 4;
            if (howManyJumps > 0) {
                rightOrDown = false;
                howManyJumps *= -1;
            }
            for (int i = 0; i < howManyJumps; i++) {
                if (rightOrDown) {

                }
            }
        } else { // Vertical
            howManyJumps = firstBrick - targetBrick;
            if (howManyJumps > 0){
                rightOrDown = false;
                howManyJumps *= -1;
            }

        }
        singleMove(firstBrick, targetBrick);
        checkVictory();
    }

    void singleMove(int firstBrick, int targetBrick){
        String firstBrickText = buttons.get(firstBrick).getText();
        String targetBrickText = buttons.get(targetBrick).getText();
        buttons.get(firstBrick).setText(targetBrickText);
        buttons.get(targetBrick).setText(firstBrickText);
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