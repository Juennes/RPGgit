package rpg.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Juennes on 27/07/2015.
 */
public class Resources {

    public Image ground;

    public Image character;

    public Image path1;

    public Image path2;

    private HashMap<String, Image> textures;

    public Resources(){

        // character = new BufferedImage(90, 180, BufferedImage.TYPE_INT_RGB);

        textures = new HashMap<String, Image>();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/rpg/img/grassTest.png"));
            ground = img;
            img = ImageIO.read(new File("src/rpg/img/charTest.png"));
            character = img;
            img = ImageIO.read(new File("src/rpg/img/pathTest.png"));
             path1 = img;
            img = ImageIO.read(new File("src/rpg/img/pathTest2.png"));
             path2 = img;
        } catch (IOException e) {
            System.out.println("dfghjkl");
        }

        textures.put("ground", ground);
        textures.put("character", character);
        textures.put("path", path1);
        textures.put("path2", path2);

        Image dink = textures.get("ground");
    }

    public Image getTexture(String id){

        return textures.get(id);

    }
}
