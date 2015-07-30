package rpg.game;

/**
 * Created by Juennes on 29/07/2015.
 */
public class Entity {

    private int offSetX;

    private int offSetY;

    protected boolean moving;

    private int size;

    protected Tile[][] repr;

    private int SPEED;

    protected int[] richting;

    // private String name;

    public Entity(String id, int size, boolean moving, int SPEED, int[] richting) {

        this.size = size;
        offSetX = 0;
        offSetY = 0;
        // this.name = name;
        this.moving = moving;
        this.SPEED = SPEED;
        this.richting = richting;
        // meerdere tegels vullen
        repr = new Tile[4][4];
        // main tegel
        repr[size-1][size-1] = new EntityTile(id, true);
        for (int i = 0; i<4; i++){
            for (int j = 0; j<4; j++){
                if (i != size-1||j!=size-1){
                    repr[i][j] = new EntityTile(id, false);
                }
            }
        }

    }

    public void setMoving(){
        moving = true;
        offSetY = richting[1]*90;
        offSetX = richting[0]*90;
    }

    public Tile[][] getRepr() {
        return repr;
    }

    public int getSize() {
        return size;
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

    /*
    @Override
    public String getId() {
        return "entity";
    }
    */

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
