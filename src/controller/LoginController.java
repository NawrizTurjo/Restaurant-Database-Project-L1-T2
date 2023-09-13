package controller;
import java.io.IOException;

import app.ClientCustomer;
import app.ClientRestaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utlilities.LoginDTO;


public class LoginController {

    private ClientRestaurant main;
    // private ClientCustomer app;
    

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    // @FXML
    // void loginAction(ActionEvent event) {
    //     String userName = userText.getText();
    //     String password = passwordText.getText();
    //     if (userName.equals("admin") && password.equals("123")) {
    //         try {
    //             app.showHomePage(userName);
    //         } catch (Exception e) {
    //             System.out.println(e);
    //         }
    //     } else {
    //         app.showAlert();
    //     }
    // }

    @FXML
    void loginAction(ActionEvent event) {
        String userName = userText.getText();
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        
        loginDTO.setPassword(password);
        // loginDTO.setNetworkUtil(main.getNetworkUtil());
        try {
            // System.out.println("Inside loginAction");
            // main.getNetworkUtil().write(userName);
            // main.getNetworkUtil().write(password);
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    // public void setMain(ClientCustomer app) {
    //     this.app = app;
    // }

    public void setMain(ClientRestaurant main) {
        this.main = main;
    }


}
