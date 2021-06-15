package ua.com.finaly;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Start extends Application {
    private static Stage PStage;

    public static void main(String[] args) throws InterruptedException {
        Application.launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Start.PStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("StartChoice.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 400.0D, 300.0D);
        stage.setScene(scene);
        stage.show();

    }

    public static Stage getPStage() {
        return PStage;
    }

    public static void setPStage(Stage PStage) {
        Start.PStage = PStage;
    }
}
