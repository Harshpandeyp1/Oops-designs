package strategy;

public class CreditCard implements PaymentStrategy{
    public CreditCard(String holder, String cardNo) {
        this.holder = holder;
        this.cardNo = cardNo;
    }

    private final String holder;
    private final String cardNo;

    @Override
    public boolean pay(double amount) {
        if (cardNo.length() != 16) {
            System.out.println("Invalid card number");
            return false;
        }

        String masked = "**** **** **** " + cardNo.substring(12);
        System.out.println("Paid Rs." + amount + " by card " + masked + " (" + holder + ")");
        return true;
    }
}
