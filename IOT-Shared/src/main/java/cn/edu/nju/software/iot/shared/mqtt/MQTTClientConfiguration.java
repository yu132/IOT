package cn.edu.nju.software.iot.shared.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  
 * @ClassName: MQTTClientConfiguration  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */

@Configuration

@ConditionalOnProperty(prefix = "mqtt", name = "is-mqtt-client",
    havingValue = "true")

public class MQTTClientConfiguration {

    @Bean
    public MQTTClient client(
        @Value("${mqtt.brokerHostAndPort}") String brokerHostAndPort,
        @Value("${mqtt.id}") String clientId,
        @Value("${mqtt.userName}") String userName,
        @Value("${mqtt.passWord}") String passWord) throws MqttException {
        return new MQTTClient(brokerHostAndPort, clientId, userName, passWord);
    }

}
