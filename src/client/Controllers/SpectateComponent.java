package client.Controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SpectateComponent {

    @FXML
    private Label name;

    @FXML
    private Label destroyed;

    @FXML
    private Label turns;



    public void setName(String name) {
        this.name.setText(name);
    }



    public void setDestroyed(int destroyed) {
        this.destroyed.setText(String.valueOf(destroyed));
    }


    public void setTurns(int turns) {
        this.turns.setText(String.valueOf(turns));
    }
}
