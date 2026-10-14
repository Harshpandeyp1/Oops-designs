package strategy;

public class NetBanking implements PaymentStrategy{
    public NetBanking(String netBankingId) {
        NetBankingId = netBankingId;
    }

    private final String NetBankingId;

    @Override
    public boolean pay(double amount) {
        if(!NetBankingId.contains("@")){
            System.out.println("invalid id");
            return false;
        }
      System.out.println("money deducted from bank");
        return true;
    }
}
