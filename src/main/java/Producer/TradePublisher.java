package Producer;

import Model.TradeSettlement;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.StringWriter;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;


public class TradePublisher {
    private KafkaProducer<String, String > producer;
    private Marshaller marshaller;
    private final String topicName="trade-settlement-queue";

    public TradePublisher(){
        try {
            Properties props= new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            this.producer=new KafkaProducer<>(props);
            JAXBContext context=JAXBContext.newInstance(TradeSettlement.class);
            this.marshaller=context.createMarshaller();
            this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publishTrade( TradeSettlement trade)
    {
        try{
            StringWriter writer= new StringWriter();
            marshaller.marshal(trade, writer);

            String finalXml=writer.toString();
            String messageKey= String.valueOf(trade.getTrading().getId());
            ProducerRecord <String, String> record = new ProducerRecord<>(topicName,messageKey,finalXml);

            producer.send(record);
            System.out.println("-> Successfully published trade CRD ID: " + messageKey);
        } catch (Exception e) {
            System.out.println("Failed to publish trade" + e.getMessage());
        }
    }

    public void shutdown(){
        producer.flush();
        producer.close();
    }
}
