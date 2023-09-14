package resources;
import java.io.Serializable;

public class Food implements Serializable{

    private static final long serialVersionUID = 1L;

    int restaurantId;
    String category;
    String foodName;
    double price;
    String restaurantName;

    public Food() {
        this.restaurantId = 0;
        this.category = "";
        this.foodName = "";
        this.price = 0.0;
    }

    public Food(int restaurantId, String category, String foodName, double price) {
        this.restaurantId = restaurantId;
        this.category = category;
        this.foodName = foodName;
        this.price = price;
    }

    public Food(Food food)
    {
        this.restaurantId = food.getRestaurantId();
        this.category = food.getCategory();
        this.foodName = food.getFoodName();
        this.price = food.getPrice();
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isEqual(Food f) {
        if (this.foodName.toUpperCase().equals(f.getFoodName().toUpperCase()) &&
                this.restaurantId == f.getRestaurantId() &&
                this.category.toUpperCase().equals(f.getCategory().toUpperCase()) &&
                this.price == f.getPrice()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return restaurantId+", "+category+", "+foodName+", "+price;
    }
}
