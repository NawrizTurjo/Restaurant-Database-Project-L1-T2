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

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import resources.Food;

public class CustomerFood {

    private Stage stage;
    @FXML
    private TableColumn<Food, String> catCol;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<Food, String> idCol;

    @FXML
    private TableColumn<Food, String> nameCol;

    @FXML
    private TableColumn<Food, Double> priceCol;

    @FXML
    private TableView<Food> foodTable;

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

    public void init(List<Food> foods){
        ObservableList<Food> foodList = FXCollections.observableArrayList(foods);

        idCol.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        foodTable.setItems(foodList);

    }

}
