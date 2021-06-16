package ua.com.finaly.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ua.com.finaly.Anketa;
import ua.com.finaly.Initialization;
import ua.com.finaly.Player.ButtonOnGrid;
import ua.com.finaly.Player.CompLogic;
import ua.com.finaly.Player.CreateShip;
import ua.com.finaly.Player.PlayComp;
import ua.com.finaly.ShipClass;
import ua.com.finaly.Start;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.scene.paint.Color.RED;

public class Controller_HumanInizial_Player1_Player2 implements Initializable {

    private static String ship = "⛵";

    @FXML
    private Button randomstart1;

    @FXML
    private Button randomstart2;

    @FXML
    private GridPane Grid1;

    @FXML
    private Button buttonname;

    @FXML
    private Button buttonname2;

    @FXML
    private Button ready;

    @FXML
    private Button ready2;

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

        ShipText(player1);
        Initialization.GridStart(player1, Grid1);

        randomstart1.setOnAction(this::randomStart1);

        randomstart2.setOnAction(this::randomStart2);

        buttonname.setOnAction(this::onClickName);

        buttonname2.setOnAction(this::onClickName2);

        ready.setOnAction(this::onClickReady1);

        ready2.setOnAction(this::onClickReady2);

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
    private void randomStart1(ActionEvent actionEvent) {
        player1.getButtonplayer().clear();
        PlayComp.GridReset(Grid1);
        CompLogic.Initial(player1);
        GridOn(Grid1, player1);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (player1.getField()[i][j] == 1) {
                    player1.getButtonplayer().add(new ButtonOnGrid(i + 1, j + 1, ship));
                }
            }
        }
    }

    @FXML
    private void randomStart2(ActionEvent actionEvent) {
        player2.getButtonplayer().clear();
        PlayComp.GridReset(Grid1);
        CompLogic.Initial(player2);
        GridOn(Grid1, player2);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (player2.getField()[i][j] == 1) {
                    player2.getButtonplayer().add(new ButtonOnGrid(i + 1, j + 1, ship));
                }
            }
        }
    }

    @FXML
    private void onClickReady1(ActionEvent actionEvent) {
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
        ShipText(player1);
        if (player1.getShipList().stream().filter(x -> x.isLife()).count() == 10) {
            Initialization.GridStart(player2, Grid1);
            buttonname.setVisible(false);
            buttonname2.setVisible(true);
            name.setText("Ноунейм");
            ready.setVisible(false);
            ready2.setText("Ноунейм - Готов?");
            ready2.setVisible(true);
            randomstart1.setVisible(false);
            randomstart2.setVisible(true);
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
    private void onClickReady2(ActionEvent actionEvent) {
        List<ButtonOnGrid> buttonship = player2.getButtonplayer().stream().
                filter(x -> x.getText().equals(ship)).collect(Collectors.toList());
        CreateShip.Create(buttonship, player2);
        for (ShipClass x : player2.getShipList()) {
            for (ShipClass y : player2.getShipList()) {
                if (x.shipChecked(y.getAura())) {
                    x.setLife(true);
                } else {
                    x.setLife(false);
                    break;
                }
            }
        }
        Initialization.GridTwoButton(player2, Grid1);
        ShipText(player2);
        if (player2.getShipList().stream().filter(x -> x.isLife()).count() == 10) {
            ready2.setVisible(false);
            textmistake.setText("");
            ReadyGo.setVisible(true);
            randomstart2.setVisible(false);
        } else {
            String text = "";
            textmistake.setFill(RED);
            text += "Ошибка\n";
            ReadyGo.setVisible(false);
            if (player2.getShipList().size() != 10) {
                text += "Не все корабли расставлены\n";
            }
            if (player2.getShipList().size() == 10 && player2.getShipList().stream().filter(x -> x.isLife()).count() != 10 ||
                    player2.getShipList().stream().filter(x -> x.isLife() == false).count() > 0) {
                text += "Корабли пересекаются\n";
            }
            textmistake.setText(text);
        }

    }

    @FXML
    private void ShipText(Anketa player1) {
        textmanual.setText("Укажите расположение кораблей и нажимайте 'Готово'" +
                "\n" + ship + " - " + player1.getShipList().stream().filter(x -> x.getPosition().size() == 2).filter(x -> x.isLife()).count() + "шт" +
                "\n" + ship + ship + " - " + player1.getShipList().stream().filter(x -> x.getPosition().size() == 4).filter(x -> x.isLife()).count() + "шт" +
                "\n" + ship + ship + ship + " - " + player1.getShipList().stream().filter(x -> x.getPosition().size() == 6).filter(x -> x.isLife()).count() + "шт" +
                "\n" + ship + ship + ship + ship + " - " + player1.getShipList().stream().filter(x -> x.getPosition().size() == 8).filter(x -> x.isLife()).count() + "шт");
    }

    @FXML
    private void onClickName(ActionEvent actionEvent) {
        name.setText(nameEnter.getText());
        ready.setText(name.getText() + " - Готов?");
        player1.setName(name.getText());
    }

    @FXML
    private void onClickName2(ActionEvent actionEvent) {
        name.setText(nameEnter.getText());
        ready2.setText(name.getText() + " - Готов?");
        player2.setName(name.getText());
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

        for (ShipClass ship : player1.getShipList()) {
            ship.setPosition(ship.getPosition().stream().map(p -> p - 1).collect(Collectors.toList()));
            ship.setAura(ship.getAura().stream().map(p -> p - 1).collect(Collectors.toList()));
        }

        for (ShipClass ship : player2.getShipList()) {
            ship.setPosition(ship.getPosition().stream().map(p -> p - 1).collect(Collectors.toList()));
            ship.setAura(ship.getAura().stream().map(p -> p - 1).collect(Collectors.toList()));
        }

        for (ShipClass x : player1.getShipList()) {
            for (int i = 0; i < x.getPosition().size(); i += 2) {
                player1.getField()[x.getPosition().get(i)][x.getPosition().get(i + 1)] = 1;
            }
        }

        for (ShipClass x : player1.getShipList()) {
            for (int i = 0; i < x.getAura().size(); i += 2) {
                try {
                    player1.getField()[x.getAura().get(i)][x.getAura().get(i + 1)] = 4;
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }

        for (ShipClass x : player2.getShipList()) {
            for (int i = 0; i < x.getPosition().size(); i += 2) {
                player2.getField()[x.getPosition().get(i)][x.getPosition().get(i + 1)] = 1;
            }
        }

        for (ShipClass x : player2.getShipList()) {
            for (int i = 0; i < x.getAura().size(); i += 2) {
                try {
                    player2.getField()[x.getAura().get(i)][x.getAura().get(i + 1)] = 4;
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }

        Stage stage = Start.getPStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("HumanvsHuman.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 700.0D, 500.0D);
        stage.setScene(scene);
    }

    public static void GridOn(GridPane Grid1, Anketa pl1) {

        for (int row = 1; row < 11; row++) {
            for (int column = 1; column < 11; column++) {

                Label tf = new Label();
                tf.setPrefHeight(0);
                tf.setPrefWidth(0);
                tf.setAlignment(Pos.CENTER);
                tf.setMaxHeight(1.7976931348623157E308);
                tf.setMaxWidth(1.7976931348623157E308);
                tf.setGraphicTextGap(6.0);
                tf.setMinHeight(0);
                tf.setMinWidth(0);
                if (pl1.getField()[column-1][row-1] == 1) {
                    tf.setText(ship);
                }
                Grid1.setRowIndex(tf, row);
                Grid1.setColumnIndex(tf, column);
                Grid1.getChildren().add(tf);
            }
        }
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
