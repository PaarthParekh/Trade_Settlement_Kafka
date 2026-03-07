import Producer.TradePublisher; // <--- Import from your new folders
import Model.TradeSettlement;
import Model.AttributeId;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TradePublisher publisher= new TradePublisher();
        Scanner scanner =new Scanner(System.in);
        int tradeCounter=1;

        while (true) {

            //Get the Security Run Name (Text)
            System.out.print("Enter Security Run Name: ");
            String secRun = scanner.nextLine();
            if (secRun.equalsIgnoreCase("exit")) break;

            System.out.print("Enter Quantity: ");
            String secQuantity = scanner.nextLine();
            if (secQuantity.equalsIgnoreCase("exit")) break;

            AttributeId auth = new AttributeId("Authentication password='xyz'");
            AttributeId trade = new AttributeId(tradeCounter, "trade_details");
            AttributeId order = new AttributeId(tradeCounter+1, "Order_OMS");
            AttributeId sec = new AttributeId(tradeCounter+2, secRun+" "+secQuantity);

            tradeCounter+=3;

            TradeSettlement newTrade = new TradeSettlement(auth, trade,order, sec);
            publisher.publishTrade(newTrade);
        }
        publisher.shutdown();
        scanner.close();
    }
}