package midterm.pentago;

/**
 * this class contains game rules and control game acts
 *
 * @author alireza sahragard
 * @since 2020-4-5
 */
public class Game {
    protected Map mapOfGame = new Map();
    protected int[][] map = new int[6][6];

    /**
     * move and check tho homes in horizontal and vertical
     * @param userToReturn int user code that has been discovered
     * @param navigate navigate to vertical(1) and horizontal(0)
     * @return int code of user to return
     */
    protected int moveStraight(int userToReturn, int navigate) {
        for (int i = 0; i < 6; i++) {
            for (int u = 0; u < 2; u++) {
                int user = 0;
                int userNum = 0;
                if (navigate == 0) {
                    user = map[i][u];
                    for (int j = 0; j < 5; j++) {
                        if (map[i][u + j] == user && user != 0) {
                            userNum++;
                        }
                        else {
                            break;
                        }
                    }
                }
                else {
                    user = map[u][i];
                    for (int j = 0; j < 5; j++) {
                        if (map[j + u][i] == user && user != 0) {
                            userNum++;
                        }
                        else {
                            break;
                        }
                    }
                }
                if (userNum > 4) {
                    if (userToReturn == 0) {
                        userToReturn = user;
                    }
                    else {
                        if (user != userToReturn) {
                            userToReturn = 3;
                        }
                    }
                }
            }
        }
        return userToReturn;
    }

    /**
     * move and check home in not straight way
     * @param coordinate coordinate of start
     * @param userToReturn int code of user has been discovered
     * @param navigate navigate to northeast(1) southeast(0)
     * @return code of user
     */
    protected int moveKaj(int[][] coordinate, int userToReturn, int navigate) {
        for (int[] c: coordinate) {
            int user = map[c[0]][c[1]];
            int userNum = 0;
            for (int i = 0; i < 5; i++) {
                if (navigate == 0) {
                    if (map[c[0] + i][c[1] + i] == user && user != 0) {
                        userNum++;
                    }
                    else {
                        break;
                    }
                }
                else {
                    if (map[c[0] - i][c[1] + i] == user && user != 0) {
                        userNum++;
                    }
                    else {
                        break;
                    }
                }
            }
            if (userNum > 4) {
                if (userToReturn == 0) {
                    userToReturn = user;
                }
                else {
                    if (user != userToReturn) {
                        userToReturn = 3;
                    }
                }
            }
        }
        return userToReturn;
    }



    /**
     * upload the map of Game
     */
    protected void uploadMap() {
        mapOfGame.copyArray(map);
    }

    /**
     * add a shape to map if there is empty
     * @param x int horizontal coordinate
     * @param y int vertical coordinate
     * @param userCode int user's code
     */
    public void addShape(int x, int y, int userCode) {
        //System.out.println("game add");
        mapOfGame.addShapeToMap(x, y, userCode);
        uploadMap();
    }

    /**
     * turn a block 90 degrees in clockwise or anticlockwise
     * @param x int horizontal coordinate
     * @param y int vertical coordinate
     * @param turnCode int turn code 1 for clockwise and 2 for anticlockwise
     */
    public void turnBlock(int x, int y, int turnCode) {
        //System.out.println("game add");
        if ((x < 2 && x >= 0) && (y >= 0 && y < 2) && (turnCode == 1 || turnCode == 2)) {
            mapOfGame.blockTurn(x, y, turnCode);
        }
        uploadMap();
    }

    /**
     * gets the map of game
     * @return 6*6 array of int
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * gets the num of shapes in the map
     * @return a int that is number of shapes in the map
     */
    public int getGameSize() {
        int counter = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] != 0) {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * checks if a user win the game or not if yes return user's code
     * @return int user's code
     */
    public int checkGame() {
        int[][] coordinate1 = {{0, 1}, {0, 0}, {1, 0}, {1, 1}};
        int[][] coordinate2 = {{4, 0}, {5, 0}, {5, 1}, {4, 1}};
        int userToReturn = 0;
        userToReturn = moveStraight(userToReturn, 0);
        userToReturn = moveStraight(userToReturn, 1);
        userToReturn = moveKaj(coordinate1, userToReturn, 0);
        userToReturn = moveKaj(coordinate2, userToReturn, 1);
        return userToReturn;
    }

    /**
     * prints the game map
     */
    public void PrintMap() {
        mapOfGame.printMap();
    }

    /**
     * copies map of game to another map
     * @param des int[][] 6*6 the map has the game status
     */
    public void copyMap(int[][] des) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                des[i][j] = map[i][j];
            }
        }

    }
}
