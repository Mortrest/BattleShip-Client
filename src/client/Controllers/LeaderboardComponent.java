package client.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class LeaderboardComponent {

    @FXML
    private Label name;

    @FXML
    private Circle online;

    @FXML
    private Circle offline;


    public void setName(String str){
        name.setText(str);
    }

    public void setOffline(boolean bool){
        if (bool){
            offline.setVisible(false);
        } else {
            online.setVisible(false);
        }
    }
}
