package client.Controllers;

import client.network.ClientManager;
import client.event.Event;
import client.shared.MainResponse;
import client.shared.Response;
import client.shared.WatchResponse;
import client.util.Manager;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

// Bia avalesh ship haro begir o state avalie


public class Watch {
    public Label whichTurn;
    public Label time;
    int playerNum;
    int timer;
    int turn;
    ClientManager clientManager;
    @FXML
    private GridPane myBoard;

    @FXML
    private GridPane oppBoard;

    int[][] myShips;
    int[][] lmyBoard;
    int[][] loppBoard;
    int[][] oppShips;
    public void initialize() throws IOException {
        this.clientManager = Manager.getClientManager();
        clientManager.sendClicked("WATCH");
        clientManager.sendClicked(Manager.getSpectateName());
        Gson gson = new Gson();
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    String string = clientManager.read();
                    WatchResponse response = gson.fromJson(clientManager.read(), WatchResponse.class);
                    timer = response.getTime();
                    myShips = response.getMyShips();
                    oppShips = response.getOppShips();
                    lmyBoard = response.getMyMatrix();
                    loppBoard = response.getOppMatrix();
                    turn = response.turn;
                    loadData();
                    Thread.sleep(500 );

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void loadData() throws IOException {
        Platform.runLater(() -> {
            if (true) {
                myBoard.getChildren().clear();
                oppBoard.getChildren().clear();
                time.setText(String.valueOf(timer));
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("cell.fxml"));
                        AnchorPane cell = null;
                        try {
                            cell = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        cell.setMaxHeight(30);
                        cell.setMaxWidth(30);
                        cell.setLayoutX(20);
                        myBoard.add(cell, i, j);
                        if (myShips[i][j] == 1) {
                            cell.setStyle("-fx-background-color: #0f0807;");
                        }
                        if (lmyBoard[i][j] == 1) {
                            cell.setStyle("-fx-background-color: #fc960f;");
                        }
                        if (lmyBoard[i][j] == 2) {
                            cell.setStyle("-fx-background-color: #fc523f;");
                        }
                        GridPane.setMargin(cell, new Insets(3));
                    }
                }
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("cell.fxml"));
                        AnchorPane cell = null;
                        try {
                            cell = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        cell.setMaxHeight(30);
                        cell.setMaxWidth(30);
                        cell.setLayoutX(20);
                        if (oppShips[i][j] == 1) {
                            cell.setStyle("-fx-background-color: #0f0807;");
                        }
                        if (loppBoard[i][j] == 2) {
                            cell.setStyle("-fx-background-color: #fc523f;");
                        }
                        if (loppBoard[i][j] == 1) {
                            cell.setStyle("-fx-background-color: #fc960f;");
                        }
                        oppBoard.add(cell, i, j);
                        GridPane.setMargin(cell, new Insets(3));
                    }
                }
            }
        });
    }

}

