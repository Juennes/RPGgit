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
        for (int i = 0; i<size; i++){
            for (int j=0; j<size; j++){
                System.out.println(tiles[i][j].getId()+"  "+tiles[i][j].getLayer());
                wereld[y+i][x+j][tiles[i][j].getLayer()] = tiles[i][j];
            }
        }

    }

    public void move(Character c){

        int x = c.X;
        int y = c.Y;
        int size = c.getSize();

        for (int i = 0; i<size; i++){
            for (int j=0; j<size; j++){
                wereld[y+c.getRichting()[1]+i][x+c.getRichting()[0]+j][2] = null;
            }
        }

        add(c, x, y);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile[] getLayers(int x, int y){
        if (x < width && y < height && y >= 0 && x >= 0){
            return wereld[y][x];
        }
        else return null;
    }
}
