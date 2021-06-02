package ua.com.finaly;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ua.com.finaly.Player.ButtonOnGrid;

import java.util.ArrayList;

import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

public class Initialization {
    private static String ship="⛵";

    @FXML
    public static void GridStart(Anketa player1,GridPane grid){
        ArrayList<ButtonOnGrid> tempbutton=new ArrayList<>();
        for(int row = 1; row < 11; row++){
            for(int column = 1; column < 11; column++){
                ButtonOnGrid tf = new ButtonOnGrid(column,row);
                grid.setMargin(tf,new Insets(1.2,1.2,1.2,1.2));
                grid.setRowIndex(tf,row);
                grid.setColumnIndex(tf,column);
                grid.getChildren().add(tf);
                tempbutton.add(tf);
            }
        }
        player1.setButtonplayer(tempbutton);
    }

    @FXML
    public static void GridTwoButton(Anketa player1,GridPane grid){
        ArrayList<ButtonOnGrid> tempbutton=new ArrayList<>();
        for(int row = 1; row < 11; row++){
            for(int column = 1; column < 11; column++){
                ButtonOnGrid tf = new ButtonOnGrid(column,row);
                for (ShipClass x:player1.getShipList()){
                    for (int i=0;i<x.getShip().size();i+=2){
                        if (x.getShip().get(i)==column&&x.getShip().get(i+1)==row){
                            tf.setText(ship);
                            if (x.isLife()){
                                tf.setTextFill(GREEN);
                            }else {
                                tf.setTextFill(RED);
                            }
                        }
                    }
                }
                grid.setMargin(tf,new Insets(1.2,1.2,1.2,1.2));
                grid.setRowIndex(tf,row);
                grid.setColumnIndex(tf,column);
                grid.getChildren().add(tf);
                tempbutton.add(tf);
            }
        }
        player1.setButtonplayer(tempbutton);
    }
}
