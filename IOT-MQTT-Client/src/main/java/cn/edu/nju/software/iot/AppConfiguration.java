package cn.edu.nju.software.iot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;

import cn.edu.nju.software.iot.device.MockIOTDevice;
import cn.edu.nju.software.iot.device.MockSensor;
import cn.edu.nju.software.iot.device.command.Command;
import cn.edu.nju.software.iot.device.command.CommandMesgParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public List<MockSensor> senosrs() {
        List<MockSensor> sensors = new ArrayList<>();
        sensors.add(new MockSensor() {

            @Override
            public String sensorName() {
                return "temperature";
            }

            @Override
            public String sensorId() {
                return "t-1";
            }

            @Override
            public String getInformation() {
                ThreadLocalRandom r = ThreadLocalRandom.current();
                return String.valueOf(r.nextInt(10) + 25);
            }
        });
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

    @Bean
    public BiConsumer<String, String> consumer(MockIOTDevice device) {
        return (topic, data) -> {
            System.out.println(data);
            Command command = commandMesgParser.parseCommand(data);
            command.doCommand();
        };
    }

}
