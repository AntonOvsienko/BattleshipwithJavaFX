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
import ua.com.finaly.Player.PlayComp;
import ua.com.finaly.Player.ButtonOnPlay;
import ua.com.finaly.Start;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller_CompVsHuman implements Initializable {

    @FXML
    private Button restart;
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
    @FXML
    private Button choiceCoil;

    Anketa player1 = Controller_HumanInizial.getPlayer1();
    Anketa player2 = Controller_HumanInizial.getPlayer2();
    Anketa player1_enemy = Controller_HumanInizial.getPlayer1_enemy();
    Anketa player2_enemy = Controller_HumanInizial.getPlayer2_enemy();

    private static int xplayer=0;
    private static int yplayer=0;
    private static String textship=" ";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player2.setName("Basic");
        comp1.setText(player1.getName());
        comp2.setText(player2.getName());
        player1.setAIturn(true);
        player2.setAIturn(!player1.isAIturn());
        PlayComp.GridVizualwithHuman(Grid1, Grid2, player1, player1_enemy, player2, player2_enemy, spisok1, spisok2);

        exit.setOnAction(actionEvent1 -> {
            onClickExit(actionEvent1);
        });

        restart.setOnAction(actionEvent -> {
            try {
                onClickRestart(actionEvent);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        choiceCoil.setOnAction(actionEvent1 -> {
            if (!ButtonOnPlay.isActive()){
                player1.getButtonplay().stream()
                        .filter(x->x.getX()==xplayer)
                        .filter(y->y.getY()==yplayer)
                        .forEach(x->x.setDisable(true));
                onChoiceCoil(actionEvent1);
            }
        });
    }
    @FXML
    private void onChoiceCoil(ActionEvent actionEvent1) {
        while(true){
            if (player1.isAIturn()) {
                if (player2.getField()[xplayer-1][yplayer-1]==1){
                   CheckFireUser(xplayer-1,yplayer-1,player1,player1_enemy,player2,messageWindow);
                   PlayComp.SpisokVizual(player1, spisok1);
                   ButtonOnPlay.setActive(true);
                   return;
                } else {
                    player1.setAIturn(false);
                }
            } else {
                PlayComp.LetsPlay(player2, player2_enemy, player1, messageWindow);
                if (textship.equalsIgnoreCase("попал")
                        ||textship.equalsIgnoreCase("убил")){
                    PlayComp.GridReset(Grid1);
                    PlayComp.GridOn(Grid1, player1, player2_enemy);
                    PlayComp.SpisokVizual(player2, spisok2);
                } else {
                    player1.setAIturn(true);
                    ButtonOnPlay.setActive(true);
                    PlayComp.GridReset(Grid1);
                    PlayComp.GridOn(Grid1, player1, player2_enemy);
                    return;
                }
        }
//        if (player1.getShipList().stream().filter(z -> z.getHealth() != 0).count() != 0) {
//
//            exit.setVisible(true);
//            exit.setText("Поздравляем " + player1.getName() + " победил\n" +
//                    "нажмите на кнопку чтобы закончить");
//        } else if (player2.getShipList().stream().filter(z -> z.getHealth() != 0).count() != 0) {
//            exit.setVisible(true);
//            exit.setText("Поздравляем " + player2.getName() + " победил\n" +
//                    "нажмите на кнопку чтобы закончить");
//            }
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
    private void onClickExit (ActionEvent actionEvent){
        Stage stage = Start.getPStage();
        stage.close();
    }

//    public static void LetsBattle(Anketa player1,Anketa player1_enemy,Anketa player2, Anketa player2_enemy,
//                                  int x, int y) {
//
//        while(true){
//            if (player1.isAIturn()) {
//                if (player2.getField()[x][y]==1){
//                   CheckFireUser(x,y,player1,player1_enemy,player2,messageWindowNew);
//                } else {
//                    player1.setAIturn(false);
//                }
//            } else {
//                PlayComp.LetsPlay(player2, player2_enemy, player1, messageWindowNew);
//                if (PlayComp.ShipChecked(x, y, player2_enemy,player1).equalsIgnoreCase("попал")
//                        ||PlayComp.ShipChecked(x, y, player2_enemy,player1).equalsIgnoreCase("убил")){
//
//            }
//        }
//        if (player1.getShipList().stream().filter(z -> z.getHealth() != 0).count() != 0) {
//
//            exitNew.setVisible(true);
//            exitNew.setText("Поздравляем " + player1.getName() + " победил\n" +
//                    "нажмите на кнопку чтобы закончить");
//        } else if (player2.getShipList().stream().filter(z -> z.getHealth() != 0).count() != 0) {
//            exitNew.setVisible(true);
//            exitNew.setText("Поздравляем " + player2.getName() + " победил\n" +
//                    "нажмите на кнопку чтобы закончить");
//            }
//        }
//    }

    @FXML
    public static void CheckFireUser(int x,int y,Anketa p1, Anketa p1_enemy, Anketa p2,TextArea messageWindow){
        String[] XA={"A","B","C","D","E","F","G","H","I","G"};
        String text;
        if (p2.getField()[x][y] == 1) {
            p1_enemy.getField()[x][y] = 3;
            text = PlayComp.ShipChecked(x, y, p1_enemy,p2);
            if (text.equals("попал")){
            } else {
                p1.setAILogicOn(false);
                p1.getAILogic().clear();
            }
        } else {
            p1_enemy.getField()[x][y] = 2;
            text = "мимо";
        }
        messageWindow.setText(p1.getName()+" выбрал координаты " + XA[x] +
                (y+1) + "-" + text);
    }

    public static void setXplayer(int xplayer) {
        Controller_CompVsHuman.xplayer = xplayer;
    }

    public static void setYplayer(int yplayer) {
        Controller_CompVsHuman.yplayer = yplayer;
    }

    public static void setTextship(String textship) {
        Controller_CompVsHuman.textship = textship;
    }
}
