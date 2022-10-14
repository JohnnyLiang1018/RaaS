package root.QueueFramework;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import root.Entities.DbFetchEO;
import root.Entities.RobotDataEO;

@Component
public interface ProducerInterface {
    String sendMsg(RobotDataEO eo);
    String fetchSignal(DbFetchEO eo);
    void connect(String s);
}
