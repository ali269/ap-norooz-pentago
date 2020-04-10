package midterm.pentago;

/**
 * this class create a 6*6 game map
 *
 * @author alireza sahragard
 * @since 2020-4-5
 */
public class Map {

    public static final String ANSI_RESET  = "\u001B[0m";
    public static final String ANSI_BLACK  = "\u001B[30m";
    public static final String ANSI_RED    = "\u001B[31m";
    public static final String ANSI_GREEN  = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE   = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN   = "\u001B[36m";
    public static final String ANSI_WHITE  = "\u001B[37m";
    public static final String ANSI_BRIGHT_BLACK  = "\u001B[90m";
    public static final String ANSI_BRIGHT_RED    = "\u001B[91m";
    public static final String ANSI_BRIGHT_GREEN  = "\u001B[92m";
    public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
    public static final String ANSI_BRIGHT_BLUE   = "\u001B[94m";
    public static final String ANSI_BRIGHT_PURPLE = "\u001B[95m";
    public static final String ANSI_BRIGHT_CYAN   = "\u001B[96m";
    public static final String ANSI_BRIGHT_WHITE  = "\u001B[97m";


    public static final String ANSI_BG_BLACK  = "\u001B[40m";
    public static final String ANSI_BG_RED    = "\u001B[41m";
    public static final String ANSI_BG_GREEN  = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
    public static final String ANSI_BG_BLUE   = "\u001B[44m";
    public static final String ANSI_BG_PURPLE = "\u001B[45m";
    public static final String ANSI_BG_CYAN   = "\u001B[46m";
    public static final String ANSI_BG_WHITE  = "\u001B[47m";
    public static final String ANSI_BRIGHT_BG_BLACK  = "\u001B[100m";
    public static final String ANSI_BRIGHT_BG_RED    = "\u001B[101m";
    public static final String ANSI_BRIGHT_BG_GREEN  = "\u001B[102m";
    public static final String ANSI_BRIGHT_BG_YELLOW = "\u001B[103m";
    public static final String ANSI_BRIGHT_BG_BLUE   = "\u001B[104m";
    public static final String ANSI_BRIGHT_BG_PURPLE = "\u001B[105m";
    public static final String ANSI_BRIGHT_BG_CYAN   = "\u001B[106m";
    public static final String ANSI_BRIGHT_BG_WHITE  = "\u001B[107m";




    private int[][] map = new int[6][6];
    private Block[][] blocks = new Block[2][2];
    /**
     * construct a new empty map with empty blocks
     */
    public Map() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                map[i][j] = 0;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                blocks[i][j] = new Block();
            }
        }
    }

    /**
     * copies the blocks to map
     */
    private void updateMap() {
        //System.out.println("map update");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                for (int v = 0; v < 2; v++) {
                    int[] a = blocks[j][v].getRow(i);
                    for (int u = 0; u < 3; u++) {
                        map[j * 3 + i][v * 3 + u] = a[u];
                    }
                }
            }
        }
    }

    /**
     * add a shape to map to a given coordinate if empty
     * @param horizontal int horizontal coordinate
     * @param vertical int vertical coordinate
     * @param userCode int user code
     */
    public void addShapeToMap(int horizontal, int vertical, int userCode) {
        int blockHor = 0, blockVer = 0;
        boolean flag = true;
        if (horizontal >= 0 && horizontal < 3) {
            blockHor = 0;
        }
        else if (horizontal < 6 && horizontal > 2) {
            blockHor = 1;
            horizontal = horizontal - 3;
        }
        else {
            flag = false;
            System.out.println("pass!");
        }
        if (vertical >= 0 && vertical < 3) {
            blockVer = 0;
        }
        else if (vertical < 6 && vertical > 2) {
            blockVer = 1;
            vertical = vertical - 3;
        }
        else {
            flag = false;
            System.out.println("pass!!");
        }
        //System.out.println("map add");
        if (flag)
            blocks[blockVer][blockHor].addShape(horizontal, vertical, userCode);
        updateMap();
    }

    /**
     * turns a block 90 degrees in clockwise or anticlockwise
     * @param x int block horizontal
     * @param y int block vertical
     * @param turnCode int turn code 1 for clockwise and 2 for anticlockwise
     */
    public void blockTurn(int x, int y, int turnCode) {
        if (turnCode == 1) {
            blocks[y][x].turnClockwise();
        }
        else if (turnCode == 2){
            blocks[y][x].turnAntiClockwise();
        }
        else {
            System.out.println("pass");
        }
        updateMap();
    }

    /**
     * copies the map to another map to be used in game
     * @param arr 6*6 array of int
     */
    public void copyArray(int[][] arr) {
        for (int i = 0; i < 6; i++) {
            System.arraycopy(map[i], 0, arr[i], 0, 6);
        }
    }


    /**
     * print the map of game
     */
    public void printMap() {
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 2; j++) {
//                blocks[i][j].printBlock();
//            }
//        }
//
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6; j++) {
//                System.out.println(i + " " + j + " " + map[i][j]);
//            }
//        }
        System.out.println();
        System.out.println("          1         2    ");
        //System.out.println("  _______________________");
        System.out.println("  |  | 1  2  3 || 4  5  6 ");
        //System.out.println("  _______________________");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (j == 0) {
                    if(i == 1) {
                        System.out.print(" 1| " + (i + 1) + "|");
                    }
                    else if (i == 4) {
                        System.out.print(" 2| " + (i + 1) + "|");
                    }
                    else {
                        System.out.print("  | " + (i + 1) + "|");
                    }
                }
                else if (j == 3) {
                    if (map[i][j - 1] == 1) {
                        System.out.print(ANSI_BG_RED + ANSI_BLUE + "|_|" + ANSI_RESET);
                    }
                    else if (map[i][j - 1] == 2) {
                        System.out.print(ANSI_BG_BLACK + ANSI_BLUE + "|_|" + ANSI_RESET);
                    }
                    else if (map[i][j - 1] == 0) {
                        System.out.print(ANSI_BRIGHT_BG_WHITE + ANSI_BLUE + "|_|" + ANSI_RESET);
                    }
                    System.out.print(ANSI_BG_GREEN + ANSI_BLUE + "||" + ANSI_RESET);
                }
                else {
                    if (map[i][j - 1] == 1) {
                        System.out.print(ANSI_BG_RED + ANSI_BLUE + "|_|" + ANSI_RESET);
                    }
                    else if (map[i][j - 1] == 2) {
                        System.out.print(ANSI_BG_BLACK + ANSI_BLUE + "|_|" + ANSI_RESET);
                    }
                    else if (map[i][j - 1] == 0) {
                        System.out.print(ANSI_BRIGHT_BG_WHITE + ANSI_BLUE + "|_|" + ANSI_RESET);
                    }
                }
            }
            if (i == 2) {
                System.out.println();
                System.out.print("      " + ANSI_BG_GREEN + ANSI_BLUE + "--------------------" + ANSI_RESET);
            }
            System.out.println();
        }
    }


}
