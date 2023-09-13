package resources;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RestaurantApp {
    public static void showFood(List<Food> f) {
        int count = 1;
        for (var i : f) {
            System.out.println(count++);
            System.out.println("Restaurant Id: " + i.getRestaurantId());
            System.out.println("Category: " + i.getCategory());
            System.out.println("Food Name: " + i.getFoodName());
            System.out.println("Price: " + i.getPrice());
            System.out.println();
        }
    }

    public static void showRestaurant(List<Restaurant> r) {
        int count = 1;
        for (var i : r) {
            System.out.println(count++);
            System.out.println("Restaurant ID: " + i.getId());
            System.out.println("Restaurant Name: " + i.getName());
            System.out.println("Score: " + i.getScore());
            System.out.println("Price: " + i.getPrice());
            System.out.println("Zip Code: " + i.getZipCode());
            System.out.println("Categories: ");
            String cat = "";
            for (String j : i.getCategory()) {
                if (cat.equals("")) {
                    cat = j;
                } else {
                    cat += "," + j;
                }
            }
            System.out.println(cat);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Menu: \n");
            int fcnt = 1;
            for (Food j : i.getMenu()) {
                System.out.println("Menu item: " + fcnt++);
                showFood(j);
            }
        }
    }

    public static void showFood(Food f) {
        System.out.println("Restaurant Id: " + f.getRestaurantId());
        System.out.println("Category: " + f.getCategory());
        System.out.println("Food Name: " + f.getFoodName());
        System.out.println("Price: " + f.getPrice());
        System.out.println();
    }

    public static void printMappedRestaurant(Map<String, String> map) {
        for (Map.Entry<String, String> i : map.entrySet()) {
            System.out.println(i.getKey() + ": " + i.getValue());
        }
    }

    public static void printMappedFood(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> i : map.entrySet()) {
            System.out.println(i.getKey() + ": " + i.getValue());
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            int change = 0;
            Scanner scanner = new Scanner(System.in);
            RestaurantManager RestaurantManager = new RestaurantManager("Restaurants");
            FileOp fileReader = new FileOp();
            List<Restaurant> r = fileReader.fileRestaurant();
            List<Food> F = fileReader.fileFood();
            for (var i : r) {
                RestaurantManager.addRestaurant(i);
            }
            for (var i : F) {
                RestaurantManager.addFood(i);
            }
            int choice;
            do {
                System.out.println("Main Menu:");
                System.out.println("1) Search Restaurants");
                System.out.println("2) Search Food Items");
                System.out.println("3) Add Restaurant");
                System.out.println("4) Add Food Item to the Menu");
                System.out.println("5) Exit System");

                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("1) By Name");
                        System.out.println("2) By Score");
                        System.out.println("3) By Category");
                        System.out.println("4) By Price");
                        System.out.println("5) By Zip Code");
                        System.out.println("6) Different Category Wise List of Restaurants");
                        System.out.println("7) Back to Main Menu");

                        int choice2 = Integer.parseInt(scanner.nextLine());
                        switch (choice2) {
                            case 1:
                                System.out.print("Enter a restaurant name to search: ");
                                String name1 = scanner.nextLine();
                                List<Restaurant> resName = RestaurantManager.searchRestaurantByName(name1);
                                if (resName.size() > 0) {
                                    showRestaurant(resName);
                                } else {
                                    System.out.println("No such restaurant with this name");
                                }
                                break;
                            case 2:
                                System.out.print("Enter a lower score: ");
                                double low = Double.parseDouble(scanner.nextLine());
                                System.out.print("Enter a upper score: ");
                                double high = Double.parseDouble(scanner.nextLine());
                                List<Restaurant> resSCore = RestaurantManager.searchRestaurantByScore(low, high);
                                if (resSCore.size() > 0) {
                                    showRestaurant(resSCore);
                                } else {
                                    System.out.println("No such restaurant with this score range");
                                }
                                break;
                            case 3:
                                System.out.print("Enter a category to search: ");
                                String category = scanner.nextLine();
                                List<Restaurant> resCat = RestaurantManager.searchRestaurantByCategory(category);
                                if (resCat.size() > 0) {
                                    showRestaurant(resCat);
                                } else {
                                    System.out.println("No such restaurant with this category");
                                }
                                break;
                            case 4:
                                System.out.print("Enter price to search: ");
                                String price = scanner.nextLine();
                                List<Restaurant> resPrice = RestaurantManager.searchRestaurantByPrice(price);
                                if (resPrice.size() > 0) {
                                    showRestaurant(resPrice);
                                } else {
                                    System.out.println("No such restaurant with this price");
                                }
                                break;
                            case 5:
                                System.out.print("Enter ZipCode to search: ");
                                String zip = scanner.nextLine();
                                List<Restaurant> resZip = RestaurantManager.searchRestaurantByZip(zip);
                                if (resZip.size() > 0) {
                                    showRestaurant(resZip);
                                } else {
                                    System.out.println("No such restaurant with this zip code");
                                }
                                break;
                            case 6:
                                System.out.println("Showing Restaurants in the database by different category: ...");
                                Thread.sleep(1000);
                                Map<String, String> map = RestaurantManager.searchRestaurantByDiffCategory();
                                printMappedRestaurant(map);
                                break;
                            case 7:
                                break;
                            default:
                                System.out.println("Error: This  is not a valid operation.");
                                break;
                        }
                        break;

                    case 2:
                        System.out.println("1) By Name");
                        System.out.println("2) By Name in a Given Restaurant");
                        System.out.println("3) By Category");
                        System.out.println("4) By Category in a Given Restaurant");
                        System.out.println("5) By Price Range");
                        System.out.println("6) By Price Range in a Given Restaurant");
                        System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
                        System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                        System.out.println("9) Back to Main Menu");

                        int choice3 = Integer.parseInt(scanner.nextLine());

                        switch (choice3) {
                            case 1:
                                System.out.print("Enter a food name: ");
                                String foodName = scanner.nextLine();
                                List<Food> fName = RestaurantManager.searchFoodByName(foodName);
                                if (fName.size() > 0) {
                                    showFood(fName);
                                } else {
                                    System.out.println("No such food item with this name");
                                }
                                break;

                            case 2:
                                System.out.print("Enter a food name: ");
                                String foodname = scanner.nextLine();
                                System.out.print("Enter a restaurant name: ");
                                String resName = scanner.nextLine();
                                List<Food> fNameRes = RestaurantManager.searchFoodByNameOfRestaurant(foodname, resName);
                                if (fNameRes.size() > 0) {
                                    showFood(fNameRes);
                                } else {
                                    System.out
                                            .println("No such food item with this name on the menu of this restaurant");
                                }
                                break;

                            case 3:
                                System.out.print("Enter a food category: ");
                                String foodcat = scanner.nextLine();
                                List<Food> fCat = RestaurantManager.searchFoodByCategory(foodcat);
                                if (fCat.size() > 0) {
                                    showFood(fCat);
                                } else {
                                    System.out.println("No such food item with this category");
                                }
                                break;

                            case 4:
                                System.out.print("Enter a food category: ");
                                String foodCategory = scanner.nextLine();
                                System.out.print("Enter a restaurant name: ");
                                String res = scanner.nextLine();
                                List<Food> fCatRes = RestaurantManager.searchFoodByCategoryOfRestaurant(foodCategory,
                                        res);
                                if (fCatRes.size() > 0) {
                                    showFood(fCatRes);
                                } else {
                                    System.out.println(
                                            "No such food item with this category on the menu of this restaurant");
                                }
                                break;

                            case 5:
                                System.out.print("Enter lower price: ");
                                double l = Double.parseDouble(scanner.nextLine());
                                System.out.print("Enter upper price: ");
                                double h = Double.parseDouble(scanner.nextLine());
                                List<Food> fPrice = RestaurantManager.searchFoodByPrice(l, h);
                                if (fPrice.size() > 0) {
                                    showFood(fPrice);
                                } else {
                                    System.out.println("No such food item with this price range");
                                }
                                break;

                            case 6:
                                System.out.print("Enter a lower price: ");
                                double low = Double.parseDouble(scanner.nextLine());
                                System.out.print("Enter upper price: ");
                                double high = Double.parseDouble(scanner.nextLine());
                                System.out.print("Enter a restaurant name: ");
                                String rest = scanner.nextLine();
                                List<Food> fPriceRes = RestaurantManager.searchFoodByPriceOfRestaurant(low, high, rest);
                                if (fPriceRes.size() > 0) {
                                    showFood(fPriceRes);
                                } else {
                                    System.out.println(
                                            "No such food item with this price range on the menu of this restaurant");
                                }

                                break;

                            case 7:
                                System.out.print("Enter a restaurant name: ");
                                String R = scanner.nextLine();
                                System.out.println("Showing costliest food in restaurant " + R + "...");
                                Thread.sleep(1000);
                                List<Food> fCostliest = RestaurantManager.costliestFoodOfReataurant(R);
                                if (fCostliest.size() > 0) {
                                    showFood(fCostliest);
                                } else {
                                    System.out.println("There is no food item on the menu.");
                                }
                                break;

                            case 8:
                                System.out.println("Displaying total food of all restaurants...");
                                Thread.sleep(1000);
                                Map<String, Integer> mapFood = RestaurantManager.displayTotalFood();
                                printMappedFood(mapFood);
                                break;

                            case 9:
                                break;
                            default:
                                System.out.println("Error: This is not a valid operation.");
                                break;

                        }
                        break;

                    case 3:
                        System.out.print("Enter Restaurant id: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        boolean isId = (RestaurantManager.isRestaurantValid(id));
                        System.out.print("Enter Restaurant Name: ");
                        String Name = scanner.nextLine();

                        boolean isName = (RestaurantManager.isRestaurantValid(Name));
                        System.out.print("Enter Restaurant Score: ");
                        double score = Double.parseDouble(scanner.nextLine());
                        System.out.print("Enter Restaurant Price: ");
                        String price = scanner.nextLine();
                        System.out.print("Enter Restaurant Zip Code: ");
                        String Z = scanner.nextLine();
                        System.out.print("Enter Categories: ");
                        String restInfo = scanner.nextLine();
                        String[] array = restInfo.split(",", -1);
                        List<String> cateList = new ArrayList<>();
                        for (int i = 0; i < array.length; i++) {
                            if (array[i] != "") {
                                cateList.add(array[i]);
                            }
                            if (i >= 2) {
                                break;
                            }
                        }
                        Restaurant newRestaurant = new Restaurant(id, Name, score, price, Z, cateList);
                        RestaurantManager.addRestaurant(newRestaurant);
                        if (isId == true && isName == true) {
                            System.out.println("Restaurant added successfully");
                            r.add(newRestaurant);
                            change = 1;
                        } else if (isId == false && isName == true) {
                            System.out.println("The Id already exists.");
                        } else if (isId == true && isName == false) {
                            System.out.println("This name already exists.");
                        } else {
                            System.out.println("Both id and name are invalid.");
                        }
                        break;

                    case 4:
                        System.out.println("Enter Restaurant Name: ");
                        String foodRes = scanner.nextLine();
                        boolean isRestaurant = !(RestaurantManager.isRestaurantValid(foodRes));
                        if (isRestaurant) {
                            int foodId = RestaurantManager.getResId(foodRes);
                            System.out.println("Enter Food Category: ");
                            String foodCat = scanner.nextLine();
                            System.out.println("Enter Food Name: ");
                            String foodName = scanner.nextLine();
                            System.out.println("Enter Food Price: ");
                            double foodPrice = Double.parseDouble(scanner.nextLine());
                            Food f = new Food(foodId, foodCat, foodName, foodPrice);
                            int foodAdded = RestaurantManager.addFood(f);
                            if (foodAdded == 1) {
                                System.out.println("Food Added Successfully");
                                F.add(f);
                                change = 1;
                            } else {
                                System.out.println("This food already exists in the restaurant.");
                            }
                        } else {
                            System.out.println("This restaurant does not exist.");
                        }
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("This is not a valid operation");
                        break;
                }
                System.out.println();
            } while (choice != 5);
            System.out.println("Exiting the system....");
            Thread.sleep(1000);
            scanner.close();
            if (change == 1) {
                System.out.println("Updating info...");
                Thread.sleep(1000);
                fileReader.writeFile(F, r);
            }
            System.out.println("Exit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
