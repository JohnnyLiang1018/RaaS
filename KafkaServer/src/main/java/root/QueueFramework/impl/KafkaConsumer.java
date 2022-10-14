package root.QueueFramework.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import root.QueueFramework.ConsumerInterface;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Component
public class KafkaConsumer implements ConsumerInterface {

    public static int edgeCount = 2;
    public static int received = 0;

    // key: user id, value: list of robot cluster id
    private Map<String, List<String>> utilizedCluster = new HashMap<>();

    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Override
    @KafkaListener(topics =  {"robot"})
    public void dataListen(ConsumerRecord<?,?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if(kafkaMessage.isPresent()){
            List<String> msg = parse(kafkaMessage.get());
            logger.info("Received message: " + msg);
        }
    }

    @Override
    @KafkaListener(topics = {"connect"})
    public void connectListen(ConsumerRecord<?,?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if(kafkaMessage.isPresent()){
            List<String> msg = parse(kafkaMessage.get());
            if(utilizedCluster.containsKey(msg.get(0))){
                utilizedCluster.get(msg.get(0)).add(msg.get(1));
            }
            else{
                utilizedCluster.put(msg.get(0),Collections.singletonList(msg.get(1)));
            }
        }
    }

    private List<String> parse(Object msg){
        List<String> output = new ArrayList<>();
        String s = msg.toString();
        int p = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == ','){
                output.add(s.substring(p,i));
                p = i+1;
            }
        }
        return output;
    }
}
