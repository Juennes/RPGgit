package rpg.game;

/**
 * Created by Juennes on 30/07/2015.
 */
public class EntityTile implements Tile {

    boolean isMain;

    boolean passable;

    int layer;

    String id;

    public EntityTile(String id, boolean isMain, boolean passable, int layer){

        this.isMain = isMain;
        this.id = id;
        this.passable = passable;
        this.layer = layer;
    }

    @Override
    public String getId() {
        return isMain?id+"Main":id+"Fill";
    }

    @Override
    public boolean canPass() {
        return passable;
    }

    public int getLayer() {
        return layer;
    }
}
