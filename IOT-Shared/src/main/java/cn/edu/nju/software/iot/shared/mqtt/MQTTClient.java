package cn.edu.nju.software.iot.shared.mqtt;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**  
 * @ClassName: MQTTClient  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月21日  
 *  
 */
public class MQTTClient {

    private final static int DEFAULT_QOS = 1;

    private final static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private MqttClient client;

    private Map<String, List<BiConsumer<String, String>>> mesgCallback;

    private MqttConnectOptions options;

    public MQTTClient(String brokerHostAndPort, String clientId,
        String userName, String passWord) throws MqttException {

        client = new MqttClient(brokerHostAndPort, clientId,
            new MemoryPersistence());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);

        this.options = options;

        client.setCallback(new MqttCallback() {

            @Override
            public void messageArrived(String topic, MqttMessage message)
                throws Exception {
                for (BiConsumer<String, String> callback : mesgCallback
                    .getOrDefault(topic, Collections.emptyList())) {
                    String mesgPlayload =
                        new String(message.getPayload(), DEFAULT_CHARSET);
                    callback.accept(topic, mesgPlayload);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {}

            @Override
            public void connectionLost(Throwable cause) {}
        });

        mesgCallback = new HashMap<>();
    }

    public void connect() throws MqttException {
        client.connect(options);
    }

    public synchronized void subscribe(String topicName,
        BiConsumer<String, String> callback) throws MqttException {

        if (!mesgCallback.containsKey(topicName)) {
            client.subscribe(topicName, DEFAULT_QOS);
            mesgCallback.put(topicName, new ArrayList<>());
        }

        mesgCallback.get(topicName).add(callback);
    }

    public synchronized void unsubscribe(String topicName,
        BiConsumer<String, String> callback) throws MqttException {

        if (!mesgCallback.containsKey(topicName)) {
            return;
        }

        List<BiConsumer<String, String>> callbacks =
            mesgCallback.get(topicName);
        callbacks.remove(callback);

        if (callbacks.isEmpty()) {
            mesgCallback.remove(topicName);
            client.unsubscribe(topicName);
        }
    }

    public boolean publish(String topicName, String mesg) {

        MqttMessage message = new MqttMessage();
        message.setQos(1);
        message.setRetained(false);
        message.setPayload(mesg.getBytes(DEFAULT_CHARSET));

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

}


