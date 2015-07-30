package rpg.game;

/**
 * Created by Juennes on 30/07/2015.
 */
public class CharacterTile implements Tile {

    boolean isMain;

    public CharacterTile(boolean isMain){

        this.isMain = isMain;

    }

    @Override
    public String getId() {
        return isMain?"charMain":"charFill";
    }
}
