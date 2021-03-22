package cn.edu.nju.software.iot;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**  
 * @ClassName: MQTTClient  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月11日  
 *  
 */
public class Test {

    private MqttClient client;

    public Test(String brokerHostAndPort, String clientId,
        String userName, String passWord) throws MqttException {

        client = new MqttClient(brokerHostAndPort, clientId,
            new MemoryPersistence());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);

        client.connect(options);
    }

    public void subscribe(List<String> topicNames,
        BiConsumer<String, MqttMessage> topicAndMesgConsumer)
        throws MqttException {

        for (String topicName : topicNames) {
            client.subscribe(topicName, 1);
        }

        client.setCallback(new MqttCallback() {

            @Override
            public void messageArrived(String topic, MqttMessage message)
                throws Exception {
                topicAndMesgConsumer.accept(topic, message);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {}

            @Override
            public void connectionLost(Throwable cause) {}
        });
    }

    public static void main(String[] args) throws Exception {
        Test server =
            new Test("tcp://127.0.0.1:1883", "c1", "admin", "admin");

        server.subscribe(Arrays.asList("1"), (t, m) -> {
            switch (t) {
                case "1":
                    try {
                        System.out.println(new String(m.getPayload(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });

    }

}
