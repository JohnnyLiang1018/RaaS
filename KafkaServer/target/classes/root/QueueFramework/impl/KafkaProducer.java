package root.QueueFramework.impl;

import org.apache.kafka.common.network.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import root.Entities.DbFetchEO;
import root.Entities.RobotDataEO;
import root.QueueFramework.ProducerInterface;

import javax.annotation.Resource;


@Component
public class KafkaProducer implements ProducerInterface {

    @Autowired
    private KafkaTemplate<String,String> KafkaTemplate;

    @Resource
    private KafkaConsumer kafkaConsumer;

    @Override
    public String sendMsg(RobotDataEO eo) {
        KafkaTemplate.send(eo.getGroupId(),eo.toString());
        return "Success";
    }

    @Override
    public String fetchSignal(DbFetchEO eo) {
        KafkaTemplate.send("fetchSignal",eo.getBatchId());
        return "Success";
    }

    @Override
    public void connect(String s) {
        ListenableFuture<SendResult<String,String>> future = KafkaTemplate.send("connect",s);
        future.addCallback(new ListenableFutureCallback<SendResult<String,String>>(){
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("msg OK");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("msg send failed");
            }
        });
    }
}
