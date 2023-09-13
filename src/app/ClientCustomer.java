package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.CustomerWelcomeController;
import controller.HomeScreenController;
import controller.OrderCustomerSideController;
import controller.OrderFoodController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import network.ReadThreadCustomer;
import resources.Food;
import resources.RestaurantManager;
import utlilities.NetworkUtil;

public class ClientCustomer extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;
    public static RestaurantManager restaurantManager;
    public OrderCustomerSideController orderCustomerSideController;
    public String userName;

    public List<Food> OrederdFoodItems = new ArrayList<>();
    double price = 0;

    public Stage getStage() {
        return stage;
    }

    public String getUsername() {
        return this.userName;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public void updateFoodList(Food food) {
        String resName = restaurantManager.getRestaurantName(food.getRestaurantId());
        food.setRestaurantName(resName);
        price += food.getPrice();
        OrederdFoodItems.add(food);
        System.out.println("List Updated with " + food);
        if (orderCustomerSideController != null) {
            orderCustomerSideController.updateFoodOrderList(food);
        }

    }

    public void displayCustomerSideOrder(List<Food> foodList) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ordercustomerside.fxml"));
        Parent root = loader.load();
        OrderCustomerSideController controller = loader.getController();
        this.orderCustomerSideController = controller;
        controller.setMain(this);
        controller.init(foodList);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Pay Slip");
        stage.setScene(new Scene(root));
        controller.setStage(stage);
        stage.show();
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        if (connectToServer()) {
            showCustomerLogin();
        } else {
            showAlert();
        }
    }

    private boolean connectToServer() throws IOException {
        try {
            String serverAddress = "127.0.0.1";
            int serverPort = 33333;
            networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(true);
            new ReadThreadCustomer(this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void showCustomerLogin() throws IOException {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/customerwelcome.fxml"));
        Parent root = loader.load();

        ImageView icon = new ImageView("/assets/icon.jpg");
        stage.getIcons().add(icon.getImage());

        // Loading the controller
        CustomerWelcomeController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Customer Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        controller.setStage(stage);
        stage.show();
    }

    public void showOrderPage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/orderfood.fxml"));
        Parent root = loader.load();

        OrderFoodController controller = loader.getController();
        controller.setMain(this);

        Stage popUpStage = new Stage();
        popUpStage.setTitle("Order Page");
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("/assets/order.jpg"));
        popUpStage.getIcons().add(icon);
        popUpStage.showAndWait();
    }

    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/homescreen.fxml"));
        Parent root = loader.load();

        HomeScreenController controller = loader.getController();
        controller.setWelcomeLabel(userName);
        controller.setMain(this);

        Stage popUpStage = new Stage();
        popUpStage.setTitle("Search Page");
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setScene(new Scene(root));
        Image icon = new Image(getClass().getResourceAsStream("/assets/search.jpg"));
        popUpStage.getIcons().add(icon);
        popUpStage.showAndWait();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Server Error");
        alert.setHeaderText("Server Error");
        alert.setContentText("Server is not running.");
        alert.showAndWait();
    }
    
    public List<Food> getFoodList() {
        return OrederdFoodItems;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
