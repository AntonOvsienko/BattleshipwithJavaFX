package ua.com.finaly.Player;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import ua.com.finaly.Anketa;
import ua.com.finaly.Controllers.Controller_CompVsHuman;
import ua.com.finaly.Controllers.Controller_HumanInizial;
import ua.com.finaly.Demo.PlayComp;

public class ButtonOnPlay extends Button {

    Anketa player1 = Controller_HumanInizial.getPlayer1();
    Anketa player2 = Controller_HumanInizial.getPlayer2();
    Anketa player1_enemy = Controller_HumanInizial.getPlayer1_enemy();
    Anketa player2_enemy = Controller_HumanInizial.getPlayer2_enemy();
    private int x;
    private int y;

    @FXML
    private GridPane Grid1;
    @FXML
    private GridPane Grid2;
    @FXML
    private Label comp1;
    @FXML
    private Label comp2;
    @FXML
    private TextArea messageWindow;
    @FXML
    private TextArea spisok1;
    @FXML
    private TextArea spisok2;
    @FXML
    private Button exit;

    public ButtonOnPlay(int x,int y){
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

        this.setOnAction(actionEvent -> {
            try {
                onClick(actionEvent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        this.setPadding(new Insets(1,1,1,1));

    }

    private void onClick(ActionEvent actionEvent) throws InterruptedException {
        this.setDisable(true);
        Controller_CompVsHuman.LetsBattle(player1,player1_enemy,player2,player2_enemy,messageWindow, exit,x,y);
    }
}
