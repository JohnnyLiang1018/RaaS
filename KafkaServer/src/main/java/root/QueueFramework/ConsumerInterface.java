package root.QueueFramework;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
public interface ConsumerInterface {
    void dataListen(ConsumerRecord<?,?> record);
    void connectListen(ConsumerRecord<?,?> record);
}
