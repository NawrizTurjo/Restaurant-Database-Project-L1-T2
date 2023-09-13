package controller;

import java.io.IOException;

import app.ClientCustomer;
import app.ClientRestaurant;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
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
    // String userName = userText.getText();
    // String password = passwordText.getText();
    // if (userName.equals("admin") && password.equals("123")) {
    // try {
    // app.showHomePage(userName);
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // } else {
    // app.showAlert();
    // }
    // }

    // @FXML
    // private ImageView image0;

    // @FXML
    // private ImageView image1;

    // @FXML
    // private ImageView image2;

    // @FXML
    // private ImageView image3;

    // @FXML
    // private ImageView image4;

    // @FXML
    // private ImageView image5;

    
    // private void initializeImageAnimation() {
    //     ImageView[] imageView = { image0, image1, image2, image3, image4, image5 };
    //     TranslateTransition[] transitions = new TranslateTransition[6];
    
        
    //     for(int i=0;i<3;i++)
    //     {
    //         transitions[i] = createTransition(imageView[i], imageView[3],1);
    //         transitions[i].play();
    //     }

    //     for(int i=3;i<6;i++)
    //     {
    //         transitions[i] = createTransition(imageView[i], imageView[5],-1);
    //         transitions[i].play();
    //     }
    // }
    
    // private TranslateTransition createTransition(ImageView imageView, ImageView imageFlag,int i) {
    //     TranslateTransition transition = new TranslateTransition(Duration.seconds(10), imageView);
    //     transition.setFromX( (i*-imageFlag.getFitWidth()));
    //     transition.setToX( (i*imageFlag.getFitWidth()));
    //     transition.setOnFinished(event -> {
    //         // imageView.setTranslateX(i*-imageView.getFitWidth());
    //         transition.setFromX(i*-imageFlag.getFitWidth());
    //         transition.setToX(i*imageFlag.getFitWidth());
    //         transition.play();
    //     });
    //     return transition;
    // }
    
    // @FXML
    // private void initialize() {
    //     initializeImageAnimation();
    // }

    // private void initializeImageAnimation() {
    //     ImageView[] imageView = { image0, image1, image2, image3, image4, image5 };
    //     TranslateTransition[] transitions = new TranslateTransition[6];
    
    //     for (int i = 0; i < 3; i++) {
    //         transitions[i] = createTransition(imageView[i], imageView[3], 1);
    //         transitions[i].play();
    //     }
    
    //     for (int i = 3; i < 6; i++) {
    //         transitions[i] = createTransition(imageView[i], imageView[5], -1);
    //         transitions[i].play();
    //     }
    // }
    
    // private TranslateTransition createTransition(ImageView imageView, ImageView imageFlag, int direction) {
    //     TranslateTransition transition = new TranslateTransition(Duration.seconds(10), imageView);
    //     double distance = direction * imageFlag.getFitWidth();
    //     transition.setFromX(-distance);
    //     transition.setToX(distance);
        
    //     transition.setOnFinished(event -> {
    //         transition.setFromX(-distance);
    //         transition.setToX(distance);
    //         transition.play();
    //     });
    
    //     return transition;
    // }
    
    // @FXML
    // private void initialize() {
    //     initializeImageAnimation();
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
    // this.app = app;
    // }

    public void setMain(ClientRestaurant main) {
        this.main = main;
    }

}
