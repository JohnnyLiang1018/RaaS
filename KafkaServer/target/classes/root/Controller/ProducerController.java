package root.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import root.Entities.DbFetchEO;
import root.Entities.RobotDataEO;
import root.QueueFramework.ProducerInterface;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
@EnableAutoConfiguration
public class ProducerController {

    @Resource
    private ProducerInterface producerInterface;

    @RequestMapping("/")
    @ResponseBody
    public String hello() { return "Hello World";}

    @PostMapping("/send")
    @ResponseBody
    public String send(@RequestBody RobotDataEO eo){
        return producerInterface.sendMsg(eo);
    }

    @PostMapping("/connect")
    public void connect(@RequestBody String entity) { producerInterface.connect(entity); }

    @PostMapping("/batch")
    @ResponseBody
    public String batchSignal(@RequestBody DbFetchEO eo) { return producerInterface.fetchSignal(eo); }
}
