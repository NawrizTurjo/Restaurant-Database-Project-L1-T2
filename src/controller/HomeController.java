package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.ClientCustomer;
import app.ClientRestaurant;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.Food;
import resources.Restaurant;
import utlilities.FoodPassUtil;
import utlilities.FoodUtil;
import utlilities.FoodUtilObject;
import utlilities.LoginDTO;

public class HomeController {

    private ClientRestaurant main;
    LoginDTO loginDTO;

    @FXML
    private Label attributes;

    // @FXML
    // private Button button;

    @FXML
    private TableColumn<Food, String> catCol;

    @FXML
    private ImageView image;

    @FXML
    private TableView<Food> menuTable;

    @FXML
    private TableColumn<Food, String> nameCol;

    @FXML
    private TableColumn<FoodUtilObject, String> orderCol;

    @FXML
    private TableColumn<FoodUtilObject, String> orderName;

    @FXML
    private TableColumn<FoodUtilObject, Double> orderPrice;

    @FXML
    private TableView<FoodUtilObject> orderTable;

    @FXML
    private TableColumn<Food, Double> priceCol;

    @FXML
    private TableColumn<FoodUtilObject, Button> confirmButton;

    @FXML
    private TableColumn<FoodUtilObject, String> customerName;

    @FXML
    private ImageView logo;

    public void init(LoginDTO loginDTO) {
        Restaurant r = loginDTO.getRestaurant();
        String Data = "Name: " + r.getName() + "\n" + "Price: " + r.getPrice() + "\n" + "Score: " + r.getScore() + "\n"
                + "ZipCode: " + r.getZipCode() + "\n" + "Total Food Count: " + r.getTotalFood();
        System.out.println(Data);
        attributes.setText(Data);

        logo.setImage(new Image("/assets/" + r.getName() + ".png"));

        ObservableList<Food> foodList = FXCollections.observableArrayList(r.getMenu());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        menuTable.setItems(foodList);

        this.loginDTO = loginDTO;
    }

    List<FoodUtilObject> foods = new ArrayList<>();

    public void updateOrder(FoodUtilObject f) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                foods.add(f);

                ObservableList<FoodUtilObject> foodList = FXCollections.observableArrayList(foods);
                orderName.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
                orderCol.setCellValueFactory(new PropertyValueFactory<>("category"));
                orderPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                customerName.setCellValueFactory(new PropertyValueFactory<>("userName"));
                confirmButton.setCellFactory(column -> {
                    return new TableCell<>() {
                        final Button confirmButton = new Button("Confirm");
                        {
                            confirmButton.setOnAction(event -> {
                                FoodUtilObject item = getTableView().getItems().get(getIndex());
                                foods.remove(item); // Ensure that the item is removed here
                                orderTable.getItems().remove(item);
                                System.out.println("Item removed: " + item);
                                FoodUtil foodUtil = (FoodUtil) item;
                                FoodPassUtil foodPassUtil = new FoodPassUtil(foodUtil);
                                System.out.println(foodPassUtil.getFood());
                            
                                // Debugging statement to check if the item is removed before sending
                                System.out.println("Sending FoodPassUtil...");
                            
                                try {
                                    main.getNetworkUtil().write(foodPassUtil);
                                } catch (IOException e) {
                                    System.out.println("Error passing foodutil");
                                }
                            });
                        }

                        @Override
                        protected void updateItem(Button item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(confirmButton);
                            }
                        }
                    };
                });
                orderTable.setItems(foodList);
            }
        });
    }


    public void setMain(ClientRestaurant main) {
        this.main = main;
    }

}
