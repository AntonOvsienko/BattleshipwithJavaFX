package ua.com.finaly.Player;

import ua.com.finaly.Anketa;
import ua.com.finaly.ShipClass;
import ua.com.finaly.Vizualization;

import java.util.Arrays;
import java.util.Random;

public class CompLogic {

    public static void Initial(Anketa player) {
        int[][] ship = new int[10][10];

        while (player.getCount4() != 0) {
            int[] massivfinal = new int[8];
            BuildCoil(massivfinal, 4);
            if (CheckCoil(massivfinal)) {
                continue;
            } else {
                player.setCount4(player.getCount4() - 1);
                int x1 = massivfinal[0];
                int y1 = massivfinal[1];
                int x2 = massivfinal[2];
                int y2 = massivfinal[3];
                int x3 = massivfinal[4];
                int y3 = massivfinal[5];
                int x4 = massivfinal[6];
                int y4 = massivfinal[7];
                ship[x1][y1] = 1;
                ship[x2][y2] = 1;
                ship[x3][y3] = 1;
                ship[x4][y4] = 1;
                player.setField(ship);
                player.getShipList().add(new ShipClass(x1+1, y1+1, x2+1, y2+1, x3+1, y3+1, x4+1, y4+1));
                Vizualization.Aura(massivfinal, player);
            }
        }
        while (player.getCount3() != 0) {
            int[] massivfinal = new int[6];
            BuildCoil(massivfinal, 3);
            if (CheckCoil(massivfinal) || CheckAnyShip(massivfinal, player)) {
                continue;
            } else {
                player.setCount3(player.getCount3() - 1);
                int x1 = massivfinal[0];
                int y1 = massivfinal[1];
                int x2 = massivfinal[2];
                int y2 = massivfinal[3];
                int x3 = massivfinal[4];
                int y3 = massivfinal[5];
                ship[x1][y1] = 1;
                ship[x2][y2] = 1;
                ship[x3][y3] = 1;
                player.setField(ship);
                player.getShipList().add(new ShipClass(x1+1, y1+1, x2+1, y2+1, x3+1, y3+1));
                Vizualization.Aura(massivfinal, player);
            }
        }

        while (player.getCount2() != 0) {
            int[] massivfinal = new int[4];
            BuildCoil(massivfinal, 2);
            if (CheckCoil(massivfinal) || CheckAnyShip(massivfinal, player)) {
                continue;
            } else {
                player.setCount2(player.getCount2() - 1);
                int x1 = massivfinal[0];
                int y1 = massivfinal[1];
                int x2 = massivfinal[2];
                int y2 = massivfinal[3];
                ship[x1][y1] = 1;
                ship[x2][y2] = 1;
                player.setField(ship);
                player.getShipList().add(new ShipClass(x1+1, y1+1, x2+1, y2+1));
                Vizualization.Aura(massivfinal, player);
            }
        }
        while (player.getCount1() != 0) {
            int[] massivfinal = new int[2];
            BuildCoil(massivfinal, 1);
            if (CheckAnyShip(massivfinal, player)) {
                continue;
            } else {
                player.setCount1(player.getCount1() - 1);
                int x1 = massivfinal[0];
                int y1 = massivfinal[1];
                ship[x1][y1] = 1;
                player.setField(ship);
                player.getShipList().add(new ShipClass(x1+1, y1+1));
                Vizualization.Aura(massivfinal, player);
            }
        }
        player.setCount1(4);
        player.setCount2(3);
        player.setCount3(2);
        player.setCount4(1);

    }

    public static void BuildCoil(int[] massivfinal, int coint) {
        Random random = new Random();
        massivfinal[0] = random.nextInt(10);
        massivfinal[1] = random.nextInt(10);
        int xy = random.nextInt(4);
        for (int i = 0; i < (coint - 1) * 2; i += 2) {
            if (xy == 0) {
                massivfinal[i + 2] = massivfinal[i] + 1;
                massivfinal[i + 3] = massivfinal[i + 1];
            } else if (xy == 1) {
                massivfinal[i + 2] = massivfinal[i] - 1;
                massivfinal[i + 3] = massivfinal[i + 1];
            } else if (xy == 2) {
                massivfinal[i + 2] = massivfinal[i];
                massivfinal[i + 3] = massivfinal[i + 1] + 1;
            } else {
                massivfinal[i + 2] = massivfinal[i];
                massivfinal[i + 3] = massivfinal[i + 1] - 1;
            }
        }
    }

    public static boolean CheckCoil(int[] massiv) {
        return Arrays.stream(massiv).anyMatch(i -> i < 0 || i > 9);
    }

    public static boolean CheckAnyShip(int[] massiv, Anketa player) {
        int[][] massiveTemp = player.getField();
        for (int i = 0; i < massiv.length; i += 2) {
            if (massiveTemp[massiv[i]][massiv[i + 1]] == 1 || massiveTemp[massiv[i]][massiv[i + 1]] == 4) {
                return true;
            }
        }
        return false;
    }
}