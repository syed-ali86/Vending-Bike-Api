package testRunner;

import org.junit.Test;
import com.collinson.qa.exceptions.NotSufficientChangeException;
import com.collinson.qa.exceptions.SoldOutException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import vendingmachine.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MachineTest {

    private static VendingMachine vm;

    @BeforeClass
    public static void setUp() {

        vm = VendingMachineFactory.createVendingMachine();
    }

    @AfterClass
    public static void tearDown() {

        vm = null;
    }

    @Test
    @DisplayName("Accepts coins of 1,5,10,25 Cents i.e. penny, nickel, dime, and quarter")
    public void acceptingCoins(){
        vm.insertCoin(Coin.QUARTER);
        vm.insertCoin(Coin.DIME);
        vm.insertCoin(Coin.NICKLE);
        vm.insertCoin(Coin.PENNY);
        Inventory<Coin> coin2 = vm.getCashInventory();
        Assertions.assertAll(()->assertEquals(6,coin2.getQuantity(Coin.QUARTER)),
                ()->assertEquals(6,coin2.getQuantity(Coin.DIME)),
                ()->assertEquals(6,coin2.getQuantity(Coin.NICKLE)),
                ()->assertEquals(6,coin2.getQuantity(Coin.PENNY))
        );
        vm.reset();
        vm.initialize();
    }


    @Test
    @DisplayName("Allow user to select products Coke(25), Pepsi(35), Soda(45)")
    public void selectProduct(){
        long coke = vm.selectItemAndGetPrice(Item.COKE);
        long pepsi = vm.selectItemAndGetPrice(Item.PEPSI);
        long soda = vm.selectItemAndGetPrice(Item.SODA);

        Assertions.assertAll(()->assertEquals(25,coke),
                ()-> assertEquals(35,pepsi),
                ()->assertEquals(45,soda)
                );
    }

    @Test
    @DisplayName("Allow user to take refund by cancelling the request")
    public void refundByCancel(){
        long price = vm.selectItemAndGetPrice(Item.PEPSI);
        vm.insertCoin(Coin.QUARTER);
        Assertions.assertAll(()-> assertEquals(Item.PEPSI.getPrice(), price),
                ()->assertEquals(25, getTotal(vm.refund())
                ));


    }

    @Test
    @DisplayName("Return selected product and remaining change if any")
    public void productAndChange(){
        long price = vm.selectItemAndGetPrice(Item.COKE);
        //assertEquals(Item.PEPSI.getPrice(), price);
        vm.insertCoin(Coin.DIME);
        vm.insertCoin(Coin.DIME);
        vm.insertCoin(Coin.PENNY);
        vm.insertCoin(Coin.NICKLE);
        Bucket<Item, List<Coin>> bucket = vm.collectItemAndChange();
        Item item = bucket.getFirst();
        List<Coin> change = bucket.getSecond();
        Assertions.assertAll(()->assertEquals(Item.COKE,item),
                ()->assertEquals(1,change.get(0).getDenomination())
                );



    }

    @Test
    @DisplayName("Allow reset operation for vending machine supplier")
    public void resetMachine(){

        Inventory<Coin> coin = vm.getCashInventory();
        Inventory<Item>  items = vm.getItemInventory();
        productAndChange();
        vm.reset();
        Inventory<Coin> coin2 = vm.getCashInventory();
        Inventory<Item>  items2 = vm.getItemInventory();
        Assertions.assertAll(
                ()->assertEquals(0,coin2.getQuantity(Coin.QUARTER)),
                ()->assertEquals(0,coin2.getQuantity(Coin.DIME)),
                ()->assertEquals(0,coin2.getQuantity(Coin.NICKLE)),
                ()->assertEquals(0,coin2.getQuantity(Coin.PENNY)),
                ()->assertEquals(0,items.getQuantity(Item.COKE)),
                ()->assertEquals(0,items.getQuantity(Item.PEPSI)),
                ()->assertEquals(0,items.getQuantity(Item.SODA))
        );
        vm.initialize();
                Assertions.assertAll(
                ()->assertEquals(5,coin2.getQuantity(Coin.QUARTER)),
                ()->assertEquals(5,coin2.getQuantity(Coin.DIME)),
                ()->assertEquals(5,coin2.getQuantity(Coin.NICKLE)),
                ()->assertEquals(5,coin2.getQuantity(Coin.PENNY)),
                ()->assertEquals(5,items.getQuantity(Item.COKE)),
                ()->assertEquals(5,items.getQuantity(Item.PEPSI)),
                ()->assertEquals(5,items.getQuantity(Item.SODA))
                );
    }



    private long getTotal(List<Coin> change) {

        long total = 0;
        for (Coin c : change) {
            total = total + c.getDenomination();
        }
        return total;
    }
}