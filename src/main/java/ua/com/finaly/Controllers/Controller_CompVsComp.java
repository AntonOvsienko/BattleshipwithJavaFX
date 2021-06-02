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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ua.com.finaly.Anketa;
import ua.com.finaly.Demo.*;
import ua.com.finaly.Start;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_CompVsComp implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Button button1;
    @FXML
    private Button button11;
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

        messageWindow.setFont(Font.font(13));
        messageWindow.setText("Выберите как смотреть игру,\nпошагово или сразу результат");

        player1.setName("Fortran");
        player2.setName("Basic");
        comp1.setText(player1.getName());
        comp2.setText(player2.getName());
        player1.setAIturn(true);
        player2.setAIturn(!player1.isAIturn());
        CompLogic.Initial(player1);
        CompLogic.Initial(player2);
        PlayComp.GridVizual(Grid1, Grid2, player1, player1_enemy, player2, player2_enemy, spisok1, spisok2);
        PlayComp.SpisokVizual(player1, spisok1);
        PlayComp.SpisokVizual(player2, spisok2);

        exit.setOnAction(actionEvent1 -> {
            onClickExit(actionEvent1);
        });

        button.setOnAction(actionEvent1 -> {
            try {
                onClickBack(actionEvent1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        button1.setOnAction(actionEvent -> {
            try {
                onClickStep(actionEvent);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        button11.setOnAction(actionEvent -> {
            try {
                onClickResult(actionEvent);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    private void onClickStep(ActionEvent actionEvent) throws InterruptedException, IOException {
        String name = "";

        if (player1.isAIturn()) {
            PlayComp.LetsPlay(player1, player1_enemy, player2, messageWindow);
        } else {
            PlayComp.LetsPlay(player2, player2_enemy, player1, messageWindow);
        }
        PlayComp.GridVizual(Grid1, Grid2, player1, player1_enemy, player2, player2_enemy, spisok1, spisok2);
        if (player1.getShipList().stream().filter(x -> x.getHealth() != 0).count() == 0 ||
                player2.getShipList().stream().filter(x -> x.getHealth() != 0).count() == 0) {
            if (player1.getShipList().stream().filter(x -> x.getHealth() != 0).count() != 0) {
                name = player1.getName();
            } else {
                name = player2.getName();
            }
            exit.setVisible(true);
            exit.setText("Поздравляем " + name + " победил\n" +
                    "нажмите на кнопку чтобы закончить");
        }
    }

    @FXML
    private void onClickResult(ActionEvent actionEvent) throws IOException, InterruptedException {
        String name = "";
        while (player1.getShipList().stream().filter(x -> x.getHealth() != 0).count() != 0 &&
                player2.getShipList().stream().filter(x -> x.getHealth() != 0).count() != 0) {
            if (player1.isAIturn()) {
                PlayComp.LetsPlay(player1, player1_enemy, player2, messageWindow);
            } else {
                PlayComp.LetsPlay(player2, player2_enemy, player1, messageWindow);
            }
        }
        PlayComp.GridVizual(Grid1, Grid2, player1, player1_enemy, player2, player2_enemy, spisok1, spisok2);
        messageWindow.setText("");
        if (player1.getShipList().stream().filter(x -> x.getHealth() != 0).count() != 0) {
            name = player1.getName();
        } else {
            name = player2.getName();
        }
        exit.setVisible(true);
        exit.setText("Поздравляем " + name + " победил\n" +
                "нажмите на кнопку чтобы закончить");
    }
    @FXML
    private void onClickExit (ActionEvent actionEvent){
        Stage stage = Start.getPStage();
        stage.close();
    }
    @FXML
    private void onClickBack (ActionEvent actionEvent) throws IOException {
        Stage stage = Start.getPStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("StartChoice.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 400.0D, 300.0D);
        stage.setScene(scene);

    }
}