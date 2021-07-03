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

public class SignIn {

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

    public SignIn() throws IOException {
    }


    public void initialize() throws IOException {
        error.setVisible(false);
        this.clientManager = Manager.getClientManager();
    }


    public void signIn() throws IOException {
//        System.out.println("yo");
//        clientManager.sendClicked("0");
        if (!usernameField.getText().equals("") && !passField.getText().equals("")) {
            clientManager.sendClicked("SIGN_IN");
            clientManager.sendClicked(usernameField.getText());
            clientManager.sendClicked(passField.getText());
            String res = clientManager.read();
            if (res.equals("Signed In")) {
                Manager.setAuthToken(clientManager.read());
                new ChangeScene("../FXML/menu.fxml", signIn);
            } else {
                error.setVisible(true);
            }
        }
    }

    public void signUp() throws IOException {
        new ChangeScene("../FXML/signUp.fxml", signIn);
    }
}