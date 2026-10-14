package strategy;

public class UpiPayment implements PaymentStrategy {
    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    private final String upiId;


    @Override
    public boolean pay(double amount){
        if(!upiId.contains("@")){
            System.out.println("invalid id");
            return false;
        }
        System.out.println("paid Rs"+amount+"Upi id"+upiId);
        return true;
    }
}
