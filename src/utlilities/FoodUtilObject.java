package utlilities;

import javafx.scene.control.Button;
import resources.Food;

public class FoodUtilObject extends FoodUtil{

    Button button;

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

    public Food getFood() {
        return this;
    }

    public String toString() {
        return "FoodUtilObject [button=" + button + ", userName=" + userName + super.toString()  +  "]";
    }

}
