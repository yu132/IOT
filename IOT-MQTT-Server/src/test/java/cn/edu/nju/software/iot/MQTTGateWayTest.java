package cn.edu.nju.software.iot;

import java.util.concurrent.locks.LockSupport;

import cn.edu.nju.software.iot.shared.mqtt.MQTTClient;
import cn.edu.nju.software.iot.shared.netty.client.NettyClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**  
 * @ClassName: MQTTGateWayTest  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQTTGateWayTest {

    @Autowired
    MQTTClient client;

    @Test
    public void test() throws MqttException {
        client.connect();

        client.subscribe("s1", (topic, data) -> {
            System.out.println("网关接收到数据 " + topic + ":" + data);
        });

        LockSupport.park();
    }

}
