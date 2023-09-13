package resources;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable{
    private int id;
    private String name;
    private double score;
    private String price;
    private String zipCode;
    private List<String> category;
    private List<Food> menu;

    public Restaurant() {
        this.id = 0;
        this.name = "";
        this.price = "";
        this.zipCode = "";
        this.score = 0.0;
        this.category = new ArrayList<>();
        this.menu = new ArrayList<>();
    }

    public Restaurant(int id, String name, double score ,String price, String zipCode) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.zipCode = zipCode;
        this.score = score;
        this.category = new ArrayList<>();
        this.menu = new ArrayList<>();
    }

    public Restaurant(int id, String name, double score, String price, String zipCode, List<String> category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.zipCode = zipCode;
        this.category = category;
        this.score=score;
        this.menu = new ArrayList<>();
    }

    public Restaurant(int id, String name, double score, String price, String zipCode, List<String> category,List<Food> menu) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.zipCode = zipCode;
        this.category = category;
        this.score=score;
        this.menu = menu;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getZipCode() {
        return zipCode;
    }

    public double getScore()
    {
        return this.score;
    }

    public List<String> getCategory() {
        return category;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public void setMenu(List<Food> menu) {
        this.menu = menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setScore(double score)
    {
        this.score = score;
    }
    
    public int getTotalFood()
    {
        return menu.size();
    }

    public void addFood(Food f)
    {
        menu.add(f);
    }

    public boolean isCategory(String Category)
    {
        boolean isFound = false;

        for(int i=0;i<category.size();i++)
        {
            if(category.get(i).toUpperCase().contains(Category.toUpperCase()))
            {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public boolean isExactCategory(String Category)
    {
        boolean isFound = false;

        for(int i=0;i<category.size();i++)
        {
            if(category.get(i).toUpperCase().equals(Category.toUpperCase()))
            {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public List<Food> isFood(String foodName)
    {
        List<Food> f = new ArrayList<>();
        for(Food i:menu)
        {
            if(i.getFoodName().toUpperCase().contains(foodName.toUpperCase()))
            {
                f.add(i);
            }
        }
        return f;
    }
}
