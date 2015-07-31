package rpg.game;

/**
 * Created by Juennes on 29/07/2015.
 */
public class Entity {
    
    public int X;
    
    public int Y;

    private int offSetX;

    private int offSetY;

    protected boolean moving;

    private int size;

    protected Tile[][] repr;

    private int SPEED;

    protected int[] richting;

    // private String name;

    public Entity(String id, int size, int X, int Y) {

        this.size = size;
        offSetX = 0;
        offSetY = 0;
        this.X = X;
        this.Y = Y;
        // this.name = name;
        this.moving = moving;
        this.SPEED = SPEED;
        this.richting = richting;
        // meerdere tegels vullen
        repr = new Tile[size][size];
        // main tegel

        for (int i = 1; i<size; i++){
            for (int j = 0; j<size; j++){
                if (i != 0||j!=0){
                    repr[i][j] = new EntityTile(id, false, true,1);
                }
            }
        }
        repr[0][0] = new EntityTile(id, true, false, 2);
        repr[0][1] = new EntityTile(id,false, false, 2);
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

    public boolean movePossible(World world){

        int verderZien = 0;

        // kijken hoever er gecheckt moet worden
        if (richting.equals(new Richting().RIGHT)){
            verderZien = size-1;
        }

        if (X-richting[0] < 0 || X-richting[0] - verderZien >= world.getWidth() || Y-richting[1] < 0 || Y-richting[1] >= world.getHeight()){
            return false;
        }

        if (world.getLayers(X-richting[0]-verderZien, Y-richting[1])[2] != null){
            System.out.println(world.getLayers(X-richting[0]-verderZien, Y-richting[1])[2].getId());
            return world.getLayers(X-richting[0]-verderZien, Y-richting[1])[2].canPass();
        }
        else return true;
    }
}
