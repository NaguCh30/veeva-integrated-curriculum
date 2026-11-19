
package dummydata;

import service.VendingMachineService;

import java.util.HashMap;
import java.util.Map;

public class DummyDataLoader {

    public static void load(VendingMachineService service) throws Exception {

        // initial products data
        service.addProduct(1, "Soda", 40.0, 10);
        service.addProduct(2, "Chips", 20.0, 15);
        service.addProduct(3, "Chocolate", 30.0, 12);
        service.addProduct(4, "Water Bottle", 20.0, 20);
        service.addProduct(5, "Biscuits", 25.0, 10);
        service.addProduct(6, "Juice", 50.0, 8);
        service.addProduct(7, "Cookies", 35.0, 10);
        service.addProduct(8, "Energy Drink", 60.0, 5);


        // initial cash inventory
        Map<Integer, Integer> cashDenominations = new HashMap<>();

        cashDenominations.put(500, 2);
        cashDenominations.put(200, 5);
        cashDenominations.put(100, 10);
        cashDenominations.put(50, 10);
        cashDenominations.put(20, 20);
        cashDenominations.put(10, 30);
        cashDenominations.put(5, 20);
        cashDenominations.put(2, 20);
        cashDenominations.put(1, 20);

        service.addCashInventory(cashDenominations);
    }
}
