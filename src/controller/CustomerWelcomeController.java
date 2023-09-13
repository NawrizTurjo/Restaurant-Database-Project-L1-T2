package controller;
import app.ClientCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerWelcomeController {

    private ClientCustomer main;
    public void setMain(ClientCustomer main) {
        this.main = main;
    }
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private Label myLabel;

    @FXML
    private TextField nameCustomer;

    @FXML
    private Button nextButton;

    @FXML
    void nextPage(ActionEvent event) throws Exception {
        String name = nameCustomer.getText();
        main.userName = name;
        System.out.println(name);
        main.getNetworkUtil().write(name);
        main.showHomePage(name);
        //close this stage
        stage.close();
    }

}
