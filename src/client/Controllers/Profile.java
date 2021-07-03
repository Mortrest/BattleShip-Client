package client.Controllers;

import client.network.ClientManager;
import client.util.ChangeScene;
import client.util.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class Profile {
    ClientManager clientManager;
    @FXML
    private Label username;

    @FXML
    private Label winsNum;

    @FXML
    private Label lossNum;

    @FXML
    private Label points;


    public void initialize() throws IOException {
        clientManager = Manager.getClientManager();
        clientManager.sendClicked("PROFILE");
        username.setText(clientManager.read());
        winsNum.setText(clientManager.read());
        lossNum.setText(clientManager.read());
        points.setText(clientManager.read());
    }

    public void back() throws IOException {
        new ChangeScene("../FXML/menu.fxml", points);
    }
}
