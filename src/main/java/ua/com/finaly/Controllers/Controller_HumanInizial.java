package ua.com.finaly.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import ua.com.finaly.Anketa;
import ua.com.finaly.Initialization;
import ua.com.finaly.Player.ButtonOnGrid;
import ua.com.finaly.Player.CreateShip;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static javafx.scene.paint.Color.BLACK;

public class Controller_HumanInizial implements Initializable {

    private static String ship="⛵";
    private static String shipEmpty="\uD83D\uDFAB";

    @FXML
    private GridPane Grid1;

    @FXML
    private Button buttonname;

    @FXML
    private Button ready;

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
        textmanual.setText("Укадите расположение кораблей и нажимайте 'Готово'\n" +
                "Однопалубные-4шт\n" +
                "Двухпалубные-3шт\n" +
                "Трёхпалубные-2шт\n" +
                "Четырёхпалубные-1шт");
        Initialization.GridStart(player1,Grid1);

        buttonname.setOnAction(this::onClickName);

        ready.setOnAction(this::onClickReady);
    }

    @FXML
    private void onClickReady(ActionEvent actionEvent) {
        List<ButtonOnGrid> buttonship=player1.getButtonplayer().stream().
                filter(x->x.getText().equals(ship)).filter(x->x.getTextFill().equals(BLACK)).collect(Collectors.toList());
        CreateShip.Create(buttonship,player1);
    }

    @FXML
    private void onClickName(ActionEvent actionEvent) {
        name.setText(nameEnter.getText());
    }

}
