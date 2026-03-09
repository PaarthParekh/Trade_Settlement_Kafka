package Consumer;

import Model.TradeSettlement;
import jakarta.xml.bind.Unmarshaller;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.StringReader;

public class ConverterXML {

    public void Converter(ConsumerRecord<String,String> record,Unmarshaller unmarshaller) {
        // Feed the raw XML string into a StringReader, then hand it to JAXB unmarshal
        try {
            StringReader reader = new StringReader(record.value());
            TradeSettlement receivedTrade = (TradeSettlement) unmarshaller.unmarshal(reader);

            //Convert to java object
            System.out.println("✅ SUCCESSFULLY CONVERTED BACK TO JAVA OBJECT!");
            System.out.println("Extracted CRD ID:   " + receivedTrade.getTrading().getId());
            System.out.println("Auth Run Name:      " + receivedTrade.getAuthenticate().getOrderRun());
            System.out.println("Order Run Name:     " + receivedTrade.getOrder().getOrderRun());
            System.out.println("Security Run Name:  " + receivedTrade.getSecurity().getOrderRun());
            System.out.println("=========================================\n");
        } catch (Exception e) {
            System.out.println("Failed to parseXml to Java object" + e.getMessage());
        }
    }

}
