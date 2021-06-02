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
import ua.com.finaly.Demo.PlayComp;
import ua.com.finaly.Player.ButtonOnGrid;
import ua.com.finaly.Player.CreateShip;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.scene.paint.Color.BLACK;

public class Controller_HumanInizial implements Initializable {

    private static String ship="⛵";

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
        Initialization.GridStart(player1,Grid1);

        buttonname.setOnAction(this::onClickName);

        ready.setOnAction(this::onClickReady);

        back.setOnAction(actionEvent -> {
            try {
                onClickBack(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ReadyGo.setOnAction(this::onClickReadyGo);
    }

    @FXML
    private void onClickReady(ActionEvent actionEvent) {
        List<ButtonOnGrid> buttonship=player1.getButtonplayer().stream().
                filter(x->x.getText().equals(ship)).collect(Collectors.toList());
        CreateShip.Create(buttonship,player1);
        for (ShipClass x:player1.getShipList()){
            for (ShipClass y:player1.getShipList()){
                x.setLife(x.shipChecked(y.getAura()));
            }
        }
        Initialization.GridTwoButton(player1,Grid1);
        ShipText();
    }

    private void ShipText() {
        textmanual.setText("Укажите расположение кораблей и нажимайте 'Готово'" +
                "\n" + ship + " - " + player1.getShipList().stream().filter(x->x.getShip().size()==2).filter(x->x.isLife()).count() + "шт" +
                "\n" + ship + ship + " - " + player1.getShipList().stream().filter(x->x.getShip().size()==4).filter(x->x.isLife()).count() + "шт" +
                "\n" + ship + ship + ship + " - " + player1.getShipList().stream().filter(x->x.getShip().size()==6).filter(x->x.isLife()).count() + "шт" +
                "\n" + ship + ship + ship + ship + " - " +player1.getShipList().stream().filter(x->x.getShip().size()==8).filter(x->x.isLife()).count() + "шт");
    }

    @FXML
    private void onClickName(ActionEvent actionEvent) {
        name.setText(nameEnter.getText());
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
    private void onClickReadyGo(ActionEvent actionEvent) {
        name.setText(nameEnter.getText());
    }


}
