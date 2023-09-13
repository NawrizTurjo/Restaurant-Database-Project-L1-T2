package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.swing.event.ChangeListener;

import app.ClientCustomer;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import resources.Food;
import resources.Restaurant;

public class HomeScreenController implements Initializable {

    @FXML
    private ListView<String> foodList;

    @FXML
    private TextField nameText;

    @FXML
    private ListView<String> restaurantList;

    @FXML
    private TextField secondaryText;

    @FXML
    private Label searchLabel;

    @FXML
    private Label welcomeLabel;

    public void setWelcomeLabel(String name) {
        welcomeLabel.setText("Welcome " + name);
    }

    @FXML
    private Button orderButton;

    @FXML
    void order(ActionEvent event) throws IOException {
        displayOrderPage();
    }

    @FXML
    private Button historyButton;


    @FXML
    void history(ActionEvent event) throws IOException {
        main.displayCustomerSideOrder(main.getFoodList());
    }

    

    @FXML
    void search(ActionEvent event) throws IOException {
        // Get the selected items from the ListView components
        String selectedRestaurant = restaurantList.getSelectionModel().getSelectedItem();
        String selectedFood = foodList.getSelectionModel().getSelectedItem();

        List<Restaurant> restaurants = new ArrayList<>();
        List<Food> foods = new ArrayList<>();

        if (selectedRestaurant != null) {
            switch (selectedRestaurant) {
                case "By Name":
                    restaurants = ClientCustomer.restaurantManager.searchRestaurantByName(nameText.getText());
                    displayRestaurantSearch(restaurants, "Search By Name");
                    break;
                case "By Score":
                    restaurants = ClientCustomer.restaurantManager.searchRestaurantByScore(Double.parseDouble(nameText.getText()),
                            Double.parseDouble(secondaryText.getText()));
                    displayRestaurantSearch(restaurants, "Search By Score");
                    break;
                case "By Category":
                    restaurants = ClientCustomer.restaurantManager.searchRestaurantByCategory(nameText.getText());
                    displayRestaurantSearch(restaurants, "Search By Category");
                    break;
                case "By Price":
                    restaurants = ClientCustomer.restaurantManager.searchRestaurantByPrice(nameText.getText());
                    displayRestaurantSearch(restaurants, "Search By Price");
                    break;
                case "By Zip Code":
                    restaurants = ClientCustomer.restaurantManager.searchRestaurantByZip(nameText.getText());
                    displayRestaurantSearch(restaurants, "Search By Zip Code");
                    break;
                case "Different Category Wise List of Restaurants":
                    Map<String, String> map = ClientCustomer.restaurantManager.searchRestaurantByDiffCategory();
                    List<String> stringMap = new ArrayList<>();
                    for (Entry<String, String> i : map.entrySet()) {
                        stringMap.add(i.getKey() + ": " + i.getValue());
                    }
                    differentSearch(stringMap, "Different Category Wise List of Restaurants");
                    break;
                default:
                    showAlert("Restaurant Search", "Invalid selection.");
                    break;
            }
        } else if (selectedFood != null) {
            switch (selectedFood) {
                case "By Name":
                    foods = ClientCustomer.restaurantManager.searchFoodByName(nameText.getText());
                    displayFood(foods,"Search By Name");
                    break;
                case "By Name in a given restaurant":
                    foods = ClientCustomer.restaurantManager.searchFoodByNameOfRestaurant(nameText.getText(),
                            secondaryText.getText());
                    displayFood(foods,"Search By Name in a given restaurant");
                    break;
                case "By Category":
                    foods = ClientCustomer.restaurantManager.searchFoodByCategory(nameText.getText());
                    displayFood(foods,"Search By Category");
                    break;
                case "By Category in a given restaurant":
                    foods = ClientCustomer.restaurantManager.searchFoodByCategoryOfRestaurant(nameText.getText(),
                            secondaryText.getText());
                    displayFood(foods,"Search By Category in a given restaurant");
                    break;
                case "By Price Range":
                    String line[] = nameText.getText().split(" ");
                    foods = ClientCustomer.restaurantManager.searchFoodByPrice(Double.parseDouble(line[0]),
                            Double.parseDouble(line[1]));
                    displayFood(foods,"Search By Price Range");
                    break;
                case "By Price Range in a Given Restaurant":
                    String lines[] = nameText.getText().split(" ");
                    foods = ClientCustomer.restaurantManager.searchFoodByPriceOfRestaurant(Double.parseDouble(lines[0]),
                            Double.parseDouble(lines[1]), secondaryText.getText());
                    displayFood(foods,"Search By Price Range in a Given Restaurant");
                    break;
                case "Costliest Food Item(s) on the Menu in a Given Restaurant":
                    foods = ClientCustomer.restaurantManager.costliestFoodOfReataurant(nameText.getText());
                    displayFood(foods,"Costliest Food Item(s) on the Menu in a Given Restaurant");
                    break;
                case "List of Restaurants and Total Food Item on the Menu":
                    Map<String, Integer> map = ClientCustomer.restaurantManager.displayTotalFood();
                    List<String> stringMap = new ArrayList<>();
                    for (Entry<String, Integer> i : map.entrySet()) {
                        stringMap.add(i.getKey() + ": " + i.getValue());
                    }
                    differentSearch(stringMap, "List of Restaurants and Total Food Item on the Menu");
                    break;
                default:
                    showAlert("Food Search", "Invalid selection.");
                    break;
            }
        } else {
            showAlert("Search", "Please select a search option.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void displayRestaurantSearch(List<Restaurant> restaurants, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/customerrestaurant.fxml"));
        Parent root = loader.load();
        CustomerRestaurantController controller = loader.getController();
        controller.init(restaurants);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setOnCloseRequest(e -> {
            e.consume();
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            if (a.showAndWait().get() == ButtonType.OK) {
                stage.close();
            }
        });
        controller.setStage(stage);
        stage.show();
    }

    private void displayOrderPage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/orderfood.fxml"));
        Parent root = loader.load();
        OrderFoodController controller = loader.getController();
        controller.setFoodItems(ClientCustomer.restaurantManager.getFoods());
        // controller.setRestaurantNames(App.restaurantManager.getRestaurants());
        
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Order Page");
        stage.setScene(new Scene(root));
        stage.setOnCloseRequest(e -> {
            e.consume();
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            if (a.showAndWait().get() == ButtonType.OK) {
                stage.close();
            }
        });
        controller.setStage(stage); 
        controller.setMain(main);
        stage.show();
    }

    private void displayFood(List<Food> foods, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/customerfood.fxml"));
        Parent root = loader.load();
        CustomerFoodController controller = loader.getController();
        controller.init(foods);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setOnCloseRequest(e -> {
            e.consume();
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            if (a.showAndWait().get() == ButtonType.OK) {
                stage.close();
            }
        });
        controller.setStage(stage);
        stage.show();
    }

    private void differentSearch(List<String> items, String title) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/differentrestaurantfood.fxml"));
        Parent root = loader.load();
        DifferentRestaurantFoodController controller = loader.getController();
        controller.init(items);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.setOnCloseRequest(e -> {
            e.consume();
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            if (a.showAndWait().get() == ButtonType.OK) {
                stage.close();
            }
        });
        controller.setStage(stage);
        stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        restaurantList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchLabel.setText("Searching Restaurant");
                foodList.getSelectionModel().clearSelection();
                nameText.clear();
                secondaryText.clear();
                if(newValue.equals("By Name")||newValue.equals("By Category")||newValue.equals("By Zip Code")||newValue.equals("By Price")){
                    nameText.setVisible(true);
                    nameText.setPromptText(newValue);
                    secondaryText.setVisible(false);
                }
                else if(newValue.equals("Different Category Wise List of Restaurants")){
                    secondaryText.setVisible(false);
                    nameText.setVisible(false);
                }
                else
                {
                    nameText.setVisible(true);
                    secondaryText.setVisible(true);
                    nameText.setPromptText("Enter Lower Score");
                    secondaryText.setPromptText("Enter Upper Score");
                }
            }
        });

        foodList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
            {   searchLabel.setText("Searching Food");
                restaurantList.getSelectionModel().clearSelection();
                nameText.clear();
                secondaryText.clear();

                if(newValue.equals("By Name")||newValue.equals("By Category")){
                    nameText.setVisible(true);
                    nameText.setPromptText(newValue);
                    secondaryText.setVisible(false);
                }
                else if(newValue.equals("By Name in a given restaurant")||newValue.equals("By Category in a given restaurant")){
                    nameText.setVisible(true);
                    secondaryText.setVisible(true);
                    nameText.setPromptText(newValue);
                    secondaryText.setPromptText("Enter Restaurant Name");
                }
                else if(newValue.equals("By Price Range")){
                    nameText.setVisible(true);
                    secondaryText.setVisible(false);
                    nameText.setPromptText("Enter Lower Price and Enter Upper Price(eg. 10 20)");
                }
                else if(newValue.equals("By Price Range in a Given Restaurant"))
                {
                    nameText.setVisible(true);
                    secondaryText.setVisible(true);
                    nameText.setPromptText("Enter Lower Price and Enter Upper Price(eg. 10 20)");
                    secondaryText.setPromptText("Enter Restaurant Name");
                }
                else if(newValue.equals("Costliest Food Item(s) on the Menu in a Given Restaurant")){
                    nameText.setVisible(true);
                    secondaryText.setVisible(false);
                    nameText.setPromptText("Enter Restaurant Name");
                }
                else if(newValue.equals("List of Restaurants and Total Food Item on the Menu")){
                    nameText.setVisible(false);
                    secondaryText.setVisible(false);
                }
        }});
        
        ObservableList<String> searchRestaurant = FXCollections.observableArrayList("By Name", "By Score", "By Price",
                "By Category", "By Zip Code", "Different Category Wise List of Restaurants");
        ObservableList<String> searchFood = FXCollections.observableArrayList("By Name",
                "By Name in a given restaurant", "By Category", "By Category in a given restaurant", "By Price Range",
                "By Price Range in a Given Restaurant", "Costliest Food Item(s) on the Menu in a Given Restaurant",
                "List of Restaurants and Total Food Item on the Menu");

        restaurantList.setItems(searchRestaurant);
        foodList.setItems(searchFood);

        nameText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                nameText.getStyleClass().clear();
                nameText.getStyleClass().add("text-field-focused");
            } else {
                nameText.getStyleClass().clear();
                nameText.getStyleClass().add("text-field");
            }
        });
        
        secondaryText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                secondaryText.getStyleClass().clear();
                secondaryText.getStyleClass().add("text-field-focused");
            } else {
                secondaryText.getStyleClass().clear();
                secondaryText.getStyleClass().add("text-field");
            }
        });
        

    }
    public ClientCustomer main;

    public void setMain(ClientCustomer main)
    {
        this.main = main;
    }

    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    

}
