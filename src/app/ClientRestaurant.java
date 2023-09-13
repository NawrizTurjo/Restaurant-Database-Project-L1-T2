package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import network.ReadThreadRestaurant;
import resources.Restaurant;
import resources.RestaurantManager;
import utlilities.LoginDTO;
import utlilities.NetworkUtil;

import java.io.IOException;

import controller.HomeController;
import controller.LoginController;

public class ClientRestaurant extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;
    private Restaurant restaurant;
    public HomeController homeController;
    public RestaurantManager restaurantManager;

    public void setRestaurantManager(RestaurantManager restaurantManager) {
        this.restaurantManager = restaurantManager;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        System.out.println("This is set");
    }

    public Restaurant getRestaurant() {
        return this.restaurant;
    }

    public void setRestaurantByName(String id) {
        int ID = Integer.parseInt(id);
        for (Restaurant r : restaurantManager.getRestaurants()) {
            if (r.getId() == ID) {
                this.restaurant = r;
                break;
            }
        }
    }

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        networkUtil.write(false);
        new ReadThreadRestaurant(this);
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();

        // ImageView img1 = 

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showHomePage(LoginDTO loginDTO) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/home.fxml"));
        Parent root = loader.load();
        // setRestaurant(loginDTO.getRestaurant());

        // Loading the controller
        HomeController controller = loader.getController();
        this.homeController = controller;

        ImageView icon = new ImageView("/assets/" + loginDTO.getRestaurant().getName() + ".png");
        stage.getIcons().clear();
        stage.getIcons().add(icon.getImage());

        controller.init(loginDTO);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root));
        stage.show();
        // System.out.println("In homePage: "+Server.loggedIn.isEmpty()+"as restaurant
        // is added");
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application

        launch(args);
    }
}
