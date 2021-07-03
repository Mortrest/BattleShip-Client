package client.Controllers;


import client.network.ClientManager;
import client.util.ChangeScene;
import client.util.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Menu {

    @FXML
    private Button start;

    @FXML
    private Button spectate;

    @FXML
    private Button profile;

    @FXML
    private Button leaderboard;


    @FXML
    void leaderboard() throws IOException {
        new ChangeScene("../FXML/leaderboard.fxml", start);

    }

    @FXML
    void profile() throws IOException {
        new ChangeScene("../FXML/profile.fxml", start);
    }

    @FXML
    void spectate() throws IOException {
        new ChangeScene("../FXML/spectate.fxml", start);
    }

    @FXML
    void start() throws IOException {
        new ChangeScene("../FXML/waiting.fxml", start);
    }

}
