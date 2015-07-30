package rpg.game;

import javafx.scene.input.KeyCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Juennes on 27/07/2015.
 */
public class Game extends Canvas {

    private Tile[][][] world;

    private Entity[] entities;

    private boolean running;

    private boolean isMoving;

    private int charPosScreenX;

    private int charPosScreenY;

    private Character character;

    private int xMoves;

    private int yMoves;

    private int x;

    private int y;

    private BufferedImage image;

    private Resources res;

    private int SPEED = 2;

    private int[] movingDir = {0,0};

    public InputHandler input;


    public Game(InputHandler input) {
        // voorlopig nog arbitreir
        world = new Tile[30][30][3];

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                world[i][j][0] = new Ground();
            }
        }

        character = new Character("Jon Sneu", false, 3, new Richting().DOWN);

        // world[15][15][1] = character;

        world[4][17][1] = new Character("henk", false, 0, new Richting().UP);

        world[9][2][1] = new Character("henk", false, 0, new Richting().UP);

        world[25][17][1] = new Character("henk", false, 0, new Richting().UP);

        world[12][12][0] = new Path();
        world[13][12][0] = new Path();

        entities = new Entity[1];

        entities[0] = character;

        // fix hier nog iets mooier
        setMinimumSize(new Dimension(90 * 17, 90 * 9));
        setMaximumSize(new Dimension(90 * 17, 90 * 9));
        setPreferredSize(new Dimension(90 * 17, 90 * 9));
        setBackground(Color.black);

        isMoving = false;

        charPosScreenX = 90 * 8;
        charPosScreenY = 90 * 4;

        x = 15 - 8;
        y = 15 - 4;

        image = new BufferedImage(90 * 17, 90 * 9, BufferedImage.TYPE_INT_RGB);

        res = new Resources();

        this.input = input;

        this.xMoves = 0;
        this.yMoves = 0;
        //this.movingDir = new int[2];

    }

    public void start() {
        running = true;
        run();
    }

    public void stop() {
        running = false;
    }

    private void tick() {

        verwerkInput();

    }

    private void render() {

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            return;
        }
        int clipX = -90;

        int clipY = -90;
        int dy = 0;
        int dx = 0;
        Graphics2D gf = image.createGraphics();
        gf.setColor(Color.black);
        gf.fillRect(0,0,this.getWidth(), this.getHeight());
        for (int i = x-1; i < x + 17 + 1; i++) {
            for (int j = y-1; j < y + 9 + 1; j++) {
                for (int k = 0; k < 3; k++) {

                    if (i >= 0 && i<world.length && j >= 0 && j < world[0].length){

                        Tile object = world[i][j][k];

                        dx = 0;
                        dy = 0;

                        if (object != null) {
                            //System.out.println(world[i][j][k].getId());
                            Image text = res.getTexture(world[i][j][k].getId());

                            if (object instanceof Entity) {
                                dy = ((Entity) object).offSet()[1];
                                dx = ((Entity) object).offSet()[0];
                            }

                            // gf.drawImage(res.getTexture("character"), -30, -30, null);

                            gf.drawImage(text, clipY + dy + xMoves, clipX + dx + yMoves, null);
                        }

                    }
                }
                clipX += 90;
            }
            clipX = -90;
            clipY += 90;
        }

        gf.drawImage(res.getTexture(character.getId()), 8*90, 4*90, null);
        gf.dispose();
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        bs.show();

    }

    public void run() {

        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        // run loop
        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            boolean shouldRender = false;
            while (delta >= 1) {
                ticks++;
                tick();
                delta--;
                shouldRender = true;
            }
            if (shouldRender) {
                frames++;
                render();
            }


            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println("ticks: " + ticks + " frames: " + frames);
                frames = 0;
                ticks = 0;
            }
        }

    }

    private void verwerkInput(){

        if (!isMoving){

            if (input.right){
                isMoving = true;
                System.out.println("right");
                movingDir = new Richting().LEFT;
            }
            else if (input.left){
                isMoving = true;
                System.out.println("left");
                movingDir = new Richting().RIGHT;
            }
            else if (input.up){
                isMoving = true;
                System.out.println("up");
                movingDir = new Richting().DOWN;
            }
            else if (input.down){
                isMoving = true;
                System.out.println("down");
                movingDir = new Richting().UP;
            }
        }
        else {
            xMoves += movingDir[0]*SPEED;
            yMoves += movingDir[1]*SPEED;

            if (Math.abs(xMoves) >= 90){
                isMoving = false;
                x -= Math.signum(xMoves);
                xMoves = 0;
            }
            if (Math.abs(yMoves) >= 90){
                isMoving = false;
                y -= Math.signum(yMoves);
                yMoves = 0;
            }
        }

    }
}