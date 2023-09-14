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
    private final ClientCustomer main;

    public ReadThreadCustomer(ClientCustomer app) {
        this.main = app;
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
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        main.showHomePage(loginDTO.getUserName());
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
                        main.updateFoodList(foodPassUtil.getFood());
                        
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
