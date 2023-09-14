package controller;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.TableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import resources.Food;
import resources.Restaurant;

public class CustomerRestaurantController {

    Stage stage;

    @FXML
    private TableColumn<Restaurant, Integer> idColumn;

    @FXML
    private TableColumn<Restaurant, String> nameColumn;

    @FXML
    private TableColumn<Restaurant, Double> priceColumn;

    @FXML
    private TableColumn<Restaurant, Double> scoreColumn;

    @FXML
    private TableColumn<Restaurant, String> zipColumn;

    @FXML
    private TableView<Restaurant> tableView;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<Restaurant, List<String>> categoryColumn;

    @FXML
    private TableColumn<Restaurant, List<Food>> menuColumn;

    @FXML
    void closeWindow(ActionEvent event) {
        Alert a = new Alert(AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            a.initModality(Modality.APPLICATION_MODAL);
            if(a.showAndWait().get() == ButtonType.OK){
                stage.close();
            }
            else{
                a.close();
            }
    }

    void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void init(List<Restaurant> restaurants) {
        ObservableList<Restaurant> data = FXCollections.observableArrayList(restaurants);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setCellFactory(column -> {
            return new TableCell<Restaurant, List<String>>() {
                @Override
                protected void updateItem(List<String> category, boolean empty) {
                    super.updateItem(category, empty);

                    if (category == null || empty) {
                        setText(null);
                    } else {
                        String categoryText = String.join("\n", category);
                        setText(categoryText);
                    }
                }
            };
        });

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zipCode"));
        menuColumn.setCellValueFactory(new PropertyValueFactory<>("menu"));
        menuColumn.setCellFactory(column -> {
            return new TableCell<Restaurant, List<Food>>() {
                @Override
                protected void updateItem(List<Food> menu, boolean empty) {
                    super.updateItem(menu, empty);

                    if (menu == null || empty) {
                        setText(null);
                    } else {
                        String menuText = menu.stream()
                                .map(Food::toString)
                                .collect(Collectors.joining("\n"));

                        setText(menuText);
                    }
                }
            };
        });

        tableView.setItems(data);
    }

}
