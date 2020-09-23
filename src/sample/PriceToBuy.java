package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PriceToBuy {
    static Map<Integer,Integer> Price = new HashMap<>();


    public static Map<Integer, Integer> getPrice() {
        createList();
        return Price;
    }

    public static void setPrice(Map<Integer, Integer> map) {
        PriceToBuy.Price = map;
    }

    public static void createList(){
        Price.put(10,2);    // цена обновления клика || Рост цены после клика
        Price.put(100,4);  // цена обновения автоКлика || Рост цены после клика

    }
}
