package controller;

// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;

// public class HomeController {

//     private Main main;
//     private App app;

//     @FXML
//     private Label message;

//     @FXML
//     private ImageView image;
//     LoginDTO loginDTO;

//     @FXML
//     private Button button;

//     public void init(LoginDTO loginDTO) {
//         Restaurant r = loginDTO.getRestaurant();
//         String Data = r.getName()+"\n"+r.getPrice()+"\n"+r.getScore()+"\n"+r.getZipCode()+r.getTotalFood();
//         message.setText(Data);
//         Image img = new Image(App.class.getResourceAsStream("1.png"));
//         image.setImage(img);
//         this.loginDTO = loginDTO;
//     }

//     @FXML
//     void logoutAction(ActionEvent event) {
//         try {
//             main.showLoginPage();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     void setMain(Main main) {
//         this.main = main;
//     }

//     void setMain(App app) {
//         this.app = app;
//     }

// }

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.ClientCustomer;
import app.ClientRestaurant;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.Food;
import resources.Restaurant;
import utlilities.LoginDTO;

public class HomeController{

    private ClientRestaurant main;
    private ClientCustomer app;
    LoginDTO loginDTO;

    @FXML
    private Label attributes;

    @FXML
    private Button button;

    @FXML
    private TableColumn<Food, String> catCol;

    @FXML
    private ImageView image;

    @FXML
    private TableView<Food> menuTable;

    @FXML
    private TableColumn<Food, String> nameCol;

    @FXML
    private TableColumn<Food, String> orderCol;

    @FXML
    private TableColumn<Food, String> orderName;

    @FXML
    private TableColumn<Food, Double> orderPrice;

    @FXML
    private TableView<Food> orderTable;

    @FXML
    private TableColumn<Food, Double> priceCol;

    public void init(LoginDTO loginDTO) {
        Restaurant r = loginDTO.getRestaurant();
        String Data = "Name: " + r.getName() + "\n" + "Price: " + r.getPrice() + "\n" + "Score: " + r.getScore() + "\n"
                + "ZipCode: " + r.getZipCode() + "\n" + "Total Food Count: " + r.getTotalFood();
        System.out.println(Data);
        attributes.setText(Data);

        ObservableList<Food> foodList = FXCollections.observableArrayList(r.getMenu());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        menuTable.setItems(foodList);

        this.loginDTO = loginDTO;
    }
    List<Food> foods = new ArrayList<>();
    public void updateOrder(Food f) {
    Platform.runLater(new Runnable() {
        @Override
        public void run() {
            foods.add(f);

            ObservableList<Food> foodList = FXCollections.observableArrayList(foods);
            orderName.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
            orderCol.setCellValueFactory(new PropertyValueFactory<>("category"));
            orderPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            orderTable.setItems(foodList);
        }
    });
}


    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(ClientRestaurant main) {
        this.main = main;
    }

    void setMain(ClientCustomer app) {
        this.app = app;
    }

    // @Override
    // public void initialize(URL location, ResourceBundle resources) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    // }
}
