package rpg.run;

import rpg.game.Game;
import rpg.game.InputHandler;

import javax.swing.*;
import java.awt.*;

public class GameMain {

    public static final int WIDTH = 45*17;
    public static final int HEIGHT = 45*9;
    public  static final int SCALE = 3;
    public static final String NAME = "Game";

    public InputHandler input;

    private JFrame frame;

    public synchronized void start(){



        input = new InputHandler();

        Game game = new Game(input);

        frame = new JFrame(NAME);

        frame.addKeyListener(input);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(game, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();

    }

    public static void main(String[] args) {
	// write your code here
        new GameMain().start();
    }
}
