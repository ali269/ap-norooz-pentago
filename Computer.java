package midterm.pentago;

/**
 * this is a class control game and play in two ways
 */
public class Computer extends Game {


    private final int[] hotSpots = {0, 2, 3, 5};


    private int move(int foundUser, int x, int y, int xProgress, int yProgress) {
        int user = map[y][x];
        int userNum = 0;
        for (; x < 6 && x >= 0 && y < 6 && y >= 0;) {
            if (map[y][x] == user) {
                userNum++;
            }
            else if (map[y][x] != 0) {
                break;
            }
            x += xProgress;
            y += yProgress;
        }
        if (userNum > 2) {
            if(foundUser != 1 && user != 0) {
                foundUser = user;
            }
        }
        return foundUser;
    }

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
                        if (map[i][u + j] == user) {
                            userNum++;
                        }
                    }
                }
                else {
                    user = map[u][i];
                    for (int j = 0; j < 5; j++) {
                        if (map[j + u][i] == user) {
                            userNum++;
                        }
                    }
                }
                if (userNum > 3) {
                    if (userToReturn != 1 && user != 0) {
                        userToReturn = user;
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
                    if (map[c[0] + i][c[1] + i] == user) {
                        userNum++;
                    }
                }
                else {
                    if (map[c[0] - i][c[1] + i] == user) {
                        userNum++;
                    }
                }
            }
            if (userNum > 3) {
                if (userToReturn != 1 && user != 0) {
                    userToReturn = user;
                }
            }
        }
        return userToReturn;
    }


    private int checkGame2(int i, int j) {
        int a = 0, b = 0, c = 0, d = 5;
        if (j > i) {
            a = 0;
            b = 1;
        }
        else if (i > j){
            a = 1;
            b = 0;
        }
        if (j + i == 4) {
            c = 0;
            d = 4;
        }
        else if(j + i == 6) {
            c = 0;
            d = 5;
        }


        int userToReturn = 0;
        userToReturn = move(userToReturn, 0, j, 1, 0);
        userToReturn = move(userToReturn, i, 0, 0, 1);
        userToReturn = move(userToReturn, a, b, 1, 1);
        userToReturn = move(userToReturn, c, d, 1, -1);
        return userToReturn;
    }


    private int checkGame1() {
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
     * checks if a user win the game or not if yes return user's code
     * @return int user's code
     */
    public int checkGame() {
        int[][] coordinate1 = {{0, 1}, {0, 0}, {1, 0}, {1, 1}};
        int[][] coordinate2 = {{4, 0}, {5, 0}, {5, 1}, {4, 1}};
        int userToReturn = 0;
        userToReturn = super.moveStraight(userToReturn, 0);
        userToReturn = super.moveStraight(userToReturn, 1);
        userToReturn = super.moveKaj(coordinate1, userToReturn, 0);
        userToReturn = super.moveKaj(coordinate2, userToReturn, 1);
        return userToReturn;
    }


    /**
     * neutral a warning zone if there is one
     * @return if warning neutralization done return true false otherwise
     */
    private boolean warningHomes() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                for (int u = 2; u > 0; u--) {
                    if (map[i][j] == 0) {
                        map[i][j] = u;
                        if (checkGame() == 2) {
                            map[i][j] = 0;
                            System.out.println(j + "a " + i);
                            addShape(j, i, 2);
                            return true;
                        }
                        else if (checkGame() == 1) {
                            map[i][j] = 0;
                            System.out.println(j + " b" + i);
                            addShape(j, i, 2);
                            return true;
                        }
                        else{
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * allocate a important point to computer
     * @return true if allocated false otherwise
     */
    private boolean addAHotSpot() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] == 0) {
                    for (int u = 2; u > 0; u--) {
                        map[i][j] = u;
                        if (checkGame2(j, i) == 2) {
                            map[i][j] = 0;
                            addShape(j, i, 2);
                            System.out.println(j + "c " + i);
                            return true;
                        }
                        else if(checkGame2(j, i) == 1) {
                            map[i][j] = 0;
                            addShape(j, i, 2);
                            System.out.println(j + "d " + i);
                            return true;
                        }
                        else {
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }
        for (int i: hotSpots) {
            for (int j: hotSpots) {
                if (map[i][j] == 0) {
                    System.out.println(j + "e " + i);
                    addShape(j, i, 2);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * add a point by chance
     */
    private void addAnyWay() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] == 0) {
                    System.out.println(j + "f " + i);
                    addShape(j, i, 2);
                    return;
                }
            }
        }
    }

    /**
     * turn a block for win
     * @return if turned return true false otherwise
     */
    private boolean turningForWin() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int v = 1; v < 3; v++) {
                    turnBlock(j, i, v);
                    if (checkGame() == 2) {
                        System.out.println(j + "g " + i);
                        return true;
                    }
                    else {
                        turnBlock(j, i, 3 - v);
                    }
                }
            }
        }
        return false;
    }

    private void simpleTurn() {
        int a = 1, b = 1, c = 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int u = 2; u > 0; u--){
                    turnBlock(j, i, u);
                    if (checkGame1() == 1) {
                        turnBlock(j, i, 3 - u);
                    }
                    else if (checkGame1() == 2) {
                        System.out.println(j + " " + i + " " + u);
                        return;
                    }
                    else{
                        turnBlock(j, i, 3 - u);
                        a = j;
                        b = i;
                        c = u;
                    }
                }
            }
        }
        turnBlock(a, b, c);
        System.out.println(a + " " + b + " " + c);
    }

    /**
     * play adding point
     */
    public void playPoint() {
        boolean flag = false;
        flag = warningHomes();
        System.out.println(flag);
        if (!flag) {
            flag = addAHotSpot();
        }
        System.out.println(flag);
        if (!flag) {
            addAnyWay();
        }
    }


    /**
     * play turning part of game
     */
    public void playTurning() {
        boolean flag = false;
        flag = turningForWin();
        System.out.println(flag);
        if (!flag) {
            simpleTurn();
        }
    }

}
