package ua.com.finaly.Demo;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import ua.com.finaly.Anketa;
import ua.com.finaly.Controllers.Controller_CompVsHuman;
import ua.com.finaly.Player.ButtonOnPlay;
import ua.com.finaly.ShipClass;

import java.util.Random;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;

public class PlayComp {

    private static String ship="⛵";
    private static String empty="⬜";
    private static String shipEmpty="✖";
    private static String shipHit="\u2B1B";

    public static void LetsPlay(Anketa player2, Anketa player2_enemy,Anketa player1, TextArea messageWindow) {
            Random random = new Random();
            while (true){
            if (player2.isAILogicOn()) {
                AIOn(player2, player2_enemy, player1,messageWindow);
            } else {
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                if (player2_enemy.getField()[x][y] == 3 || player2_enemy.getField()[x][y] == 2) {
                    continue;
                }
                AIOff(x, y, player2, player2_enemy, player1,messageWindow);
            }
            break;
        }
    }

    public static String ShipChecked(int x, int y,Anketa player1_enemy,Anketa player2){
        for (ShipClass shipcounter:player2.getShipList()){
            for (int i = 0; i<shipcounter.getPosition().size(); i+=2){
                if (shipcounter.getPosition().get(i)==x&&shipcounter.getPosition().get(i+1)==y){
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
                        Controller_CompVsHuman.setTextship("убил");
                        return "убил";
                    }
                }
            }
        }
        Controller_CompVsHuman.setTextship("попал");
        return "попал";
    }

    public static void AIOff(int x,int y,Anketa player2, Anketa player2_enemy, Anketa player1,TextArea messageWindow){
        String[] XA={"A","B","C","D","E","F","G","H","I","G"};
        String text;
        if (player1.getField()[x][y] == 1) {
            player2_enemy.getField()[x][y] = 3;
            text = ShipChecked(x, y, player2_enemy,player1);
            if (text.equals("попал")){
                player2.setAILogicOn(true);
                AICoordinateFuture(x,y,player2,player2_enemy);
            }
        } else {
            player2_enemy.getField()[x][y] = 2;
            text = "мимо";
            player2.setAIturn(false);
            player1.setAIturn(true);
        }
        messageWindow.setText(player2.getName()+" выбрал координаты " + XA[x] +
                (y+1) + "-" + text);
    }

    public static void AIOn(Anketa p2, Anketa p2_enemy, Anketa p1, TextArea messageWindow){
        String[] XA={"A","B","C","D","E","F","G","H","I","G"};
        String text;
        int x;
        int y;
        Random random = new Random();

        if (p2.getAILogic().size()==2){
            x=p2.getAILogic().get(0);
            y=p2.getAILogic().get(1);
            p2.getAILogic().clear();
        } else {
            int ji = random.nextInt(p2.getAILogic().size()/2);
            x = p2.getAILogic().get(ji*2);
            p2.getAILogic().remove(ji*2);
            y = p2.getAILogic().get(ji*2);
            p2.getAILogic().remove(ji*2);
            }
        System.out.println(x+","+y);
        if (p1.getField()[x][y] == 1) {
            p2_enemy.getField()[x][y] = 3;
            text = ShipChecked(x, y, p2_enemy, p1);
            if (text.equals("попал")){
                AICoordinateFuture(x, y, p2, p2_enemy);
            } else {
                p2.setAILogicOn(false);
                p2.getAILogic().clear();
            }
        } else {
            p2_enemy.getField()[x][y] = 2;
            text = "мимо";
            System.out.println(p2.getAILogic());
            p2.setAIturn(false);
            p1.setAIturn(true);
        }

        messageWindow.setText(p2.getName()+" выбрал координаты " + XA[x] +
                (y+1) + "-" + text);
    }

    public static void AICoordinateFuture(int x, int y, Anketa p2, Anketa p2_enemy){
        System.out.println(p2.getAILogic());
        try {
            if (p2_enemy.getField()[x - 1][y] == 0) {
                p2.getAILogic().add(x - 1);
                p2.getAILogic().add(y);
            }
        } catch (ArrayIndexOutOfBoundsException ignored){

        }

        try {
            if (p2_enemy.getField()[x + 1][y] == 0) {
                p2.getAILogic().add(x + 1);
                p2.getAILogic().add(y);
            }
        } catch (ArrayIndexOutOfBoundsException ignored){

        }

        try {
            if (p2_enemy.getField()[x][y-1] == 0){
                p2.getAILogic().add(x);
                p2.getAILogic().add(y-1);
            }
        } catch (ArrayIndexOutOfBoundsException e){

        }

        try {
            if (p2_enemy.getField()[x][y+1] == 0){
                p2.getAILogic().add(x);
                p2.getAILogic().add(y+1);
            }
        } catch (IndexOutOfBoundsException e){

        }

        try{
            if (p2_enemy.getField()[x+1][y]==3){
                for (int i=0;i<p2.getAILogic().size();i+=2){
                    if (p2.getAILogic().get(i+1)!=y){
                        p2.getAILogic().remove(i);
                        p2.getAILogic().remove(i);
                        i-=2;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){

        }

        try{
            if (p2_enemy.getField()[x-1][y]==3){
                for (int i=0;i<p2.getAILogic().size();i+=2){
                    if (p2.getAILogic().get(i+1)!=y){
                        p2.getAILogic().remove(i);
                        p2.getAILogic().remove(i);
                        i-=2;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
        }

        try{
            if (p2_enemy.getField()[x][y+1]==3){
                for (int i=0;i<p2.getAILogic().size();i+=2){
                    if (p2.getAILogic().get(i)!=x){
                        p2.getAILogic().remove(i);
                        p2.getAILogic().remove(i);
                        i-=2;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
        }

        try{
            if (p2_enemy.getField()[x][y-1]==3){
                for (int i=0;i<p2.getAILogic().size();i+=2){
                    if (p2.getAILogic().get(i)!=x){
                        p2.getAILogic().remove(i);
                        p2.getAILogic().remove(i);
                        i-=2;
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
        }
        System.out.println(p2.getAILogic());
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
        GridOnComp(Grid2, pl2, pl1);
        SpisokVizual(pl1, spisok1);
        SpisokVizual(pl2, spisok2);
    }

    public static void SpisokVizual(Anketa pl2, TextArea spisok2) {
        spisok2.setText(ship +"             -" + pl2.getShipList().stream().filter(x->x.getPosition().size()==2).filter(x->x.getHealth()!=0).count() + "шт" +
                "\n" + ship + ship + "          -" + pl2.getShipList().stream().filter(x->x.getPosition().size()==4).filter(x->x.getHealth()!=0).count() + "шт" +
                "\n" + ship + ship + ship + "       -"+ pl2.getShipList().stream().filter(x->x.getPosition().size()==6).filter(x->x.getHealth()!=0).count() + "шт" +
                "\n" + ship + ship + ship + ship +"    -"+ pl2.getShipList().stream().filter(x->x.getPosition().size()==8).filter(x->x.getHealth()!=0).count() + "шт");
    }

    public static void GridReset(GridPane grid){
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
    public static void GridOn(GridPane Grid1,Anketa pl1,
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
                    tf.setOpacity(1);
                    tf.setText(shipEmpty);
                }

                Grid1.setRowIndex(tf,row);
                Grid1.setColumnIndex(tf,column);
                Grid1.getChildren().add(tf);
            }
        }
    }

    private static void GridOnComp(GridPane Grid1,Anketa pl2, Anketa pl1){
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
                if (pl2.getField()[column-1][row-1]==1){
                    tf.setText(ship);
                    tf.setOpacity(1);
                    tf.setTextFill(RED);
                } else {
                    tf.setOpacity(1);
                    tf.setText(shipEmpty);
                    tf.setTextFill(BLACK);
                }

                Grid1.setRowIndex(tf,row);
                Grid1.setColumnIndex(tf,column);
                Grid1.getChildren().add(tf);
            }
        }

        for(int row = 1; row < 11; row++){
            for(int column = 1; column < 11; column++){
                ButtonOnPlay tf = new ButtonOnPlay(column,row);
                tf.setPrefHeight(0);
                tf.setPrefWidth(0);
                tf.setAlignment(Pos.CENTER);
                tf.setMaxHeight(1.7976931348623157E308);
                tf.setMaxWidth(1.7976931348623157E308);
                tf.setGraphicTextGap(6.0);
                tf.setMinHeight(0);
                tf.setMinWidth(0);
                Grid1.setRowIndex(tf,row);
                Grid1.setColumnIndex(tf,column);
                Grid1.getChildren().add(tf);
                pl1.getButtonplay().add(tf);
            }
        }
    }
}
