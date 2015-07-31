package rpg.game;

/**
 * Created by Juennes on 30/07/2015.
 */
public class Path implements Tile {

    public String getId() {
        return "path";
    }

    @Override
    public boolean canPass() {
        return true;
    }

    public int getLayer(){return 0;}
}
