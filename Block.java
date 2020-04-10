package midterm.pentago;

/**
 * this class create a Block of the game
 *
 * @author alireza sahragard
 * @since 2020-4-4
 */
public class Block {

    private int[][] blockHomes = new int[3][3];

    /**
     * construct an empty block
     */
    public Block() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blockHomes[i][j] = 0;
            }
        }
    }

    /**
     * add a shape to Block
     * @param x int horizontal coordinate
     * @param y int vertical coordinate
     * @param userCode int code for user
     */
    public void addShape(int x, int y, int userCode) {
        if (blockHomes[y][x] == 0) {
            blockHomes[y][x] = userCode;
        }
        else {
            System.out.println("pass adding!!");
        }
    }

    /**
     * turn the block clockwise
     */
    public void turnClockwise() {
        int[][] array = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0, u = 2; j < 3; j++, u--) {
                array[i][j] = blockHomes[u][i];
            }
        }
        resetBlock(array);
    }

    /**
     * turn the block anti-clockwise
     */
    public void turnAntiClockwise() {
        int[][] array = new int[3][3];
        for (int j = 0, u = 2; j < 3; j++, u--) {
            for (int i = 0; i < 3; i++) {
                array[j][i] = blockHomes[i][u];
            }
        }
        resetBlock(array);
    }

    /**
     * reset block to a 3*3 array
     * @param array 3*3 array of int
     */
    private void resetBlock(int[][] array) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blockHomes[i][j] = array[i][j];
            }
        }
    }

    /**
     * gets a row of block
     * @param i int row
     * @return array of int with length 3
     */
    public int[] getRow(int i) {
        int[] a = new int[3];
        //System.out.println("block get");
        for (int j = 0; j < 3; j++) {
            a[j] = blockHomes[i][j];
        }
        return a;
    }

    /**
     * prints the block info
     */
    public void printBlock() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(i + " " + j + " " + blockHomes[i][j]);
            }
        }
    }
}
