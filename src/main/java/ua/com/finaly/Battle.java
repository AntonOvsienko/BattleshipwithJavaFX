package ua.com.finaly;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    public static void FightStart (Anketa player1,Anketa player2){
        Anketa player1Hit=new Anketa();
        Anketa player2Hit=new Anketa();
        boolean game1=true;
        boolean game2=false;
        boolean win=false;
        Anketa playerFirst, playerSecond;
        Random random=new Random();

        int firstRandom=random.nextInt(2);
        if (firstRandom==0){
            playerFirst=player1;
            playerSecond=player2;
        } else {
            playerFirst=player2;
            playerSecond=player1;
        }
        System.out.println("Первым ходит "+ playerFirst.getName());
        while(!win){
        while (game1) {
            if (Hit(playerSecond, player1Hit,playerFirst.getName())) {
                playerSecond.setHitpoint(playerSecond.getHitpoint() - 1);
                if (playerSecond.getHitpoint()==0){
                    System.out.println(playerFirst.getName()+ " выиграл");
                    win=true;
                    game1=false;
                }
            } else {
                System.out.println("Мимо");
                game1 = false;
                game2 = true;
            }
        }
        while (game2){
                if (Hit(playerFirst,player2Hit,playerSecond.getName())){
                    playerFirst.setHitpoint(playerFirst.getHitpoint()-1);
                    if (playerFirst.getHitpoint()==0){
                        System.out.println(playerSecond.getName()+ " выиграл");
                        win=true;
                        game2=false;
                    }
                }else {
                    System.out.println("Мимо");
                    game2=false;
                    game1=true;
                }
            }
        }
    }

    public static boolean Hit(Anketa player2_1,Anketa player1Hit,String name){

        int[][] emptyField=player1Hit.getField();
        int[][] player=player2_1.getField();
        Scanner scanner=new Scanner(System.in);
        Vizualization.Vizual(emptyField);
        System.out.println(name + " вводи координаты в формате x,y");
        String hit=scanner.nextLine();
        int x=Integer.valueOf(hit.substring(0, 1));
        int y=Integer.valueOf(hit.substring(2));

        if (player[x][y]==1){
            emptyField[x][y]=3;
            player1Hit.setField(emptyField);
            Checked.ShipAll(x,y,player1Hit,player2_1);
            return true;
        } else {
            emptyField[x][y]=2;
            player1Hit.setField(emptyField);
        }
        return false;
    }
}
