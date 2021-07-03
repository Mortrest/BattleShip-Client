package client.Controllers;

import client.event.Event;
import client.network.ClientManager;
import client.shared.MainResponse;
import client.shared.Response;
import client.util.ChangeScene;
import client.util.Manager;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class MainGameController {
    public Label whichTurn;
    public Label time;
    public Button change;
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

    public void initialize() throws IOException {
        this.clientManager = Manager.getClientManager();
        clientManager.sendClicked("GAME");
        playerNum = Integer.parseInt(clientManager.read());
        Gson gson = new Gson();
        String str = clientManager.read();
        Response responses = gson.fromJson(str, Response.class);
        loppBoard = responses.getOppMatrix();
        lmyBoard = responses.getMyMatrix();
        myShips = responses.getMyShips();
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    clientManager.sendClicked("2");
                    MainResponse response = gson.fromJson(clientManager.read(), MainResponse.class);
                    timer = response.getTime();
                    turn = response.getTurn();
                    if (response.getIsFinished()){
                        Platform.runLater(() ->{
                            try {
                                new ChangeScene("../FXML/menu.fxml", myBoard);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        break;
                    }
                    loadData();
                    Thread.sleep(400 );
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
                if (turn >= 2){
                    change.setVisible(false);
                }
                myBoard.getChildren().clear();
                oppBoard.getChildren().clear();
                time.setText(String.valueOf(timer));
                if (turn == playerNum) {
                    whichTurn.setText("Your Turn");
                } else {
                    whichTurn.setText("Wait...");
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
                        int finalI = i;
                        int finalJ = j;
                        cell.setOnMouseClicked(e -> {
                            try {
                                // Inja bayad type ro havaset bashe

                                click(2, finalI, finalJ);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        });
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

    private synchronized void click(int type, int x, int y) throws IOException {
        if (turn == playerNum) {
            int[] xoy = {x, y};
            Event event = new Event(type, xoy,Manager.getAuthToken());
            clientManager.sendClicked(event.toJson());
            String str = clientManager.read();
            Gson gson = new Gson();
            Response response = gson.fromJson(str, Response.class);
            loppBoard = response.getOppMatrix();
            lmyBoard = response.getMyMatrix();
        }
    }

    public void change() throws IOException {
        clientManager.sendClicked("CHANGE");
        String str = clientManager.read();
        Gson gson = new Gson();
        Response response = gson.fromJson(str, Response.class);
        loppBoard = response.getOppMatrix();
        lmyBoard = response.getMyMatrix();
        myShips = response.getMyShips();
    }
}

