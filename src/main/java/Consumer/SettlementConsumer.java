package Consumer;

import Model.TradeSettlement;
import jakarta.xml.bind.JAXBContext;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import jakarta.xml.bind.Unmarshaller;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class SettlementConsumer {
    public static void main(String [] args)
    {
        System.out.println("Starting settlement System Consumer");

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");

        props.put(ConsumerConfig.GROUP_ID_CONFIG,"crd-settlement-group");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());

        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        KafkaConsumer <String,String> consumer=new KafkaConsumer<>(props);
        String topicName="trade-settlement-queue";
        consumer.subscribe(Collections.singletonList(topicName));

        System.out.println("Subscribed to topic:"+topicName +"waiting for trades ...\n");

        try{
            JAXBContext context = JAXBContext.newInstance(TradeSettlement.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ConverterXML converter = new ConverterXML();
            while (true){
                ConsumerRecords <String,String> records= consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord <String, String> record :records){
                    System.out.println("========================================");
                    System.out.println("TRADE RECEIVED!");
                    System.out.println("CRD ID (Key): " + record.key());
                    System.out.println("XML Payload:");
                    System.out.println(record.value());
                    System.out.println("========================================\n");

                    converter.Converter(record,unmarshaller);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error in consumer"+e.getMessage());
    } finally {
            consumer.close();
            System.out.println("Consumer closed");
        }
    }
}
