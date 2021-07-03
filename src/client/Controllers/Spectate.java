package client.Controllers;

import client.network.ClientManager;
import client.shared.SpectateResponse;
import client.util.ChangeScene;
import client.util.Manager;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.util.LinkedList;

public class Spectate {
    ClientManager clientManager;
    LinkedList<SpectateResponse> sr;
    @FXML
    private GridPane grid;
    @FXML
    private AnchorPane component;
    @FXML
    private Label name;
    @FXML
    private Label destroyed;
    @FXML
    private Label turns;

    public void initialize() throws IOException {
        clientManager = Manager.getClientManager();
        clientManager.sendClicked("SPECTATE");
        String str = clientManager.read();
        Gson gson = new Gson();
        sr = new LinkedList<>();
        for (int i = 0; i < Integer.parseInt(str); i++) {
            String data = clientManager.read();
            sr.add(gson.fromJson(data,SpectateResponse.class));
        }
        loadData();
    }

    public void loadData() throws IOException {
        grid.getChildren().clear();
        for (int i = 0; i < sr.size(); i++) {
            SpectateResponse spectateResponse = sr.get(i);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("spectateComponent.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            SpectateComponent itemController = fxmlLoader.getController();
            itemController.setName(spectateResponse.getPlayer1() + " - " + spectateResponse.getPlayer2());
            itemController.setDestroyed(spectateResponse.getDestroyed());
            itemController.setTurns(spectateResponse.getTurns());
            int finalI = i;
            anchorPane.setOnMouseClicked(e -> {
                        try {
                            click(sr.get(finalI));
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    });
            grid.add(anchorPane, 1, grid.getRowCount() + 1);
            grid.setLayoutX(-20);
            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }

    private void click(SpectateResponse spectateComponent) throws IOException {
        Manager.setSpectateName(spectateComponent.getPlayer1());
        new ChangeScene("../FXML/watch.fxml", grid);
    }
}
