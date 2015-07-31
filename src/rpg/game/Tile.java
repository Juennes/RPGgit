package rpg.game;

import java.awt.*;

/**
 * Created by Juennes on 27/07/2015.
 */
public interface Tile {

    public boolean canPass();

    public String getId();

    public int getLayer();
}
