package midterm.pentago;

import java.util.Scanner;

public class Run {

    public static void addPoint(Game game, String[] users, int i) {
        Scanner scanner = new Scanner(System.in);
        cls();
        game.PrintMap();
        System.out.println("please enter point(x) " + users[i]);
        int hor = scanner.nextInt();
        System.out.println("please enter point(y) " + users[i]);
        int ver = scanner.nextInt();
        game.addShape(hor - 1, ver - 1, i + 1);
        cls();
        game.PrintMap();
    }

    public static void turn(Game game, String[] users, int i) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please choose a block column " + users[i]);
        int col = scanner.nextInt();
        System.out.println("please choose a block row " + users[i]);
        int row = scanner.nextInt();
        System.out.println("please choose rotation: 1: clockwise   2: anticlockwise");
        int turnCode = scanner.nextInt();
        game.turnBlock(col - 1, row - 1, turnCode);
        cls();
        game.PrintMap();
    }

    public static void main(String[] args) {
        setColor();
        cls();
        Scanner scanner = new Scanner(System.in);
        boolean process = true;
        while(process) {
            System.out.println("[1] play a singular game");
            System.out.println("[2] play a twosome game");
            System.out.println("[0] exit");
            int processKey = scanner.nextInt();
            switch (processKey) {
                case 1: {
                    cls();
                    String[] users = new String[2];
                    System.out.println("please enter user one:");
                    users[0] = scanner.next();
                    users[1] = new String("COMPUTER");
                    Computer computer = new Computer();
                    while(computer.getGameSize() < 35 && computer.checkGame() == 0) {
                        addPoint(computer, users, 0);
                        if (computer.checkGame() != 0)
                            break;
                        turn(computer, users, 0);
                        if (computer.checkGame() != 0)
                            break;
                        computer.playPoint();
                        if (computer.checkGame() != 0)
                            break;
                        computer.playTurning();
                        if (computer.checkGame() != 0)
                            break;
                    }
                    computer.PrintMap();
                    if (computer.checkGame() == 1 || computer.checkGame() == 2)
                        System.out.println("winner: " + users[computer.checkGame() - 1]);
                    else
                        System.out.println("equals");
                    break;
                }
                case 2: {
                    cls();
                    String[] users = new String[2];
                    System.out.println("please enter user one:");
                    users[0] = scanner.next();
                    System.out.println("please enter user two:");
                    users[1] = scanner.next();
                    Game game = new Game();
                    while(game.getGameSize() < 35 && game.checkGame() == 0) {
                        for (int i = 0; i < 2; i++) {
                            addPoint(game, users, i);
                            if (game.checkGame() != 0)
                                break;
                            turn(game, users, i);
                            if (game.checkGame() != 0)
                                break;
                        }
                    }
                    game.PrintMap();
                    if (game.checkGame() == 1 || game.checkGame() == 2)
                        System.out.println("winner: " + users[game.checkGame() - 1]);
                    else
                        System.out.println("equals");
                    break;
                }
                case 0: {
                    process = false;
                    break;
                }
                default:{
                    System.out.println("invalid key");
                    break;
                }
            }
        }
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void setColor() {
        try{
            new ProcessBuilder("cmd", "/c", "color").inheritIO().start().waitFor();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
