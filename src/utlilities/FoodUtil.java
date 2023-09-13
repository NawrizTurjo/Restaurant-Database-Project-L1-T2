package utlilities;

import java.io.Serializable;

import resources.Food;
import java.util.Objects;

public class FoodUtil extends Food {

    // Food food;

    // public FoodUtil() {
    // }

    // public Food getFood() {
    // return this.food;
    // }

    // public void setFood(Food food) {
    // this.food = food;
    // }

    // public String getUserName() {
    // return this.userName;
    // }

    // public void setUserName(String userName) {
    // this.userName = userName;
    // }

    // public FoodUtil food(Food food) {
    // setFood(food);
    // return this;
    // }

    // public FoodUtil userName(String userName) {
    // setUserName(userName);
    // return this;
    // }

    // @Override
    // public boolean equals(Object o) {
    // if (o == this)
    // return true;
    // if (!(o instanceof FoodUtil)) {
    // return false;
    // }
    // FoodUtil foodUtil = (FoodUtil) o;
    // return Objects.equals(food, foodUtil.food) && Objects.equals(userName,
    // foodUtil.userName);
    // }

    // @Override
    // public int hashCode() {
    // return Objects.hash(food, userName);
    // }

    // @Override
    // public String toString() {
    // return "{" +
    // " food='" + getFood() + "'" +
    // ", userName='" + getUserName() + "'" +
    // "}";
    // }

    // String userName;

    // public FoodUtil(Food food, String userName){
    // super(food);
    // this.userName = userName;
    // }

    // public FoodUtil(FoodUtil foodUtil){
    // super(foodUtil.food);
    // this.userName = foodUtil.userName;
    // }

    // public int getRestaurantId()
    // {
    // return this.food.getRestaurantId();
    // }

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

// public FoodUtil(FoodUtil foodUtil){
// super(foodUtil);
// this.userName = foodUtil.userName;
// }
