package example;

import java.util.HashMap;
import java.util.Map;

public class Items {

    private Items() {}

    public static Map<String, Double> getPage1Items() {
        Map<String, Double> map = new HashMap<>();
        map.put("Potato Sack", 10.0);
        map.put("Apples", 5.0);
        map.put("Tomatoes", 5.0);
        return map;
    }

    public static Map<String, Double> getPage2Items() {
        Map<String, Double> map = new HashMap<>();
        map.put("Milk", 10.0);
        map.put("Yogurt", 15.0);
        map.put("Eggs", 20.0);
        return map;
    }
}
