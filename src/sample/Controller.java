package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class Controller {

     static int CountClicks = 0; // стартовое значение || Количество кликов
     static int HowPlusToPow = 1; // стартовое значение || сколько прибавляется после  нажатие  upgrade
     static int HowPlusAutoClick = 1 ; //стартовое значение || сколько прибавиться после  нажатие на AutoClickUpdate
     static int HowPlusAfterUpgradeAutoClick = 1 ;// сколько будет прибавлять после  улучшения AutoClick
     static PriceToBuy pr = new PriceToBuy();
     static Map<Integer,Integer> MapPrice = new HashMap<>(pr.getPrice());
     static ArrayList<Integer> PriceKey = new ArrayList<>();
     static ArrayList<Integer> PriceIncreaseVal = new ArrayList<>();
     static int price  ;
     static int AutoPrice ;
     static int numCount = 1;


    public static  void printArr(){
        for (Map.Entry<Integer, Integer> entry : MapPrice.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
            PriceKey.add(entry.getKey());
            PriceIncreaseVal.add(entry.getValue());

        }
        System.out.println(PriceKey + " это PriceKey");
        System.out.println("////////////////////////");
        System.out.println(PriceIncreaseVal + " это PriceIncreaseVal");

        price = PriceKey.get(1);
        AutoPrice = PriceKey.get(0);
    }




    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button upgradeButton;

    @FXML
    private Button autoClick;

    @FXML
    private Text PowerDance;

    @FXML
    private Text PowerAutoClick;

    @FXML
    private Text Power;

    @FXML
    private ImageView rasDoge;

    @FXML
    void initialize() {
        printArr();
        upgradeButton.setText("улучшить Клик " + PriceKey.get(1));
        autoClick.setText("Улучшить АвтоТанец " + AutoPrice);

        assert upgradeButton != null : "fx:id=\"upgrade\" was not injected: check your FXML file 'sample.fxml'.";
        assert autoClick != null : "fx:id=\"autoClick\" was not injected: check your FXML file 'sample.fxml'.";
        assert PowerDance != null : "fx:id=\"PowerDance\" was not injected: check your FXML file 'sample.fxml'.";
        assert PowerAutoClick != null : "fx:id=\"PowerAutoClick\" was not injected: check your FXML file 'sample.fxml'.";
        assert Power != null : "fx:id=\"Power\" was not injected: check your FXML file 'sample.fxml'.";
        assert rasDoge != null : "fx:id=\"rasDoge\" was not injected: check your FXML file 'sample.fxml'.";


        rasDoge.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CountClicks += HowPlusToPow;

                PowerDance.setText(String.valueOf(HowPlusToPow));
                    if (numCount == 1) {
                        numCount++;

                        new Thread(() -> {
                            while (true) {

                                Power.setText(String.valueOf(CountClicks));
                            }
                        }).start();
                    }



            }
        });
        upgradeButton.setOnAction(event ->{

            btnUpdatePlus(1);

        });

        autoClick.setOnAction(event ->{

        if (CountClicks >= AutoPrice) {
            CountClicks = CountClicks - AutoPrice ;
            AutoPrice = AutoPrice * PriceIncreaseVal.get(0);
            autoClick.setText("Улучшить АвтоТанец " + AutoPrice);
            HowPlusAutoClick += HowPlusAfterUpgradeAutoClick;
            if (numCount == 2) {
                numCount++;
                new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(500);
                            CountClicks += HowPlusAutoClick;
                            PowerAutoClick.setText(String.valueOf(HowPlusAutoClick));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        }


        });


    }

    void btnUpdatePlus(int plus){

        if (CountClicks >= price){
            CountClicks = CountClicks - price ;
            price = price * PriceIncreaseVal.get(1) ;
            System.out.println("damage " + (HowPlusToPow + 1) );
            HowPlusToPow += plus;
            upgradeButton.setText("улучшить Клик " + price);

        }
    }
}
