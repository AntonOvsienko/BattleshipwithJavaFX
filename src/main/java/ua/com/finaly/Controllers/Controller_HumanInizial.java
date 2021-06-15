package ua.com.finaly.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ua.com.finaly.*;
import ua.com.finaly.Player.CompLogic;
import ua.com.finaly.Player.ButtonOnGrid;
import ua.com.finaly.Player.CreateShip;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.scene.paint.Color.RED;

public class Controller_HumanInizial implements Initializable {

    private static String ship = "⛵";

    @FXML
    private GridPane Grid1;

    @FXML
    private Button buttonname;

    @FXML
    private Button ready;

    @FXML
    private Button back;

    @FXML
    private Button ReadyGo;

    @FXML
    private Text name;

    @FXML
    private Text textmanual;

    @FXML
    private Text textmistake;

    @FXML
    private TextField nameEnter;

    private static Anketa player1;
    private static Anketa player2;
    private static Anketa player1_enemy;
    private static Anketa player2_enemy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        player1 = new Anketa();
        player2 = new Anketa();
        player1_enemy = new Anketa();
        player2_enemy = new Anketa();
        player2.setName("Basic");

        ShipText();
        Initialization.GridStart(player1, Grid1);
        CompLogic.Initial(player2);

        buttonname.setOnAction(this::onClickName);

        ready.setOnAction(this::onClickReady);

        back.setOnAction(actionEvent -> {
            try {
                onClickBack(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ReadyGo.setOnAction(actionEvent -> {
            try {
                onClickReadyGo(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void onClickReady(ActionEvent actionEvent) {
        List<ButtonOnGrid> buttonship = player1.getButtonplayer().stream().
                filter(x -> x.getText().equals(ship)).collect(Collectors.toList());
        CreateShip.Create(buttonship, player1);
        for (ShipClass x : player1.getShipList()) {
            for (ShipClass y : player1.getShipList()) {
                if (x.shipChecked(y.getAura())) {
                    x.setLife(true);
                } else {
                    x.setLife(false);
                    break;
                }
            }
        }
        Initialization.GridTwoButton(player1, Grid1);
        ShipText();
        if (player1.getShipList().stream().filter(x -> x.isLife()).count() == 10) {
            textmistake.setText("");
            ReadyGo.setVisible(true);
        } else {
            String text = "";
            textmistake.setFill(RED);
            text += "Ошибка\n";
            ReadyGo.setVisible(false);
            if (player1.getShipList().size() != 10) {
                text += "Не все корабли расставлены\n";
            }
            if (player1.getShipList().size() == 10 && player1.getShipList().stream().filter(x -> x.isLife()).count() != 10 ||
                    player1.getShipList().stream().filter(x -> x.isLife() == false).count() > 0) {
                text += "Корабли пересекаются\n";
            }
            textmistake.setText(text);
        }

    }

    @FXML
    private void ShipText() {
        textmanual.setText("Укажите расположение кораблей и нажимайте 'Готово'" +
                "\n" + ship + " - " + player1.getShipList().stream().filter(x -> x.getPosition().size() == 2).filter(x -> x.isLife()).count() + "шт" +
                "\n" + ship + ship + " - " + player1.getShipList().stream().filter(x -> x.getPosition().size() == 4).filter(x -> x.isLife()).count() + "шт" +
                "\n" + ship + ship + ship + " - " + player1.getShipList().stream().filter(x -> x.getPosition().size() == 6).filter(x -> x.isLife()).count() + "шт" +
                "\n" + ship + ship + ship + ship + " - " + player1.getShipList().stream().filter(x -> x.getPosition().size() == 8).filter(x -> x.isLife()).count() + "шт");
    }

    @FXML
    private void onClickName(ActionEvent actionEvent) {
        name.setText(nameEnter.getText());
        player1.setName(name.getText());
    }

    @FXML
    private void onClickBack(ActionEvent actionEvent) throws IOException {
        Stage stage = Start.getPStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("StartChoice.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 400.0D, 300.0D);
        stage.setScene(scene);
    }

    @FXML
    private void onClickReadyGo(ActionEvent actionEvent) throws IOException {

        for (ShipClass ship:player1.getShipList()) {
            ship.setPosition(ship.getPosition().stream().map(p -> p - 1).collect(Collectors.toList()));
            ship.setAura(ship.getAura().stream().map(p -> p - 1).collect(Collectors.toList()));
            System.out.println(ship);
        }

        for (ShipClass ship:player2.getShipList()) {
            ship.setPosition(ship.getPosition().stream().map(p -> p - 1).collect(Collectors.toList()));
            ship.setAura(ship.getAura().stream().map(p -> p - 1).collect(Collectors.toList()));
            System.out.println(ship);
        }

        for (ShipClass x : player1.getShipList()) {
            for (int i = 0; i < x.getPosition().size(); i += 2) {
                player1.getField()[x.getPosition().get(i)][x.getPosition().get(i+1)] = 1;
            }
        }

        for (ShipClass x : player1.getShipList()) {
            for (int i = 0; i < x.getAura().size(); i += 2) {
                try {
                    player1.getField()[x.getAura().get(i)][x.getAura().get(i+1)] = 4;
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }

        System.out.println(player2);
        Stage stage = Start.getPStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CompvsHuman.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 700.0D, 500.0D);
        stage.setScene(scene);
    }

    public static Anketa getPlayer1() {
        return player1;
    }

    public static Anketa getPlayer2() {
        return player2;
    }

    public static Anketa getPlayer1_enemy() {
        return player1_enemy;
    }

    public static Anketa getPlayer2_enemy() {
        return player2_enemy;
    }
}
