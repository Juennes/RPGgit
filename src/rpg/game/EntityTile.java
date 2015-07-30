package rpg.game;

/**
 * Created by Juennes on 30/07/2015.
 */
public class EntityTile implements Tile {

    boolean isMain;

    String id;

    public EntityTile(String id, boolean isMain){

        this.isMain = isMain;
        this.id = id;

    }

    @Override
    public String getId() {
        return isMain?id+"main":id+"fill";
    }

}
