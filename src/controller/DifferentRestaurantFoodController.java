package controller;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DifferentRestaurantFoodController {

    private Stage stage;
    @FXML
    private Button closeButton;

    @FXML
    private ListView<String> listView;

    @FXML
    void close(ActionEvent event) {
        Alert a = new Alert(AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            a.initModality(Modality.APPLICATION_MODAL);
            if(a.showAndWait().get() == ButtonType.OK){
                stage.close();
            }
            else{
                a.close();
            }
    }

    void setStage(Stage stage){
        this.stage = stage;
    }

    public void init(List<String> differentSearchItems){
        ObservableList<String> items = FXCollections.observableArrayList(differentSearchItems);
        listView.setItems(items);
        
    }

}
