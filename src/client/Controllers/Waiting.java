package client.Controllers;

import client.network.ClientManager;
import client.util.ChangeScene;
import client.util.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class Waiting {
    public Button butt;
    ClientManager clientManager;
    @FXML
    private Label username;

    @FXML
    private Label winsNum;

    @FXML
    private Label lossNum;

    @FXML
    private Label points;


    public void initialize() throws IOException, InterruptedException {
        clientManager = Manager.getClientManager();
        clientManager.sendClicked("WAIT");
        System.out.println("residiiiim");

    }

    public void go() throws IOException {
        System.out.println("huraaaa");
        clientManager.sendClicked("GO");
        String str = clientManager.read();
        System.out.println(str);
        if (str.equals("YES")) {
            new ChangeScene("../FXML/mainGame.fxml", butt);
        }
        System.out.println(str);

    }

    public void back() throws IOException {
        new ChangeScene("../FXML/menu.fxml", butt);
    }
}
