package ua.com.finaly.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ua.com.finaly.Start;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_Start implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        button1.setOnAction(actionEvent -> {
            try {
                onClickHumanVsHuman(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        button2.setOnAction(actionEvent -> {
            try {
                onClickCompVsHuman(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button3.setOnAction(actionEvent -> {
            try {
                onClickCompVsComp(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void onClickCompVsComp(ActionEvent actionEvent) throws IOException {
        Stage stage = Start.getPStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("CompsvsComps.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 700.0D, 500.0D);
        stage.setScene(scene);
    }

    @FXML
    private void onClickCompVsHuman(ActionEvent actionEvent) throws IOException {
        Stage stage = Start.getPStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("HumanInizial.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 700.0D, 500.0D);
        stage.setScene(scene);
    }

    @FXML
    private void onClickHumanVsHuman(ActionEvent actionEvent) throws IOException {
        Stage stage = Start.getPStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("HumanInizialPlayer1_Player2.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 700.0D, 500.0D);
        stage.setScene(scene);
    }
}
