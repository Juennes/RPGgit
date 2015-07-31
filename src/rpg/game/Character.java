package rpg.game;

/**
 * Created by Juennes on 27/07/2015.
 */
public class Character extends Entity {

    private String name;

    public Character (String name, int X, int Y){
        super("char", 2, X, Y);

        this.name = name;
    }

    /*
    @Override
    public String getId() {
        return "character";
    }
    */

    public void moveTo(){

        X -= richting[0];
        Y -= richting[1];
    }
}
