package network;

import javafx.application.Platform;
import resources.RestaurantManager;
import utlilities.FoodUtil;
import utlilities.FoodUtilObject;
import utlilities.LoginDTO;

import java.io.IOException;

import app.ClientRestaurant;

public class ReadThreadRestaurant implements Runnable {
    private final Thread thr;
    private final ClientRestaurant mainR;

    public ReadThreadRestaurant(ClientRestaurant main) {
        this.mainR = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object r = mainR.getNetworkUtil().read();
                if (r != null) {
                    if (r instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) r;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        mainR.setRestaurantByName(loginDTO.getUserName());
                                        mainR.showHomePage(loginDTO);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    mainR.showAlert();
                                }

                            }
                        });
                    }
                    else if (r instanceof FoodUtil) {
                        FoodUtil f = (FoodUtil) r;
                        FoodUtilObject F = new FoodUtilObject(f);
                        if (mainR.getRestaurant().getId() == F.getRestaurantId()) {
                            mainR.homeController.updateOrder(F);
                        }

                    }

                    else if (r instanceof RestaurantManager) {
                        RestaurantManager rManager = (RestaurantManager) r;
                        mainR.restaurantManager = rManager;
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (mainR != null) {
                    mainR.getNetworkUtil().closeConnection();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
