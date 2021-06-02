package ua.com.finaly.Player;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import ua.com.finaly.Anketa;

import static javafx.scene.paint.Color.BLACK;
import static ua.com.finaly.Controllers.Controller_CompVsHuman.*;
import static ua.com.finaly.Demo.PlayComp.ShipChecked;

public class ButtonOnGrid extends Button {

    private static String shipicon="⛵";
    private static String shipEmpty="\uD83D\uDFAB";
    private static String ship2="⛵";

    private int x;
    private int y;
    private boolean ship;

    public ButtonOnGrid(int x, int y){
        this.x=x;
        this.y=y;
        this.setDisable(false);
        this.setPrefHeight(0);
        this.setPrefWidth(0);
        this.setAlignment(Pos.CENTER);
        this.setMaxHeight(1.7976931348623157E308);
        this.setMaxWidth(1.7976931348623157E308);
        this.setGraphicTextGap(6.0);
        this.setMinHeight(0);
        this.setMinWidth(0);
        this.setText(" ");

        this.setOnAction(this::onClick);
        this.setPadding(new Insets(1,1,1,1));
        this.setShip(false);
    }

    @FXML
    private void onClick(ActionEvent actionEvent) {
        System.out.println("кнопка нажата координаты x:" + x + "|y:" + y + shipicon);
        if (this.getText().equals(shipicon)){
            this.setText(" ");
        } else {
            this.setText(shipicon);
            this.setTextFill(BLACK);
        }
    }

    public void AIOff(int x, int y, Anketa player1, Anketa player1_enemy, Anketa player2){
        String[] XA={"A","B","C","D","E","F","G","H","I","G"};
        String text;
        if (player2.getField()[x-1][y-1] == 1) {
            player1_enemy.getField()[x-1][y-1] = 3;
            text = ShipChecked(x, y, player1_enemy,player2);
            if (text.equals("попал")){
                player1.setAILogicOn(true);
            }
        } else {
            player1_enemy.getField()[x-1][y-1] = 2;
            text = "мимо";
            player1.setAIturn(false);
            player2.setAIturn(true);
        }
        System.out.println(player1.getName()+" выбрал координаты " + XA[x-1] +
                y + "-" + text);
//        setMessageWindow.setText(player1.getName()+" выбрал координаты " + XA[x] +
//                (y+1) + "-" + text);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isShip() {
        return ship;
    }

    public void setShip(boolean ship) {
        this.ship = ship;
    }
}
