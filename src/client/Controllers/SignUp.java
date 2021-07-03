package client.Controllers;

import client.network.ClientManager;
import client.util.ChangeScene;
import client.util.Manager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUp {

    public Label error;
    ClientManager clientManager;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signUp;

    @FXML
    private Button signIn;

    public SignUp() throws IOException {
    }


    public void initialize() throws IOException {
        error.setVisible(false);
        this.clientManager = Manager.getClientManager();
//        clientManager.start();
    }


    public void signUp() throws IOException {
        if (!usernameField.getText().equals("") && !passField.getText().equals("")) {
            clientManager.sendClicked("SIGN_UP");
            clientManager.sendClicked(usernameField.getText());
            clientManager.sendClicked(passField.getText());
            String res = clientManager.read();
            if (res.equals("Signed Up")){
                new ChangeScene("../FXML/signIn.fxml", signIn);
            } else {
                error.setVisible(true);
            }
        }
    }


    public void signIn() throws IOException {
        new ChangeScene("../FXML/signIn.fxml", signIn);
    }
}

