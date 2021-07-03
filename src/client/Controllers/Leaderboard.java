package client.Controllers;

import client.network.ClientManager;
import client.shared.User;
import client.util.ChangeScene;
import client.util.LoadComponent;
import client.util.Manager;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.LinkedList;

public class Leaderboard {
    ClientManager clientManager;
    LinkedList<User> users;
    @FXML
    private Label username;

    @FXML
    private GridPane grid;

    public void initialize() throws IOException {
        clientManager = Manager.getClientManager();
        clientManager.sendClicked("LEADERBOARD");
        int size = Integer.parseInt(clientManager.read());
        Gson gson = new Gson();
        users = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            String str = clientManager.read();
            users.add(gson.fromJson(str,User.class));
        }
        loadData();
    }

    private void loadData() throws IOException {
        for (int i = 0; i < users.size(); i++) {
            User us = users.get(i);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("leaderboardComponent.fxml"));
            AnchorPane cell = null;
            cell = fxmlLoader.load();
            LeaderboardComponent lc = fxmlLoader.getController();
            lc.setOffline(us.isOnline());
            lc.setName(us.getUsername() + " - " + us.getWins());
            grid.add(cell, 1, grid.getRowCount() + 1);
            GridPane.setMargin(cell, new Insets(3));
        }
    }

    public void back() throws IOException {
        new ChangeScene("../FXML/menu.fxml", grid);
    }
}
