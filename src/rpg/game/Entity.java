package rpg.game;

/**
 * Created by Juennes on 29/07/2015.
 */
public class Entity implements Tile {

    private int offSetX;

    private int offSetY;

    protected boolean moving;



    private int SPEED;

    protected int[] richting;

    // private String name;

    public Entity(boolean moving, int SPEED, int[] richting) {
        offSetX = 0;
        offSetY = 0;
        // this.name = name;
        this.moving = moving;
        this.SPEED = SPEED;
        this.richting = richting;
    }

    public void setMoving(){
        moving = true;
        offSetY = richting[1]*90;
        offSetX = richting[0]*90;
    }

    public void setRichting(int[] richting) {
        this.richting = richting;
    }

    public int[] getRichting() {
        return richting;
    }

    public void update(){

        if (moving){
            move (SPEED, richting);
        }
    }

    @Override
    public String getId() {
        return "entity";
    }

    private void move(int pixels, int[] richting){

        offSetX -= richting[0]*pixels;

        offSetY -= richting[1]*pixels;

        if (offSetX < 0|| offSetY < 0){

            moving = false;
            offSetX = 0;
            offSetY = 0;
        }

    }

    public int[] offSet(){

        int [] off = {offSetX, offSetY};
        return off;
    }
}
