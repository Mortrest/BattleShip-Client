package client;

import client.util.Manager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Platform.runLater(()->{
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("./FXML/signIn.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.setTitle("Battle Ship");
            primaryStage.setScene(new Scene(root, 954, 703));
            primaryStage.show();

        });
    }

    public static void main(String[] args) throws IOException {
        Manager manager = new Manager();
        launch(args);
    }
}
