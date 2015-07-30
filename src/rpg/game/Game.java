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

    private final int cellSize = 45;

    private World world;

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
        world = new World(60, 60);

        for (int i = 0; i < world.getHeight(); i++) {
            for (int j = 0; j < world.getWidth(); j++) {
                world.add(new Ground(), i, j);
            }
        }

        character = new Character("Jon Sneu", false, 3, new Richting().DOWN);

        // world[15][15][1] = character;

        world.add(new Character("henk", false, 0, new Richting().UP), 32, 7);

        world.add(new Character("henk", false, 0, new Richting().UP), 45, 8);

        world.add(new Character("henk", false, 0, new Richting().UP), 12, 56);

        world.add( new Path(), 26, 35);
        world.add(new Path(), 25, 35);

        entities = new Entity[1];

        entities[0] = character;

        // fix hier nog iets mooier
        setMinimumSize(new Dimension(cellSize * 17, cellSize * 9));
        setMaximumSize(new Dimension(cellSize * 17, cellSize * 9));
        setPreferredSize(new Dimension(cellSize * 17, cellSize * 9));
        setBackground(Color.black);

        isMoving = false;

        charPosScreenX = cellSize * 8;
        charPosScreenY = cellSize * 4;

        x = 15 - 8;
        y = 15 - 4;

        image = new BufferedImage(cellSize * 17, cellSize * 9, BufferedImage.TYPE_INT_RGB);

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
        int clipX = -cellSize;

        int clipY = -cellSize;
        int dy = 0;
        int dx = 0;
        Graphics2D gf = image.createGraphics();
        gf.setColor(Color.black);
        gf.fillRect(0,0,this.getWidth(), this.getHeight());
        for (int i = x-1; i < x + 17 + 1; i++) {
            for (int j = y-1; j < y + 9 + 1; j++) {
                for (int k = 0; k < 3; k++) {

                    if (i >= 0 && i<world.getHeight() && j >= 0 && j < world.getWidth()){

                        Tile object = world.getLayers(j, i)[k];

                        dx = 0;
                        dy = 0;

                        if (object != null) {
                            //System.out.println(world[i][j][k].getId());
                            Image text = res.getTexture(object.getId());

                            if (object instanceof Entity) {
                                dy = ((Entity) object).offSet()[1];
                                dx = ((Entity) object).offSet()[0];
                            }

                            // gf.drawImage(res.getTexture("character"), -30, -30, null);

                            gf.drawImage(text, clipY + dy + xMoves, clipX + dx + yMoves, null);
                        }

                    }
                }
                clipX += cellSize;
            }
            clipX = -cellSize;
            clipY += cellSize;
        }
        // karakter toevoegen
        gf.drawImage(res.getTexture("charMain"), 8*cellSize, 4*cellSize, null);
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

            if (Math.abs(xMoves) >= 45){
                isMoving = false;
                x -= Math.signum(xMoves);
                xMoves = 0;
            }
            if (Math.abs(yMoves) >= 45){
                isMoving = false;
                y -= Math.signum(yMoves);
                yMoves = 0;
            }
        }

    }
}