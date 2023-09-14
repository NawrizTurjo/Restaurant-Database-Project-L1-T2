package network;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import resources.FileOp;
import resources.Food;
import resources.Restaurant;
import resources.RestaurantManager;
import utlilities.LoginDTO;
import utlilities.NetworkUtil;

public class Server{

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public List<Restaurant> restaurantList;
    public List<Food> foodList;
    public RestaurantManager restaurantManager;
    Map<String, Restaurant> restaurantMap;
    public static Map<String, NetworkUtil> loggedIn;

    public static void setLoggedIn(String name, NetworkUtil myNetworkUtil) {
        loggedIn.put(name, myNetworkUtil);
        System.out.println(name + " logged in");
    }

    public static Map<String, NetworkUtil> getHashMap() {
        return loggedIn;
    }

    public static Map<String, NetworkUtil> customerLoggedIn;

    public static void customerLoggedIn(String name, NetworkUtil myNetworkUtil) {
        loggedIn.put(name, myNetworkUtil);
        System.out.println(name + " logged in");
    }

    public static Map<String, NetworkUtil> getHashMapCustomer() {
        return loggedIn;
    }

    Server() {
        userMap = FileOp.readRestaurantPass();

        loggedIn = new HashMap<>();

        restaurantManager = new RestaurantManager("Restaurant Manager");

        FileOp fileReader = new FileOp();
        List<Restaurant> r = fileReader.fileRestaurant();
        List<Food> F = fileReader.fileFood();
        for (var i : r) {
            restaurantManager.addRestaurant(i);
        }
        for (var i : F) {
            restaurantManager.addFood(i);
        }

        restaurantMap = new HashMap<>();

        for (Restaurant i : restaurantManager.getRestaurants()) {
            restaurantMap.put(i.getId().toString(), i);
        }

        System.out.println(loggedIn.isEmpty());

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        boolean isCustomer = (boolean) networkUtil.read();
        {
            if (isCustomer) {
                networkUtil.write(restaurantManager);
                System.out.println("This is customer");
                String name = (String) networkUtil.read();
                customerLoggedIn(name,networkUtil);
                new ReadThreadServer(userMap, restaurantMap, networkUtil);
            } else {
                networkUtil.write(restaurantManager);
                LoginDTO loginDTO = (LoginDTO) networkUtil.read();
                setLoggedIn(loginDTO.getUserName(), networkUtil);
                System.out.println("This is restaurant");
                new ReadThreadServer(userMap, restaurantMap, networkUtil);
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }

}
