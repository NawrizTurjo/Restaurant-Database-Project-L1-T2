package network;

//client ke ptahay server theke
import javafx.application.Platform;
import resources.RestaurantManager;
import utlilities.FoodPassUtil;
import utlilities.LoginDTO;

import java.io.IOException;

import app.ClientCustomer;

public class ReadThreadCustomer implements Runnable {
    private final Thread thr;
    // private final Main mainR;
    private final ClientCustomer main;

    // public ReadThread(Main main) {
    // this.mainR = main;
    // this.main = null;
    // this.thr = new Thread(this);
    // thr.start();
    // }

    public ReadThreadCustomer(ClientCustomer app) {
        this.main = app;
        // this.mainR = null;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        System.out.println("loginDTO");
                        // the following is necessary to update JavaFX UI components from user created
                        // threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        // loginDTO.setRestaurant();
                                        main.showHomePage(loginDTO.getUserName()); // ekhane object pass korse
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }
                    if (o instanceof RestaurantManager) {
                        RestaurantManager rManager = (RestaurantManager) o;
                        main.restaurantManager = rManager;
                    }
                    if(o instanceof FoodPassUtil){
                        FoodPassUtil foodPassUtil = (FoodPassUtil) o;
                        System.out.println(foodPassUtil.getFood());
                        main.updateFoodList(foodPassUtil.getFood());
                        // main.orderCustomerSideController.updateFoodOrderList(foodPassUtil.getFood());
                        
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (main != null) {
                    main.getNetworkUtil().closeConnection();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
