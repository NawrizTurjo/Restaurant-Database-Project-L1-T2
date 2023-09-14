package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import resources.Food;
import utlilities.FoodUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import app.ClientCustomer;

public class OrderFoodController implements Initializable {

    @FXML
    private ListView<Food> listView;

    @FXML
    private ListView<Food> placedOrder;

    private ObservableList<Food> items = FXCollections.observableArrayList();
    private ObservableList<Food> orderedItems = FXCollections.observableArrayList();

    public void setFoodItems(List<Food> foodItems) {
        items.addAll(foodItems);
    }

    @FXML
    private Button confirmButton;

    @FXML
    void confirm(ActionEvent event) throws IOException {
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.initModality(Modality.APPLICATION_MODAL);
        if (a.showAndWait().get() == ButtonType.OK) {
            ObservableList<Food> orderedItems = placedOrder.getItems();
            for (Food item : orderedItems) {
                System.out.println(item);
                FoodUtil foodUtil = new FoodUtil(item,main.getUsername());
                System.out.println(foodUtil);
                main.getNetworkUtil().write(foodUtil);
            }
            stage.close();
        } else {
            a.close();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(items);
        placedOrder.setItems(orderedItems);
        listView.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
            @Override
            public ListCell<Food> call(ListView<Food> param) {
                return new ListCell<Food>() {
                    private final Button addButton = new Button("Add");

                    {
                        addButton.setOnAction((ActionEvent event) -> {
                            Food item = getItem();
                            orderedItems.add(item);
                            System.out.println("Added: " + item);
                        });
                    }

                    @Override
                    protected void updateItem(Food item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText("("+item.getRestaurantId()+") "+item.getFoodName()+ " $" + item.getPrice());
                            setGraphic(addButton);
                        }
                    }
                };
            }
        });

        placedOrder.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
            @Override
            public ListCell<Food> call(ListView<Food> param) {
                return new ListCell<Food>() {
                    private final Button removeButton = new Button("Remove");

                    {
                        removeButton.setOnAction((ActionEvent event) -> {
                            Food item = getItem();
                            orderedItems.remove(item);
                            System.out.println("Removed: " + item);
                        });
                    }

                    @Override
                    protected void updateItem(Food item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.getFoodName());
                            setGraphic(removeButton);
                        }
                    }
                };
            }
        });
    }

    private ClientCustomer main;

    public void setMain(ClientCustomer main) {
        this.main = main;
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
