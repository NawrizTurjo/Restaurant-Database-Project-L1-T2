package app;
import java.io.IOException;

import controller.CustomerWelcomeController;
import controller.HomeScreenController;
import controller.LoginController;
import controller.OrderFoodController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import network.ReadThreadCustomer;
import resources.RestaurantManager;
import utlilities.NetworkUtil;


public class ClientCustomer extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;
    public static RestaurantManager restaurantManager;
    // public boolean isServer = false;

    public Stage getStage() {
        return stage;
    }

    // public void setServer(boolean isServer) {
    //     this.isServer = isServer;
    // }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        try {
            connectToServer();
            showCustomerLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connectToServer() throws IOException {
        try {
            String serverAddress = "127.0.0.1";
            int serverPort = 33333;
            networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(true);
            new ReadThreadCustomer(this);
        } catch (Exception e) {
            showAlert();
        }
    }

    public void showCustomerLogin() throws IOException{
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
        // System.out.println("In customerloginPage: "+Server.loggedIn.isEmpty());
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }

    public void showOrderPage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/orderfood.fxml"));
        Parent root = loader.load();

        // Loading the controller
        OrderFoodController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Order Page");
        // System.out.println("In orderPage: "+Server.loggedIn.isEmpty());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/homescreen.fxml"));
        Parent root = loader.load();

        // Loading the controller
        // homeScreenController controller = loader.getController();
        // controller.init(userName);
        
        // Set the primary stage
        // Stage primaryStage = new Stage();
        // primaryStage.setTitle("Search");
        // primaryStage.setScene(new Scene(root));
        // primaryStage.show();
        
        // FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("homescreen.fxml"));
        // Parent parent = fxmlLoader.load();
        
        HomeScreenController controller = loader.getController();
        controller.setWelcomeLabel(userName);
        controller.setMain(this);

        Stage popUpStage = new Stage();
        popUpStage.setTitle("Search Page");
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.setScene(new Scene(root));
        popUpStage.showAndWait();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    // public void setRestaurantManager(RestaurantManager restaurantManager) {
    // this.restaurantManager = restaurantManager;
    // }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        // FileOp fileReader = new FileOp();
        // List<Restaurant> r = fileReader.fileRestaurant();
        // List<Food> F = fileReader.fileFood();
        // for (var i : r) {
        // restaurantManager.addRestaurant(i);
        // }
        // for (var i : F) {
        // restaurantManager.addFood(i);
        // }
        launch(args);
    }
}
