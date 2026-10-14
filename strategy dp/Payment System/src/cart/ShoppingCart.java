package cart;

import model.Item;
import strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }
    public double getTotal(){
        double total=0;
        for(Item item:items){
            total+= item.getPrice();
        }
        return total;
    }
    public void checkOut(PaymentStrategy strategy){
        if (items.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        boolean success=strategy.pay(getTotal());
        if(success){
            items.clear();
            System.out.println("order placed");
        }else{
            System.out.println("order failed");
        }
    }
}
