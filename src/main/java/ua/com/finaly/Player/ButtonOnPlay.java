package ua.com.finaly.Player;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import ua.com.finaly.Controllers.Controller_CompVsHuman;
import ua.com.finaly.Controllers.Controller_HumanVsHuman;

public class ButtonOnPlay extends Button {

    private int x;
    private int y;
    private static boolean active = true;

    public ButtonOnPlay(int x, int y) {
        this.x = x;
        this.y = y;
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
                if (active) {
                    onClick(actionEvent);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        this.setPadding(new Insets(1, 1, 1, 1));
    }

    private void onClick(ActionEvent actionEvent) throws InterruptedException {
        this.setText("•");
//        this.setDisable(true);
        active = false;
        Controller_CompVsHuman.setXplayer(x);
        Controller_CompVsHuman.setYplayer(y);
        Controller_HumanVsHuman.setXplayer(x);
        Controller_HumanVsHuman.setYplayer(y);
    }

    public static boolean isActive() {
        return active;
    }

    public static void setActive(boolean active) {
        ButtonOnPlay.active = active;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

