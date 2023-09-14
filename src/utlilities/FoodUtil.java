package utlilities;

import resources.Food;

public class FoodUtil extends Food {
    String userName;

    public FoodUtil(Food food, String userName) {
        super(food);
        this.userName = userName;
    }

    public FoodUtil(FoodUtil foodUtil) {
        super(foodUtil);
        this.userName = foodUtil.userName;
    }
}
