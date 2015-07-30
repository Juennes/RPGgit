package rpg.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Juennes on 29/07/2015.
 */
public class InputHandler implements KeyListener{

    public boolean up;

    public boolean down;

    public   boolean right;

    public  boolean left;

    public InputHandler(){
        this.up = false;
        this.down = false;
        this.right = false;
        this.left = false;

        //game.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("eeeeeee");
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:    up=true;
                break;
            case KeyEvent.VK_DOWN:  down=true;
                break;
            case KeyEvent.VK_RIGHT:    right=true;
                break;
            case KeyEvent.VK_LEFT:    left=true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("oooooooo");
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:    up=false;
                break;
            case KeyEvent.VK_DOWN:  down=false;
                break;
            case KeyEvent.VK_RIGHT:    right=false;
                break;
            case KeyEvent.VK_LEFT:    left=false;
                break;
        }

    }
}
