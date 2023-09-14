package controller;

import java.text.DecimalFormat;
import java.util.List;

import app.ClientCustomer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import resources.Food;
import resources.Restaurant;

public class OrderCustomerSideController {

    @FXML
    private TableColumn<Food, String> orderCategory;

    @FXML
    private TableColumn<Food, String> orderFood;

    @FXML
    private TableColumn<Food, Double> orderPrice;

    @FXML
    private TableColumn<Food, Restaurant> orderRestaurant;

    @FXML
    private TableView<Food> orderTable;

    @FXML
    private Label payLabel;

    private ClientCustomer main;

    public void setMain(ClientCustomer main) {
        this.main = main;
    }

    @FXML
    private Button backButton;


    @FXML
    void back(ActionEvent event) throws Exception {
        this.stage.close();
    }

    private ObservableList<Food> foodList = FXCollections.observableArrayList();


    private Stage stage;

    public void updateFoodOrderList(Food food) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                foodList.add(food);
                ObservableList<Food> foodObservableList = FXCollections.observableArrayList(main.getFoodList());

                orderRestaurant.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
                orderCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
                orderFood.setCellValueFactory(new PropertyValueFactory<>("foodName"));
                orderPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

                orderTable.setItems(foodObservableList);
                DecimalFormat df = new DecimalFormat("#.##");
                payLabel.setText("Total Payable: " + df.format(main.getPrice()));
            }
        });
    }

    public void init(List<Food> foods) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                foodList.addAll(foods);
                orderRestaurant.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
                orderCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
                orderFood.setCellValueFactory(new PropertyValueFactory<>("foodName"));
                orderPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

                orderTable.setItems(foodList);
                payLabel.setText("Total Payable: " + main.getPrice());
            }
        });
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}

