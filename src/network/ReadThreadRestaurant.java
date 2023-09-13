package network;

//client(Restaurant) ke ptahay server theke
import javafx.application.Platform;
import resources.Food;
import resources.RestaurantManager;
import utlilities.FoodUtil;
import utlilities.FoodUtilObject;
import utlilities.LoginDTO;

import java.io.IOException;

import app.ClientRestaurant;

public class ReadThreadRestaurant implements Runnable {
    private final Thread thr;
    private final ClientRestaurant mainR;
    // private final App main;

    public ReadThreadRestaurant(ClientRestaurant main) {
        this.mainR = main;
        // this.main = null;
        this.thr = new Thread(this);
        thr.start();
    }

    // public ReadThreadRestaurant(App app) {
    //     // this.main = app;
    //     this.mainR = null;
    //     this.thr = new Thread(this);
    //     thr.start();
    // }

    public void run() {
        try {
            while (true) {
                // if (main != null) {
                //     Object o = main.getNetworkUtil().read();
                //     if (o != null) {
                //         if (o instanceof LoginDTO) {
                //             LoginDTO loginDTO = (LoginDTO) o;
                //             System.out.println(loginDTO.getUserName());
                //             System.out.println(loginDTO.isStatus());
                //             System.out.println("loginDTO");
                //             // the following is necessary to update JavaFX UI components from user created
                //             // threads
                //             Platform.runLater(new Runnable() {
                //                 @Override
                //                 public void run() {
                //                     if (loginDTO.isStatus()) {
                //                         try {
                //                             main.showHomePage(loginDTO.getUserName()); // ekhane object pass korse
                //                         } catch (Exception e) {
                //                             e.printStackTrace();
                //                         }
                //                     } else {
                //                         main.showAlert();
                //                     }

                //                 }
                //             });
                //         }
                //         if (o instanceof RestaurantManager) {
                //             RestaurantManager rManager = (RestaurantManager) o;
                //             main.restaurantManager = rManager;
                //         }
                //         if (o instanceof Food) {
                //             Food f = (Food) o;
                //             if(mainR.getRestaurant().getId()==f.getRestaurantId())
                //             {
                //                 mainR.homeController.updateOrder(f);
                //                 System.out.println("Is Calling...(ig)");
                //             }
                            
                //         }
                        

                //     }
                // }
                // if (mainR != null) {
                    Object r = mainR.getNetworkUtil().read();
                    if (r != null) {
                        if (r instanceof LoginDTO) {
                            LoginDTO loginDTO = (LoginDTO) r;
                            // System.out.println(loginDTO.getUserName());
                            // System.out.println(loginDTO.isStatus());
                            // System.out.println("loginDTO");
                            // the following is necessary to update JavaFX UI components from user created
                            // threadsd
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if (loginDTO.isStatus()) {
                                        try {
                                            mainR.setRestaurantByName(loginDTO.getUserName());
                                            mainR.showHomePage(loginDTO); // ekhane object pass korse
                                            // Server.setLoggedIn(loginDTO.getRestaurant().getName(), mainR.getNetworkUtil());
                                            System.out.println("Haha");
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        mainR.showAlert();
                                    }

                                }
                            });
                        }
                        // if (r instanceof Food) {
                        //     Food f = (Food) r;
                        //     System.out.println("Nice");
                        //     if(mainR.getRestaurant().getId()==f.getRestaurantId())
                        //     {
                        //         mainR.homeController.updateOrder(f);
                        //         System.out.println("Is Calling...(ig)");
                        //     }
                            
                        // }

                        if (r instanceof FoodUtil) {
                            FoodUtil f = (FoodUtil) r;
                            System.out.println("Nice");
                            FoodUtilObject F = new FoodUtilObject(f);
                            if(mainR.getRestaurant().getId()==F.getRestaurantId())
                            {
                                mainR.homeController.updateOrder(F);
                                System.out.println("Is Calling...(ig)");
                            }
                            
                        }

                        if(r instanceof RestaurantManager)
                        {
                            RestaurantManager rManager = (RestaurantManager) r;
                            mainR.restaurantManager = rManager;
                        }

                    }
                // }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                // if (main != null) {
                //     main.getNetworkUtil().closeConnection();
                // } else 
                if (mainR != null) {
                    mainR.getNetworkUtil().closeConnection();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
