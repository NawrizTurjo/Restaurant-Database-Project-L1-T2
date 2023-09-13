package resources;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileOp {

    private static final String INPUT_FILE_NAME1 = "restaurant.txt";
    private static final String INPUT_FILE_NAME2 = "menu.txt";
    private static final String OUTPUT_FILE_NAME1 = "restaurant.txt";
    private static final String OUTPUT_FILE_NAME2 = "menu.txt";
    private static final String INPUT_FILE_NAME3 = "restaurantPass.txt";
    private static final String INPUT_FILE_NAME4 = "customerPass.txt";
    private static final DecimalFormat df = new DecimalFormat("#.##");


    public List<Restaurant> fileRestaurant() {
        List<Restaurant> restaurantList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME1));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",", -1);
                int id = Integer.parseInt(array[0]);
                String name = array[1];
                double score = Double.parseDouble(array[2]);
                String price = array[3];
                String zip = array[4];
                List<String> category = new ArrayList<>();
                for (int i = 5; i < array.length; i++) {
                    if (!(array[i].equals(""))) {
                        category.add(array[i]);
                    }
                }
                Restaurant restaurant = new Restaurant(id, name, score, price, zip, category);
                restaurantList.add(restaurant);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurantList;
    }

    public List<Food> fileFood() {
        List<Food> foodList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME2));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",", -1);
                int id = Integer.parseInt(array[0]);
                String category = array[1];
                String name = array[2];
                double price = Double.parseDouble(array[3]);
                Food f = new Food(id, category, name, price);
                foodList.add(f);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodList;
    }

    public void writeFile(List<Food> f, List<Restaurant> r) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME2));
            for (var i : f) {
                String line = i.getRestaurantId() + "," + i.getCategory() + "," + i.getFoodName() + ","
                        + df.format(i.getPrice());
                bw.write(line + "\n");
            }
            bw.close();
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME1));
            for (var i : r) {
                String line = i.getId() + "," + i.getName() + "," + df.format(i.getScore()) + "," + i.getPrice() + ","
                        + i.getZipCode();
                for (int j = 0; j < i.getCategory().size(); j++) {
                    line += "," + i.getCategory().get(j);
                }
                if (i.getCategory().size() < 3) {
                    line += ",";
                }
                bw2.write(line + "\n");
            }
            bw2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String,String> readRestaurantPass()
    {
        HashMap<String,String> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME3));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",", -1);
                map.put(array[0], array[1]);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static HashMap<String,String> readFoodPass()
    {
        HashMap<String,String> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME4));
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",", -1);
                map.put(array[0], array[1]);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}