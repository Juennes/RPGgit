package rpg.game;

/**
 * Created by Juennes on 30/07/2015.
 */
public class World {

    private Tile[][][] wereld;
    private int height;
    private int width;

    public World (int width, int heigth){


        wereld = new Tile[heigth][width][3];
        this.height = heigth;
        this.width = width;
    }

    public void add(Tile tile, int x, int y){

        wereld[y][x][0] = tile;

    }

    public void add(Entity entity, int x, int y){

        int size = entity.getSize();

        Tile[][] tiles = entity.getRepr();
        // check if possible
        for (int i = 0; i<size; i++){
            for (int j=0; j<size; j++){
                wereld[y+i][x+j][1] = tiles[i][j];
            }
        }

    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile[] getLayers(int x, int y){
        return wereld[y][x];
    }
}
