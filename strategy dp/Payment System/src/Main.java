import cart.ShoppingCart;
import model.Item;
import strategy.CreditCard;
import strategy.UpiPayment;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ShoppingCart cart=new ShoppingCart();
        cart.addItem(new Item("keyboard",1500));
        cart.addItem(new Item("bidi",5));


        cart.checkOut(new UpiPayment("HarshPandey@22"));

        cart.addItem(new Item("aalu",200));
        cart.checkOut(new CreditCard("harsh","7004201625"));
    }
}