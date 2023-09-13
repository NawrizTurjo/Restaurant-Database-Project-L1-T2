package utlilities;

import java.io.Serializable;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.util.Objects;

public class FoodUtilObject extends FoodUtil{

    // FoodUtil foodUtil;
    Button button;

    // public FoodUtilObject() {
    // }

    // public FoodUtilObject(FoodUtil foodUtil, Button button) {
    //     this.foodUtil = foodUtil;
    //     this.button = button;
    // }

    // public FoodUtil getFoodUtil() {
    //     return this.foodUtil;
    // }

    // public void setFoodUtil(FoodUtil foodUtil) {
    //     this.foodUtil = foodUtil;
    // }

    // public Button getButton() {
    //     return this.button;
    // }

    // public void setButton(Button button) {
    //     this.button = button;
    // }

    // public FoodUtilObject foodUtil(FoodUtil foodUtil) {
    //     setFoodUtil(foodUtil);
    //     return this;
    // }

    // public FoodUtilObject button(Button button) {
    //     setButton(button);
    //     return this;
    // }

    // @Override
    // public boolean equals(Object o) {
    //     if (o == this)
    //         return true;
    //     if (!(o instanceof FoodUtilObject)) {
    //         return false;
    //     }
    //     FoodUtilObject foodUtilObject = (FoodUtilObject) o;
    //     return Objects.equals(foodUtil, foodUtilObject.foodUtil) && Objects.equals(button, foodUtilObject.button);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(foodUtil, button);
    // }

    // @Override
    // public String toString() {
    //     return "{" +
    //         " foodUtil='" + getFoodUtil() + "'" +
    //         ", button='" + getButton() + "'" +
    //         "}";
    // }

    // public FoodUtilObject(FoodUtil foodUtil) {
    //     this.foodUtil = foodUtil;
    //     button = new Button("Confirm");
    // }

    public FoodUtilObject(FoodUtil foodUtil){
        super(foodUtil);
        button = new Button("Confirm");
    }

    public String getUserName() {
        return userName;
    }

    public Button getButton() {
        return button;
    }

    // button.setOnAction(e->{
    //     @Override
    //     public void handle(ActionEvent event) {
    //         System.out.println("Button clicked");
    //     }
    // });
    
    
}
