package network;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import resources.FileOp;
import resources.Food;
import resources.Restaurant;
import resources.RestaurantManager;
import utlilities.LoginDTO;
import utlilities.NetworkUtil;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public List<Restaurant> restaurantList;
    public List<Food> foodList;
    RestaurantManager restaurantManager;
    Map<String, Restaurant> restaurantMap;
    public static Map<String, NetworkUtil> loggedIn;

    public static void setLoggedIn(String name, NetworkUtil myNetworkUtil) {
        loggedIn.put(name, myNetworkUtil);
        System.out.println(name + " logged in");
        if (myNetworkUtil == null) {
            System.out.println("Null");
        } else {
            System.out.println("Not Null");
        }
        for (String key : loggedIn.keySet()) {
            System.out.println("Key: " + key);
        }
        System.out.println(loggedIn.isEmpty());
    }

    public static Map<String, NetworkUtil> getHashMap() {
        return loggedIn;
    }

    Server() {
        // userMap = new HashMap<>();
        // userMap.put("a", "a");
        // userMap.put("b", "b");
        // userMap.put("c", "c");
        // userMap.put("d", "d");
        // userMap.put("e", "e");

        userMap = FileOp.readRestaurantPass();

        for (String s : userMap.keySet()) {
            System.out.println(s + " " + userMap.get(s));
        }
        loggedIn = new HashMap<>();

        // loggedIn = ;

        // FileOp fileOp = new FileOp();
        // restaurantList = fileOp.fileRestaurant();
        // foodList = fileOp.fileFood();
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
        // loggedIn = new HashMap<>();

        System.out.println(loggedIn.isEmpty());

        // file theke input nibo
        // map e Restaurant er password ar username put kore dibo

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
                new ReadThreadServer(userMap, restaurantMap, networkUtil);
            } else {
                // System.out.println("Inside Server");
                // String name = (String) networkUtil.read();
                // String password = (String) networkUtil.read();
                // System.out.println(name);
                // System.out.println(password);

                // if (userMap.containsKey(name)) {
                // if (userMap.get(name).equals(password)) {
                // setLoggedIn(name, networkUtil);
                networkUtil.write(restaurantManager);
                LoginDTO loginDTO = (LoginDTO) networkUtil.read();
                setLoggedIn(loginDTO.getUserName(), networkUtil);
                System.out.println("This is restaurant");
                new ReadThreadServer(userMap, restaurantMap, networkUtil);
                // }
                // } else {
                // System.out.println("Invalid Login");
            }

        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
