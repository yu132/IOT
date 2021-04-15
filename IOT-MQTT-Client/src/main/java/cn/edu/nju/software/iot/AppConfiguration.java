package cn.edu.nju.software.iot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import cn.edu.nju.software.iot.device.MockIOTDevice;
import cn.edu.nju.software.iot.device.command.Command;
import cn.edu.nju.software.iot.device.command.CommandMesgParser;
import cn.edu.nju.software.iot.device.sensor.BrightnessSensor;
import cn.edu.nju.software.iot.device.sensor.MockSensor;
import cn.edu.nju.software.iot.device.sensor.PressureSensor;
import cn.edu.nju.software.iot.device.sensor.TemperatureSensor;

/**  
 * @ClassName: AppConfiguration  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@Configuration
public class AppConfiguration {

    @Autowired
    private CommandMesgParser commandMesgParser;

    @Bean
    @ConditionalOnProperty(prefix = "device", name = "is-sensor",
        havingValue = "true")
    public List<MockSensor> sensors() {
        List<MockSensor> sensors = new ArrayList<>();
        sensors.add(new BrightnessSensor());
        sensors.add(new TemperatureSensor());
        sensors.add(new PressureSensor());
        return sensors;
    }

    @Bean(name = "sendTopic")
    public String sendTopic(@Value("${mqtt.id}") String id) {
        return id + "_fromDevice";
    }

    @Bean(name = "receiveTopic")
    public String receiveTopic(@Value("${mqtt.id}") String id) {
        return id + "_fromCloud";
    }

    @Bean(name = "httpUrl")
    public String httpUrl(@Value("${http.ip}") String ip,
        @Value("${http.port}") String port) {
        return "http://" + ip + ":" + port + "/";
    }

    @Bean(name = "postDataUrl")
    public String postDataUrl(String httpUrl) {
        return httpUrl + "post-data?deviceId={1}&data={2}";
    }

    @Bean(name = "getDataUrl")
    public String getDataUrl(String httpUrl) {
        return httpUrl + "get-data?deviceId={1}";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public BiConsumer<String, String> consumer(MockIOTDevice device) {
        return (topic, data) -> {
            System.out.println(data);
            Command command = commandMesgParser.parseCommand(data);
            command.doCommand();
        };
    }

}
