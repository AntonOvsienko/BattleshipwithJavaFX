package ua.com.finaly.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ua.com.finaly.Anketa;
import ua.com.finaly.Player.ButtonOnPlay;
import ua.com.finaly.Player.PlayComp;
import ua.com.finaly.ShipClass;
import ua.com.finaly.Start;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static ua.com.finaly.Player.PlayComp.*;


public class Controller_HumanVsHuman implements Initializable {

    @FXML
    private Button restart;
    @FXML
    private GridPane Grid1;
    @FXML
    private GridPane GridStart1;
    @FXML
    private GridPane Grid2;
    @FXML
    private GridPane GridStart2;
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
    @FXML
    private Button choiceCoil;
    @FXML
    private Button choiceCoil2;

    Anketa player1 = Controller_HumanInizial_Player1_Player2.getPlayer1();
    Anketa player2 = Controller_HumanInizial_Player1_Player2.getPlayer2();
    Anketa player1_enemy = Controller_HumanInizial_Player1_Player2.getPlayer1_enemy();
    Anketa player2_enemy = Controller_HumanInizial_Player1_Player2.getPlayer2_enemy();

    private static int xplayer = 0;
    private static int yplayer = 0;
    private static String textship = " ";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comp1.setText(player1.getName());
        comp2.setText(player2.getName());
        player1.setAIturn(true);
        player2.setAIturn(!player1.isAIturn());
        PlayComp.GridVizualonlyHuman(Grid1, Grid2, player1, player2, spisok1, spisok2);
        GridStart2.setDisable(true);
        exit.setOnAction(actionEvent -> {
            onClickExit(actionEvent);
        });

        restart.setOnAction(actionEvent -> {
            try {
                onClickRestart(actionEvent);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        choiceCoil.setOnAction(actionEvent1 -> {
            if (!ButtonOnPlay.isActive()) {
                player1.getButtonplay().stream()
                        .filter(x -> x.getX() == xplayer)
                        .filter(y -> y.getY() == yplayer)
                        .forEach(x -> x.setDisable(true));
                onChoiceCoil(actionEvent1);
            }
        });

        choiceCoil2.setOnAction(actionEvent1 -> {
            if (!ButtonOnPlay.isActive()) {
                player2.getButtonplay().stream()
                        .filter(x -> x.getX() == xplayer)
                        .filter(y -> y.getY() == yplayer)
                        .forEach(x -> x.setDisable(true));
                onChoiceCoil2(actionEvent1);
            }
        });
    }

    @FXML
    private void onChoiceCoil(ActionEvent actionEvent1) {
        if (player2.getField()[xplayer][yplayer] == 1) {
            CheckFireUser(xplayer, yplayer, player1, player1_enemy, player2, messageWindow);
            PlayComp.SpisokVizual(player2, spisok2);
            ButtonOnPlay.setActive(true);
            if (player2.getShipList().stream().filter(t -> t.isLife() == true).count() == 0) {
                exit.setVisible(true);
                choiceCoil.setDisable(true);
                finish(player1, exit);
            }
        } else {
            GridStart2.setDisable(false);
            GridStart1.setDisable(true);
            choiceCoil.setVisible(false);
            choiceCoil2.setVisible(true);
        }
    }

    @FXML
    private void onChoiceCoil2(ActionEvent actionEvent1) {

        if (player1.getField()[xplayer][yplayer] == 1) {
            CheckFireUser(xplayer, yplayer, player2, player2_enemy, player1, messageWindow);
            PlayComp.SpisokVizual(player1, spisok1);
            ButtonOnPlay.setActive(true);
            if (player1.getShipList().stream().filter(t -> t.isLife() == true).count() == 0) {
                exit.setVisible(true);
                choiceCoil.setDisable(true);
                finish(player2, exit);
            }
        }else{
            GridStart1.setDisable(false);
            GridStart2.setDisable(true);
            choiceCoil2.setVisible(false);
            choiceCoil.setVisible(true);
        }
    }

    @FXML
    private void onClickRestart(ActionEvent actionEvent) throws IOException, InterruptedException {
        Stage stage = Start.getPStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("StartChoice.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 400.0D, 300.0D);
        stage.setScene(scene);

    }

    @FXML
    private void onClickExit(ActionEvent actionEvent) {
        Stage stage = Start.getPStage();
        stage.close();
    }


    @FXML
    public static void CheckFireUser(int x, int y, Anketa p1, Anketa p1_enemy, Anketa p2, TextArea messageWindow) {
        String[] XA = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "G"};
        String text;
        if (p2.getField()[x][y] == 1) {
            p1_enemy.getField()[x][y] = 3;
            text = PlayComp.ShipChecked(x, y, p1_enemy, p2);
        } else {
            p1_enemy.getField()[x][y] = 2;
            text = "мимо";
        }
        if (text.equals("убил")) {
            List<ShipClass> shipdestroy = p2.getShipList().stream().filter(i -> i.isLife() != true).collect(Collectors.toList());
            for (ShipClass aura : shipdestroy) {
                for (int i = 0; i < aura.getAura().size(); i += 2) {
                    int finalI = i;
                    p1.getButtonplay().stream()
                            .filter(p -> p.getX() == aura.getAura().get(finalI) + 1)
                            .filter(p -> p.getY() == aura.getAura().get(finalI + 1) + 1)
                            .forEach(p -> p.setVisible(false));
                }
            }
        }
        messageWindow.setText(p1.getName() + " выбрал координаты " + XA[x] +
                (y + 1) + "-" + text);
    }

    @FXML
    public static void finish(Anketa player, Button exit) {
        exit.setText("Игрок-" + player.getName() + ",победил нажмите для выхода");
    }

    public static void setXplayer(int xplayer) {
        Controller_HumanVsHuman.xplayer = xplayer;
    }

    public static void setYplayer(int yplayer) {
        Controller_HumanVsHuman.yplayer = yplayer;
    }

    public static void setTextship(String textship) {
        Controller_HumanVsHuman.textship = textship;
    }
}
