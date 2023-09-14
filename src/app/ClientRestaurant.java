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
        if (connectToServer()) {
            showLoginPage();
        } else
            showAlert();
    }

    private boolean connectToServer() throws IOException {
        try {
            String serverAddress = "127.0.0.1";
            int serverPort = 33333;
            networkUtil = new NetworkUtil(serverAddress, serverPort);
            networkUtil.write(false);
            new ReadThreadRestaurant(this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void showLoginPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setMain(this);

        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showHomePage(LoginDTO loginDTO) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/home.fxml"));
        Parent root = loader.load();

        HomeController controller = loader.getController();
        this.homeController = controller;

        ImageView icon = new ImageView("/assets/" + loginDTO.getRestaurant().getName() + ".png");
        stage.getIcons().clear();
        stage.getIcons().add(icon.getImage());

        controller.init(loginDTO);
        controller.setMain(this);

        stage.setTitle("Home");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Server Error");
        alert.setHeaderText("Server Error");
        alert.setContentText("Server is not running.");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
