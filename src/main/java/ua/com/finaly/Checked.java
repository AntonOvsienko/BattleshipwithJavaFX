package ua.com.finaly;

import java.util.ArrayList;
import java.util.List;

public class Checked {

    public static boolean Fire(int[] massivfinal, Anketa player){
        int [][] massiveTemp= player.getField();
        for (int i=0;i<(massivfinal.length)/2;i=i+2){
            if (massiveTemp[massivfinal[i]][massivfinal[i+1]]!=0){return false;}
        }
        return true;
    }

    public static boolean ShipCorrected(int[] field) {
        int xcount=0;
        int ycount=0;
        switch (field.length / 2) {
            case 2:
                if ((field[0]==field[2]+1)||(field[0]==field[2]-1)){
                  xcount++;
                }
                if ((field[1]==field[3]+1)||(field[1]==field[3]-1)){
                  ycount++;
                }
                if (xcount!=1&&ycount!=1){
                    return false;
                }
                break;
            case 3:
                for (int i=0;i<5;i+=2) {
                    if (((field[i] == field[2] + 1) || (field[i] == field[2] - 1)) || ((field[i] == field[4] + 1) || (field[i] == field[4] - 1))) {
                        xcount++;
                    }
                }
                for (int i=1;i<6;i+=2){
                    if (((field[i] == field[3] + 1) || (field[i] == field[3] - 1)) || ((field[i] == field[5] + 1) || (field[i] == field[5] - 1))){
                        ycount++;
                    }
                }
                if (xcount!=3&&ycount!=3){
                    return false;
                }
                break;

            case 4:
                for (int i=0;i<7;i+=2) {
                    if (((field[i] == field[2] + 1) || (field[i] == field[2] - 1)) || ((field[i] == field[4] + 1) ||
                            (field[i] == field[4] - 1)||(field[i] == field[6] - 1))) {
                        xcount++;
                    }
                }
                for (int i=1;i<6;i+=2){
                    if (((field[i] == field[3] + 1) || (field[i] == field[3] - 1)) || ((field[i] == field[5] + 1) ||
                            (field[i] == field[7] - 1)||(field[i] == field[7] - 1))){
                     ycount++;
                    }
                }
            if (xcount!=4&&ycount!=4){
                return false;
            }
            break;
        }
        return true;
    }

    public static void ShipAll(int x, int y, Anketa player1Hit, Anketa player2_1) {
        List<Integer> ship=new ArrayList<>();
        int[][] playerShip=player1Hit.getField();
        int[][] fullShip = player2_1.getField();
        int bomb=0;
        ship.add(x);
        ship.add(y);
        for(int i=1;i<4;i++){
            if (x+i<10){
                if (fullShip[x+i][y]==1){
                   ship.add(x+i);
                   ship.add(y);
                }
                if(fullShip[x+i][y]==0||fullShip[x+i][y]==4){
                break;
                }
            }
        }
        for(int i=1;i<4;i++){
            if (x-i>=0){
                if (fullShip[x-i][y]==1){
                    ship.add(x-i);
                    ship.add(y);
                }
                if(fullShip[x-i][y]==0||fullShip[x-i][y]==4){
                    break;
                }
            }
        }
        for(int i=1;i<4;i++){
            if (y+i<10){
                if (fullShip[x][y+i]==1){
                    ship.add(x);
                    ship.add(y+i);
                }
                if(fullShip[x][y+i]==0||fullShip[x][y+i]==4){
                    break;
                }
            }
        }
        for(int i=1;i<4;i++){
            if (y-i>=0){
                if (fullShip[x][y-i]==1){
                    ship.add(x);
                    ship.add(y-i);
                }
                if(fullShip[x][y-i]==0||fullShip[x][y-i]==4){
                    break;
                }
            }
        }
        int[] arr=new int[ship.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ship.get(i);
        }
        for(int i=0;i< arr.length;i+=2){
                if (playerShip[arr[i]][arr[i+1]]==3){
                    bomb++;
            }
        }
        if (bomb!=arr.length/2){
            System.out.println("Попал");
        }else {
            System.out.println("Уничтожил");
            Vizualization.AuraForBattle(player1Hit,arr);
        }
    }

}
