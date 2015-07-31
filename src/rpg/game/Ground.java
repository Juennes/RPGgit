package rpg.game;

/**
 * Created by Juennes on 27/07/2015.
 */
public class Ground implements Tile {

    @Override
    public boolean canPass() {
        return true;
    }

    public String getId(){
        return "ground";
    }

    public int getLayer(){return 0;}
}
