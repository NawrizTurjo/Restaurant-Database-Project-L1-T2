package utlilities;

import resources.Food;

public class FoodPassUtil extends FoodUtil{

    private static final long serialVersionUID = 5525171917750934004L; // Use the correct UID

    String className;

    public FoodPassUtil(Food food, String userName) {
        super(food, userName);
        className = "RestaurantClient";
    }

    public FoodPassUtil(FoodUtil foodUtil)
    {
        super(foodUtil);
        className = "RestaurantClient";
    }

    public Food getFood()
    {
        return this;
    }

    public String getUserName() {
        return userName;
    }

    
}
