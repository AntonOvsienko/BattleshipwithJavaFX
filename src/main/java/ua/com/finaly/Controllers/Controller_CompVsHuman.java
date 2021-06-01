package ua.com.finaly.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import ua.com.finaly.Anketa;
import ua.com.finaly.Demo.CompLogic;
import ua.com.finaly.Initialization;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.RED;

public class Controller_CompVsHuman implements Initializable {
    private static String ship="⛵";
    private static String shipEmpty="\uD83D\uDFAB";

    @FXML
    private GridPane Grid1;
    @FXML
    private GridPane Grid2;
    @FXML
    private Label comp1;
    @FXML
    private Button exit;
    @FXML
    private Button button11;
    @FXML
    private TextArea messageWindow;

    private static Anketa player1;
    private static Anketa player2;
    private static Anketa player1_enemy;
    private static Anketa player2_enemy;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageWindow.setFont(Font.font(13));
        messageWindow.setText("Начнём");
        player1 = new Anketa("Антон");
        player2 = new Anketa();
        player1_enemy = new Anketa();
        player2_enemy = new Anketa();
        player2.setName("Basic");
        CompLogic.Initial(player2);
        GridPlayeronEnemy(player1,Grid1,player2);
    }

    private static void GridPlayeronEnemy(Anketa player1,GridPane Grid1,Anketa pl2){
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
                    tf.setTextFill(RED);
                    tf.setOpacity(1);
                }else {
                    tf.setText(shipEmpty);
                }
                Grid1.setRowIndex(tf,row);
                Grid1.setColumnIndex(tf,column);
                Grid1.getChildren().add(tf);
            }
        }
        Initialization.GridStart(player1,Grid1);
    }

    public static Anketa getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Anketa player1) {
        Controller_CompVsHuman.player1 = player1;
    }

    public static Anketa getPlayer2() {
        return player2;
    }

    public static void setPlayer2(Anketa player2) {
        Controller_CompVsHuman.player2 = player2;
    }

    public static Anketa getPlayer1_enemy() {
        return player1_enemy;
    }

    public static void setPlayer1_enemy(Anketa player1_enemy) {
        Controller_CompVsHuman.player1_enemy = player1_enemy;
    }

    public static Anketa getPlayer2_enemy() {
        return player2_enemy;
    }

    public static void setPlayer2_enemy(Anketa player2_enemy) {
        Controller_CompVsHuman.player2_enemy = player2_enemy;
    }

    public TextArea getMessageWindow() {
        return messageWindow;
    }

    public void setMessageWindow(TextArea messageWindow) {
        this.messageWindow = messageWindow;
    }
}
