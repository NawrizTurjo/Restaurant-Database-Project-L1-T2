package utlilities;
import java.io.Serializable;

import resources.Restaurant;
import resources.RestaurantManager;

public class LoginDTO implements Serializable {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String userName;
    private String password;
    private boolean status;
    Restaurant restaurant;
    RestaurantManager restaurantManager;

    public Restaurant getRestaurant()
    {
        return this.restaurant;
    }

    public void setRestaurant(Restaurant restaurant)
    {
        this.restaurant = restaurant;
    }

    RestaurantManager getRestaurantManager(){
        return this.restaurantManager;
    }
}
