package network;

//client server ke pathay
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import resources.Food;
import resources.Restaurant;
import utlilities.FoodUtil;
import utlilities.LoginDTO;
import utlilities.NetworkUtil;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;
    Map<String, Restaurant> restaurantMap;
    // Map<String,NetworkUtil> loggedIn;
    // Server server;

    // public ReadThreadServer(HashMap<String, String> map,Map<String,Restaurant>
    // restaurantMap, NetworkUtil networkUtil,Map<String,NetworkUtil>
    // loggedIn,Server server) {
    // this.userMap = map;
    // this.networkUtil = networkUtil;
    // this.restaurantMap = restaurantMap;
    // this.loggedIn = loggedIn;
    // this.server = server;
    // this.thr = new Thread(this);
    // thr.start();
    // }
    public ReadThreadServer(HashMap<String, String> map, Map<String, Restaurant> restaurantMap,
            NetworkUtil networkUtil) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.restaurantMap = restaurantMap;
        // this.loggedIn = loggedIn;s
        // this.server = server;
        this.thr = new Thread(this);
        thr.start();
    }
    // public ReadThreadServer(HashMap<String, String> map, Map<String, Restaurant>
    // restaurantMap,
    // NetworkUtil networkUtil,Map<String,NetworkUtil> loggedIn) {
    // this.userMap = map;
    // this.networkUtil = networkUtil;
    // this.restaurantMap = restaurantMap;
    // this.loggedIn = loggedIn;
    // // this.server = server;
    // this.thr = new Thread(this);
    // thr.start();
    // }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    // if(o instanceof Main)
                    // {
                    // Main main = (Main) o;
                    // System.out.println("Haha");
                    // Server.setLoggedIn(main.getRestaurant().getName(), networkUtil);
                    // }
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println("server theke pathano hoise");
                        String password = userMap.get(loginDTO.getUserName());
                        // System.out.println(loginDTO.getUserName());
                        // System.out.println(password);
                        loginDTO.setRestaurant(restaurantMap.get(loginDTO.getUserName()));
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        networkUtil.write(loginDTO);
                    }
                    // if(o instanceof RestaurantManager)
                    // {
                    // networkUtil.write(main.getRestaurantManager());
                    // }

                    // if (o instanceof Food) {
                    //     Food f = (Food) o;
                    //     System.out.println("Food pathano hoise");
                    //     System.out.println(f.getRestaurantName());
                    //     System.out.println(f);
                    //     System.out.println(Server.getHashMap().isEmpty());
                    //     Map<String, NetworkUtil> loginInfo = Server.getHashMap();
                    //     for (var i : loginInfo.keySet()) {
                    //         System.out.println(i);
                    //     }
                    //     String ID = f.getRestaurantId() + "";
                    //     if (loginInfo.containsKey(ID)) {
                    //         System.out.println("Milse");
                    //         NetworkUtil myNetworkUtil = loginInfo.get(ID);
                    //         System.out.println(f.getRestaurantName());
                    //         try {
                    //             myNetworkUtil.write(f);
                    //         } catch (IOException e) {
                    //             // TODO Auto-generated catch block
                    //             System.out.println("Error dise");
                    //             e.printStackTrace();
                    //         }
                    //     }
                    // }

                    if (o instanceof FoodUtil) {
                        FoodUtil f = (FoodUtil) o;
                        System.out.println("Food pathano hoise");
                        System.out.println(f.getRestaurantName());
                        System.out.println(f);
                        System.out.println(Server.getHashMap().isEmpty());
                        Map<String, NetworkUtil> loginInfo = Server.getHashMap();
                        for (var i : loginInfo.keySet()) {
                            System.out.println(i);
                        }
                        String ID = f.getRestaurantId() + "";
                        System.out.println(ID);
                        if (loginInfo.containsKey(ID)) {
                            System.out.println("Milse");
                            NetworkUtil myNetworkUtil = loginInfo.get(ID);
                            // System.out.println(f.getRestaurantName());
                            try {
                                myNetworkUtil.write(f);
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                System.out.println("Error dise");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
