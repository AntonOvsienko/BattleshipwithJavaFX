package ua.com.finaly.Demo;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import ua.com.finaly.Anketa;
import ua.com.finaly.ShipClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static javafx.scene.paint.Color.RED;

public class PlayComp {

    private static String ship="⛵";
    private static String empty="⬜";
    private static String shipEmpty="\uD83D\uDFAB";
    private static String shipHit="\u2B1B";

    public static void LetsPlay(Anketa player1, Anketa player1_enemy,Anketa player2, TextArea messageWindow) throws InterruptedException {
            Random random = new Random();
            while (true){
            if (player1.isAILogicOn()) {
                AIOn(player1, player1_enemy, player2,messageWindow);
            } else {
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                if (player1_enemy.getField()[x][y] == 3 || player1_enemy.getField()[x][y] == 2) {
                    continue;
                }
                AIOff(x, y, player1, player1_enemy, player2,messageWindow);
            }
            break;
        }
    }

    public static String ShipChecked(int x, int y,Anketa player1_enemy,Anketa player2){
        for (ShipClass shipcounter:player2.getShipList()){
            for (int i=0;i<shipcounter.getShip().size();i+=2){
                if (shipcounter.getShip().get(i)==x&&shipcounter.getShip().get(1+i)==y){
                    shipcounter.setHealth(shipcounter.getHealth()-1);
                    if (shipcounter.getHealth()==0){
                        shipcounter.setLife(false);
                        int[] temp=new int[shipcounter.getAura().size()];
                        for (int p=0;p < temp.length;p++){
                            temp[p]=shipcounter.getAura().get(p);
                        }
                        for (int j=0;j < temp.length;j+=2){
                            player1_enemy.getField()[temp[j]][temp[j+1]]=2;
                        }
                        return "убил";
                    }
                }
            }
        }
        return "попал";
    }

    public static void AIOff(int x,int y,Anketa player1, Anketa player1_enemy, Anketa player2,TextArea messageWindow){
        String[] XA={"A","B","C","D","E","F","G","H","I","G"};
        String text;
        if (player2.getField()[x][y] == 1) {
            player1_enemy.getField()[x][y] = 3;
            text = ShipChecked(x, y, player1_enemy,player2);
            if (text.equals("попал")){
                player1.setAILogicOn(true);
                AICoordinateFuture(x,y,player1,player1_enemy);
            }
        } else {
            player1_enemy.getField()[x][y] = 2;
            text = "мимо";
            player1.setAIturn(false);
            player2.setAIturn(true);
        }
        messageWindow.setText(player1.getName()+" выбрал координаты " + XA[x] +
                (y+1) + "-" + text);
    }

    public static void AIOn(Anketa p1, Anketa p1_enemy, Anketa p2, TextArea messageWindow){
        String[] XA={"A","B","C","D","E","F","G","H","I","G"};
        String text;
        int x;
        int y;
        Random random = new Random();
        if (p1.getAILogic().size()==2){
            x=p1.getAILogic().get(0);
            y=p1.getAILogic().get(1);
            p1.getAILogic().clear();
        } else {
            int ji = random.nextInt(p1.getAILogic().size()/2);
            x = p1.getAILogic().get(ji*2);
            p1.getAILogic().remove(ji*2);
            y = p1.getAILogic().get(ji*2);
            p1.getAILogic().remove(ji*2);
            }
        if (p2.getField()[x][y] == 1) {
            p1_enemy.getField()[x][y] = 3;
            text = ShipChecked(x, y, p1_enemy,p2);
            if (text.equals("попал")){
                AICoordinateFuture(x,y,p1,p1_enemy);
            } else {
                p1.setAILogicOn(false);
                p1.getAILogic().clear();
            }
        } else {
            p1_enemy.getField()[x][y] = 2;
            text = "мимо";
            p1.setAIturn(false);
            p2.setAIturn(true);
        }
        messageWindow.setText(p1.getName()+" выбрал координаты " + XA[x] +
                (y+1) + "-" + text);
    }

    public static void AICoordinateFuture(int x, int y, Anketa p1, Anketa p1_enemy){
        System.out.println(p1.getAILogic());
        try {
            if (p1_enemy.getField()[x - 1][y] == 0) {
                p1.getAILogic().add(x - 1);
                p1.getAILogic().add(y);
            }
        } catch (ArrayIndexOutOfBoundsException ignored){

        }

        try {
            if (p1_enemy.getField()[x + 1][y] == 0) {
                p1.getAILogic().add(x + 1);
                p1.getAILogic().add(y);
            }
        } catch (ArrayIndexOutOfBoundsException ignored){

        }

        try {
            if (p1_enemy.getField()[x][y-1] == 0){
                p1.getAILogic().add(x);
                p1.getAILogic().add(y-1);
            }
        } catch (ArrayIndexOutOfBoundsException e){

        }

        try {
            if (p1_enemy.getField()[x][y+1] == 0){
                p1.getAILogic().add(x);
                p1.getAILogic().add(y+1);
            }
        } catch (IndexOutOfBoundsException e){
        }

        try{
        if (p1_enemy.getField()[x+1][y]==3){
            int round=0;
            int i;
            for (i=0;i<p1.getAILogic().size()-round+1;i+=2){
                if (p1.getAILogic().get(i+1)!=y){
                    p1.getAILogic().remove(i);
                    p1.getAILogic().remove(i);
                    i-=2;
                    round+=2;
                }
            }
        }
        }catch (IndexOutOfBoundsException e){
        }

        try{
            if (p1_enemy.getField()[x-1][y]==3){
                int round=0;
                int i;
                for (i=0;i<p1.getAILogic().size()-round+1;i+=2){
                    if (p1.getAILogic().get(i+1)!=y){
                        p1.getAILogic().remove(i);
                        p1.getAILogic().remove(i);
                        i-=2;
                        round+=2;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
        }

        try{
            if (p1_enemy.getField()[x][y+1]==3){
                int round=0;
                int i;
                for (i=0;i<p1.getAILogic().size()-round+1;i+=2){
                    if (p1.getAILogic().get(i)!=x){
                        p1.getAILogic().remove(i);
                        p1.getAILogic().remove(i);
                        i-=2;
                        round+=2;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
        }

        try{
            if (p1_enemy.getField()[x][y-1]==3){
                int round=0;
                int i;
                for (i=0;i<p1.getAILogic().size()-round+1;i+=2){
                    if (p1.getAILogic().get(i)!=x){
                        p1.getAILogic().remove(i);
                        p1.getAILogic().remove(i);
                        i-=2;
                        round+=2;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
        }
    }

    public static void GridVizual(GridPane Grid1, GridPane Grid2,Anketa pl1, Anketa pl1_enemy, Anketa pl2,
                                  Anketa pl2_enemy, TextArea spisok1, TextArea spisok2){

        GridReset(Grid1);
        GridReset(Grid2);
        GridOn(Grid1, pl1, pl2_enemy);
        GridOn(Grid2, pl2, pl1_enemy);
        SpisokVizual(pl1, spisok1);
        SpisokVizual(pl2, spisok2);
    }

    public static void GridVizualwithHuman(GridPane Grid1, GridPane Grid2,Anketa pl1, Anketa pl1_enemy, Anketa pl2,
                                  Anketa pl2_enemy, TextArea spisok1, TextArea spisok2){

        GridReset(Grid1);
        GridReset(Grid2);
        GridOn(Grid1, pl1, pl2_enemy);
        GridOnComp(Grid2, pl2, pl1_enemy);
        SpisokVizual(pl1, spisok1);
        SpisokVizual(pl2, spisok2);
    }

    public static void SpisokVizual(Anketa pl2, TextArea spisok2) {
        spisok2.setText(ship +"             -" + pl2.getShipList().stream().filter(x->x.getShip().size()==2).filter(x->x.getHealth()!=0).count() + "шт" +
                "\n" + ship + ship + "          -" + pl2.getShipList().stream().filter(x->x.getShip().size()==4).filter(x->x.getHealth()!=0).count() + "шт" +
                "\n" + ship + ship + ship + "       -"+ pl2.getShipList().stream().filter(x->x.getShip().size()==6).filter(x->x.getHealth()!=0).count() + "шт" +
                "\n" + ship + ship + ship + ship +"    -"+ pl2.getShipList().stream().filter(x->x.getShip().size()==8).filter(x->x.getHealth()!=0).count() + "шт");
    }

    private static void GridReset(GridPane grid){
        grid.setGridLinesVisible(false);
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);
        String[] vertikal={"A","B","C","D","E","F","G","H","I","G"};
        String[] gorizont={"1","2","3","4","5","6","7","8","9","10"};
        for (int i=1;i<11;i++){
            Label tfg = new Label();
            Label tfh = new Label();
            tfg.setPrefHeight(0);
            tfg.setPrefWidth(0);
            tfg.setAlignment(Pos.CENTER);
            tfg.setMaxHeight(1.7976931348623157E308);
            tfg.setMaxWidth(1.7976931348623157E308);
            tfg.setGraphicTextGap(6.0);
            tfg.setMinHeight(0);
            tfg.setMinWidth(0);
            tfg.setText(gorizont[i-1]);
            tfh.setPrefHeight(0);
            tfh.setPrefWidth(0);
            tfh.setAlignment(Pos.CENTER);
            tfh.setMaxHeight(1.7976931348623157E308);
            tfh.setMaxWidth(1.7976931348623157E308);
            tfh.setGraphicTextGap(6.0);
            tfh.setMinHeight(0);
            tfh.setMinWidth(0);
            tfh.setText(vertikal[i-1]);
            grid.setRowIndex(tfg,i);
            grid.setColumnIndex(tfg,0);
            grid.getChildren().add(tfg);
            grid.setRowIndex(tfh,0);
            grid.setColumnIndex(tfh,i);
            grid.getChildren().add(tfh);
        }
    }
    private static void GridOn(GridPane Grid1,Anketa pl1,
                               Anketa pl2_enemy){
        for(int row = 1; row < 11; row++){
            for(int column = 1; column < 11; column++){

                Label tf = new Label();
                tf.setPrefHeight(0);
                tf.setPrefWidth(0);
                tf.setAlignment(Pos.CENTER);
                tf.setMaxHeight(1.7976931348623157E308);
                tf.setMaxWidth(1.7976931348623157E308);
                tf.setGraphicTextGap(6.0);
                tf.setMinHeight(0);
                tf.setMinWidth(0);
                if (pl1.getField()[column-1][row-1]==1&&pl2_enemy.getField()[column-1][row-1]==3){
                    tf.setDisable(true);
                    tf.setText(ship);
                    tf.setOpacity(1);
                    tf.setTextFill(RED);
                } else if (pl1.getField()[column-1][row-1]==1){
                    tf.setDisable(true);
                    tf.setText(ship);
                    tf.setOpacity(0.2);
                } else if (pl2_enemy.getField()[column-1][row-1]==2){
                    tf.setDisable(true);
                    tf.setText(shipEmpty);
                }

                Grid1.setRowIndex(tf,row);
                Grid1.setColumnIndex(tf,column);
                Grid1.getChildren().add(tf);
            }
        }
    }

    private static void GridOnComp(GridPane Grid1,Anketa pl1,
                               Anketa pl2_enemy){
        for(int row = 1; row < 11; row++){
            for(int column = 1; column < 11; column++){

                Button tf = new Button();
                tf.setPrefHeight(0);
                tf.setPrefWidth(0);
                tf.setAlignment(Pos.CENTER);
                tf.setMaxHeight(1.7976931348623157E308);
                tf.setMaxWidth(1.7976931348623157E308);
                tf.setGraphicTextGap(6.0);
                tf.setMinHeight(0);
                tf.setMinWidth(0);
                if (pl1.getField()[column-1][row-1]==1&&pl2_enemy.getField()[column-1][row-1]==3){
                    tf.setText(ship);
                    tf.setOpacity(1);
                    tf.setTextFill(RED);
                } else if (pl1.getField()[column-1][row-1]==1){
                    tf.setText(ship);
                    tf.setOpacity(0.2);
                } else if (pl2_enemy.getField()[column-1][row-1]==2){
                    tf.setText(shipEmpty);
                }

                Grid1.setRowIndex(tf,row);
                Grid1.setColumnIndex(tf,column);
                Grid1.getChildren().add(tf);
            }
        }
    }
}
