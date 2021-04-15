package cn.edu.nju.software.iot;

import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

import javax.annotation.Resource;

import cn.edu.nju.software.iot.device.MockIOTDevice;
import cn.edu.nju.software.iot.shared.mqtt.MQTTClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**  
 * @ClassName: Application  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@SpringBootApplication
public class MQTTDeviceApplication implements CommandLineRunner {

    @Autowired
    private MQTTClient client;

    @Autowired
    private MockIOTDevice device;

    @Resource(name = "sendTopic")
    private String sendTopic;

    @Resource(name = "receiveTopic")
    private String receiveTopic;

    @Autowired
    private BiConsumer<String, String> callback;

    @Override
    public void run(String... args) throws Exception {
        client.connect();

        client.subscribe(receiveTopic, callback);

        Scanner scan = new Scanner(System.in);
        while (true) {// 模拟10s发送一次传感器数据
            Thread.sleep(10000);
            if (scan.nextInt() == 0)
            	break;
            publishInMqtt(client, device);
        }
        scan.close();
    }

    private void publishInMqtt(MQTTClient client, MockIOTDevice device) {
        List<String> reports = device.getSensorData();
        for (String report : reports) {
            client.publish(sendTopic, report);
            System.out.println("发送信息：" + report);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MQTTDeviceApplication.class, args);
    }

}
