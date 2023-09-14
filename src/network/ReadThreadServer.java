package network;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import resources.Restaurant;
import utlilities.FoodPassUtil;
import utlilities.FoodUtil;
import utlilities.LoginDTO;
import utlilities.NetworkUtil;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;
    Map<String, Restaurant> restaurantMap;

    public ReadThreadServer(HashMap<String, String> map, Map<String, Restaurant> restaurantMap,
            NetworkUtil networkUtil) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.restaurantMap = restaurantMap;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());
                        loginDTO.setRestaurant(restaurantMap.get(loginDTO.getUserName()));
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        networkUtil.write(loginDTO);
                    }
                    else if(o instanceof FoodPassUtil)
                    {
                        FoodPassUtil f = (FoodPassUtil) o;

                        Map<String, NetworkUtil> loginInfo = Server.getHashMapCustomer();
                        String ID = f.getUserName();
                        if(loginInfo.containsKey(ID)){
                            NetworkUtil myNetworkUtil = loginInfo.get(ID);
                            try {
                                myNetworkUtil.write(f);
                            } catch (IOException e) {
                                System.out.println("Error");
                                e.printStackTrace();
                            }
                        }
                    }
                    else if (o instanceof FoodUtil) {
                        FoodUtil f = (FoodUtil) o;
                        Map<String, NetworkUtil> loginInfo = Server.getHashMap();
                        String ID = f.getRestaurantId() + "";
                        if (loginInfo.containsKey(ID)) {
                            NetworkUtil myNetworkUtil = loginInfo.get(ID);
                            try {
                                myNetworkUtil.write(f);
                            } catch (IOException e) {
                                System.out.println("Error");
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            System.out.println("This Restaurant is offline");
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
