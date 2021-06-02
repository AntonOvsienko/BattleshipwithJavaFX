package ua.com.finaly;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

public class Vizualization {
    private static String ship="⛵";
    private static String empty="\u2B1C";
    private static String shipEmpty="\u2BBD";
    private static String shipHit="\u2B1B";
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";


    public static void Vizual(int[][] player){
        System.out.println("----------------------------------------");
        System.out.println("  ⓪"+"①"+"②"+"③"+"④"+"⑤"+"⑥"+"⑦"+"⑧"+"⑨");
        for (int i=0;i<player.length;i++){
            System.out.printf("%1d|",i);
            for (int j=0;j<player[i].length;j++){
                switch (player[j][i]){
                    case (1):
                        System.out.print(ship);
                        break;
                    case (2):
                        System.out.print(shipEmpty);
                        break;
                    case (3):
                        System.out.print(RED + shipHit + RESET);
                        break;
                    case (4):
                        System.out.print(PURPLE + empty + RESET);
                        break;
                    default:
                        System.out.print(empty);
                }
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");
    }


    public static void Aura(int[] massivfinal, Anketa player) {
        int[][] aura = player.getField();
        for (int k = 0; k < massivfinal.length/2; k++) {
            for (int i = massivfinal[0 + k * 2] - 1; i < massivfinal[0 + k * 2] + 2; i++) {
                for (int j = massivfinal[1 + k * 2] - 1; j < massivfinal[1 + k * 2] + 2; j++) {
                    if (i >= 0 && i < 10 && j >= 0 && j < 10) {
                        if (aura[i][j] != 1 && aura[i][j] != 3) {
                            aura[i][j] = 4;
                        }
                    }
                }
            }
        }
        player.setField(aura);
    }
    public static void AuraForBattle(Anketa player, int[] massivfinal) {
        int[][] aura = player.getField();
        for (int i=0;i< massivfinal.length;i+=2){
            aura[massivfinal[i]][massivfinal[i+1]] = 2;
        }
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
    private static void GridOn(GridPane Grid1){
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

                Grid1.setRowIndex(tf,row);
                Grid1.setColumnIndex(tf,column);
                Grid1.getChildren().add(tf);
            }
        }
    }

}

