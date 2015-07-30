package rpg.game;

/**
 * Created by Juennes on 27/07/2015.
 */
public class Character extends Entity {

    private String name;

    public int X;

    public int Y;

    public Character (String name, boolean moving, int SPEED, int[] richting){
        super("char", 4, moving, SPEED, richting);

        this.name = name;
    }

    /*
    @Override
    public String getId() {
        return "character";
    }
    */

    public void moveTo(){

        X += richting[0];
        Y += richting[1];

    }
}
