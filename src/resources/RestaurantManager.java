package resources;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RestaurantManager implements Serializable{
    private String name;
    private List<Restaurant> restaurants;
    private List<String> AllRestCategories;
    private List<Food> foods;

    RestaurantManager() {
        this.name = "";
        this.restaurants = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.AllRestCategories = new ArrayList<>();
    }

    public RestaurantManager(String name) {
        this.name = name;
        this.restaurants = new ArrayList<>();
        this.foods = new ArrayList<>();
        this.AllRestCategories = new ArrayList<>();

    }

    RestaurantManager(String name, List<Restaurant> restaurants, List<Food> foods) {
        this.name = name;
        this.restaurants = restaurants;
        this.foods = foods;
        this.AllRestCategories = new ArrayList<>();
        Set<String> temp = new HashSet<>();
        for(Restaurant i:restaurants)
        {
            for(String j:i.getCategory())
            {
                
                    temp.add(j);
            }
        }
        for(String i:temp)
        {
            AllRestCategories.add(i);
        }
    }


    public void setFoods(List<Food> foodList) {
        this.foods = foodList;
    }

    public List<Food> getFoods() {
        return this.foods;
    }

    public String getName() {
        return this.name;
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    // Option 1
    public List<Restaurant> searchRestaurantByName(String name) {
        List<Restaurant> res = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getName().toUpperCase().contains(name.toUpperCase())) {
                res.add(restaurants.get(i));
            }
        }
        return res;
    }

    public List<Restaurant> searchRestaurantByScore(double low, double high) {
        List<Restaurant> res = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            double score = restaurants.get(i).getScore();
            if (score >= low && score <= high) {
                res.add(restaurants.get(i));
            }
        }
        return res;
    }

    public List<Restaurant> searchRestaurantByCategory(String category) {
        List<Restaurant> res = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).isCategory(category)) {
                res.add(restaurants.get(i));
            }
        }
        return res;
    }

    public List<Restaurant> searchRestaurantByPrice(String price) {
        List<Restaurant> res = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getPrice().equals(price)) {
                res.add(restaurants.get(i));
            }
        }
        return res;
    }

    public List<Restaurant> searchRestaurantByZip(String Zip) {
        List<Restaurant> res = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getZipCode().equals(Zip)) {
                res.add(restaurants.get(i));
            }
        }
        return res;
    }

    public Map<String, String> searchRestaurantByDiffCategory() {
        Map<String, String> map = new HashMap<>();
        for (String i : AllRestCategories) {
            String resName = "";
            for (Restaurant j : restaurants) {
                if (j.isExactCategory(i)) {
                    if (resName.equals("")) {
                        resName = j.getName();
                    } else {
                        resName += ", " + j.getName();
                    }
                }
            }
            map.put(i, resName);
        }
        return map;
    }

    // Option 2
    public List<Food> searchFoodByName(String name) {
        List<Food> f = new ArrayList<>();
        for (Food i : foods) {
            if (i.getFoodName().toUpperCase().contains(name.toUpperCase())) {
                f.add(i);
            }
        }
        return f;
    }

    public Restaurant getRestaurant(String name) {
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getName().toUpperCase().equals(name.toUpperCase())) {
                return restaurants.get(i);
            }
        }
        return null;
    }

    public List<Food> searchFoodByNameOfRestaurant(String foodName, String restaurantName) {
        List<Food> f = new ArrayList<>();
        List<Restaurant> r = searchRestaurantByName(restaurantName);
        for (Restaurant i : r) {
            List<Food> ftemp = i.isFood(foodName);
            for (Food j : ftemp) {
                f.add(j);
            }
        }
        return f;

    }

    public List<Food> searchFoodByCategory(String category) {
        List<Food> f = new ArrayList<>();
        for (Food i : foods) {
            if (i.getCategory().toUpperCase().contains(category.toUpperCase())) {
                f.add(i);
            }
        }
        return f;
    }

    public List<Food> searchFoodByCategoryOfRestaurant(String category, String restaurant) {
        List<Food> f = new ArrayList<>();
        List<Restaurant> r = searchRestaurantByName(restaurant);
        for (Restaurant j : r) {
            for (Food i : foods) {
                if (i.getRestaurantId() == j.getId()
                        && i.getCategory().toUpperCase().contains(category.toUpperCase())) {
                    f.add(i);
                }
            }
        }
        return f;
    }

    public List<Food> searchFoodByPrice(double low, double high) {
        List<Food> f = new ArrayList<>();
        for (Food i : foods) {
            double price = i.getPrice();
            if (low <= price && high >= price) {
                f.add(i);
            }
        }
        return f;
    }

    public List<Food> searchFoodByPriceOfRestaurant(double low, double high, String restaurant) {
        List<Food> f = new ArrayList<>();
        List<Restaurant> r = searchRestaurantByName(restaurant);
        for (Restaurant j : r) {
            for (Food i : foods) {
                double price = i.getPrice();
                if (i.getRestaurantId() == j.getId() && low <= price && high >= price) {
                    f.add(i);
                }
            }
        }
        return f;
    }

    public List<Food> costliestFoodOfReataurant(String restaurant) {
        List<Food> f = new ArrayList<>();
        List<Restaurant> r = searchRestaurantByName(restaurant);
        double costliest = -1;
        for (var i : r) {
            List<Food> menu = i.getMenu();
            for (var j : menu) {
                if (j.getPrice() > costliest) {
                    costliest = j.getPrice();
                }
            }
        }
        for (var i : r) {
            List<Food> menu = i.getMenu();
            for (var j : menu) {
                if (j.getPrice() == costliest) {
                    f.add(j);
                }
            }
        }
        return f;
    }

    public Map<String, Integer> displayTotalFood() {
        Map<String, Integer> map = new HashMap<>();
        for (Restaurant i : restaurants) {
            map.put(i.getName(), i.getTotalFood());
        }
        return map;
    }

    boolean isCat(String Cat) {
        for (String i : AllRestCategories) {
            if (i.toUpperCase().equals(Cat.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    // Option 3

    public boolean isRestaurantValid(String name) {
        for (Restaurant i : restaurants) {
            if (i.getName().toUpperCase().equals(name.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean isRestaurantValid(int id) {
        for (Restaurant i : restaurants) {
            if (i.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public int addRestaurant(Restaurant r) {
        boolean isValid = isRestaurantValid(r.getName()) && isRestaurantValid(r.getId());
        if (isValid) {
            restaurants.add(new Restaurant(r.getId(), r.getName(), r.getScore(), r.getPrice(), r.getZipCode(),
                    r.getCategory()));
            for (String i : r.getCategory()) {
                if (!isCat(i)) {
                    AllRestCategories.add(i);
                }
            }
            for(var i:r.getMenu())
            {
                foods.add(i);
            }
            return 1;
        } else {
            return -1;
        }
    }

    // Option 4
    public boolean isFoodValid(Food f) {
        for (Food i : foods) {
            if (i.isEqual(f)) {
                return false;
            }
        }
        return true;
    }

    public String getRestaurantName(int id)
    {
        for(Restaurant i:restaurants)
        {
            if(i.getId()==id)
            {
                return i.getName();
            }
        }
        return null;
    }

    public int addFood(Food f) {
        if (isFoodValid(f)) {
            f.setRestaurantName(getRestaurantName(f.getRestaurantId()));
            foods.add(f);
            for (Restaurant i : restaurants) {
                if (i.getId() == f.getRestaurantId()) {
                    i.addFood(f);
                    break;
                }
            }
            return 1;
        } else {
            return -1;
        }
    }

    public int getResId(String resName) {
        int id = -1;
        for (Restaurant i : restaurants) {
            if (i.getName().toUpperCase().equals(resName.toUpperCase())) {
                return i.getId();
            }
        }
        return id;
    }

    
}
