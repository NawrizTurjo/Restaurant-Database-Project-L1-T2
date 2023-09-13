// package app;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.Initializable;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Alert.AlertType;
// import javafx.scene.control.Button;
// import javafx.scene.control.ButtonType;
// import javafx.scene.control.ListView;
// import javafx.stage.Modality;
// import javafx.stage.Stage;
// import javafx.scene.control.ListCell;
// import javafx.util.Callback;

// import java.io.IOException;
// import java.net.URL;
// import java.util.List;
// import java.util.ResourceBundle;

// public class OrderFood implements Initializable {

//     @FXML
//     private ListView<Food> listView;

//     @FXML
//     private ListView<Food> placedOrder;

//     private ObservableList<Food> items = FXCollections.observableArrayList();
//     private ObservableList<Food> orderedItems = FXCollections.observableArrayList();
//     private ObservableList<String> restaurantNames = FXCollections.observableArrayList();

//     public void setFoodItems(List<Food> foodItems) {
//         items.addAll(foodItems);
//     }

//     @FXML
//     private Button confirmButton;

//     public void setRestaurantNames(List<Restaurant> restaurantNames) {
//         for (Restaurant r : restaurantNames) {
//             this.restaurantNames.add(r.getName());
//         }
//     }

//     @FXML
//     void confirm(ActionEvent event) throws IOException {
//         // Send the orderedItems to the server
//         Alert a = new Alert(AlertType.CONFIRMATION);
//         a.setTitle("Confirmation");
//         a.initModality(Modality.APPLICATION_MODAL);
//         if (a.showAndWait().get() == ButtonType.OK) {
//             ObservableList<Food> orderedItems = placedOrder.getItems();
//             for (Food item : orderedItems) {
//                 System.out.println(item);
//                 main.getNetworkUtil().write(item);
//             }
//             stage.close();
//         } else {
//             a.close();
//         }

//     }

//     // private boolean isOrdered = false;

//     @Override
//     public void initialize(URL location, ResourceBundle resources) {
//         // restaurants.setItems(restaurantNames);
//         // listView.setItems(items);
//         placedOrder.setItems(orderedItems);
//         // confirmButton.setDisable(true);

//         // Customize the list cell in listView to include an "Add" button
//         listView.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
//             @Override
//             public ListCell<Food> call(ListView<Food> param) {
//                 return new ListCell<Food>() {
//                     private final Button addButton = new Button("Add");
//                     {
//                         addButton.setOnAction((ActionEvent event) -> {
//                             // if (!isOrdered) {
//                                 Food item = getItem();
//                                 // Add the item to placedOrder
//                                 orderedItems.add(item);
//                                 System.out.println("Added: " + item);
//                                 // confirmButton.setDisable(false);
//                                 // restaurants.setDisable(false);
//                             // }
//                         });
//                     }

//                     @Override
//                     protected void updateItem(Food item, boolean empty) {
//                         super.updateItem(item, empty);

//                         if (empty || item == null) {
//                             setText(null);
//                             setGraphic(null);
//                         } else {
//                             setText("(" + item.getRestaurantId() + ") " + item.getFoodName() + " $" + item.getPrice());
//                             setGraphic(addButton);
//                         }
//                     }
//                 };
//             }
//         });

//         // Customize the list cell in placedOrder to include a "Remove" button
//         placedOrder.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
//             @Override
//             public ListCell<Food> call(ListView<Food> param) {
//                 return new ListCell<Food>() {
//                     private final Button removeButton = new Button("Remove");

//                     {
//                         removeButton.setOnAction((ActionEvent event) -> {
//                             Food item = getItem();
//                             // Remove the item from placedOrder
//                             orderedItems.remove(item);
//                             System.out.println("Removed: " + item);
//                             if (orderedItems.isEmpty()) {
//                                 // restaurants.setDisable(false);
//                             }
//                         });
//                     }

//                     @Override
//                     protected void updateItem(Food item, boolean empty) {
//                         super.updateItem(item, empty);

//                         if (empty || item == null) {
//                             setText(null);
//                             setGraphic(null);
//                         } else {
//                             setText(item.getFoodName());
//                             setGraphic(removeButton);
//                         }
//                     }
//                 };
//             }
//         });

//         // restaurants.setOnAction(e -> {
//         //     String selectedRestaurant = restaurants.getSelectionModel().getSelectedItem();
//         //     System.out.println(selectedRestaurant);
//         //     ObservableList<Food> filteredItems = FXCollections.observableArrayList();
//         //     for (Food item : items) {
//         //         if (item.getRestaurantName().equals(selectedRestaurant)) {
//         //             filteredItems.add(item);
//         //         }
//         //     }
//         //     listView.setItems(filteredItems);

//         //     if (orderedItems.isEmpty()) {
//                 // confirmButton.setDisable(true);
//         //         isOrdered = false;
//                 // restaurants.setDisable(true);
//         //     } else {
//                 // confirmButton.setDisable(false);
//         //         isOrdered = true;
//                 // restaurants.setDisable(false);
//         //     }
//         // });
//     }

//     private App main;

//     public void setMain(App main) {
//         this.main = main;
//     }

//     private Stage stage;

//     public void setStage(Stage stage) {
//         this.stage = stage;
//     }

// }


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
        // Send the orderedItems to the server
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setTitle("Confirmation");
        a.initModality(Modality.APPLICATION_MODAL);
        if (a.showAndWait().get() == ButtonType.OK) {
            ObservableList<Food> orderedItems = placedOrder.getItems();
            for (Food item : orderedItems) {
                System.out.println(item);
                main.getNetworkUtil().write(item);
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

        // Customize the list cell in listView to include an "Add" button
        listView.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
            @Override
            public ListCell<Food> call(ListView<Food> param) {
                return new ListCell<Food>() {
                    private final Button addButton = new Button("Add");

                    {
                        addButton.setOnAction((ActionEvent event) -> {
                            Food item = getItem();
                            // Add the item to placedOrder
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

        // Customize the list cell in placedOrder to include a "Remove" button
        placedOrder.setCellFactory(new Callback<ListView<Food>, ListCell<Food>>() {
            @Override
            public ListCell<Food> call(ListView<Food> param) {
                return new ListCell<Food>() {
                    private final Button removeButton = new Button("Remove");

                    {
                        removeButton.setOnAction((ActionEvent event) -> {
                            Food item = getItem();
                            // Remove the item from placedOrder
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
