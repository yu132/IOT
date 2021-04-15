package cn.edu.nju.software.iot;

import java.io.UnsupportedEncodingException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**  
 * @ClassName: MQTTServer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月11日  
 *  
 */
public class Test {

    private MqttClient client;// 服务器相当于broker，此处的Server相对应消息发送方，对于MQTT依然为client

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

    public boolean pubish(String topicName, String mesg) {

        try {
            return publish(topicName, mesg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // "UTF-8"应该是平台都能支持的
        }

        return false;
    }

    public boolean publish(String topicName, String mesg, String charset)
        throws UnsupportedEncodingException {

        MqttMessage message = new MqttMessage();
        message.setQos(1);
        message.setRetained(false);
        message.setPayload(mesg.getBytes(charset));

        MqttTopic topic = client.getTopic(topicName);

        try {
            MqttDeliveryToken token = topic.publish(message);
            token.waitForCompletion();
            return true;
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws MqttException {
        Test server =
            new Test("tcp://127.0.0.1:1883", "s1", "admin", "admin");

        server.pubish("1", "Hello World!");
    }

}
