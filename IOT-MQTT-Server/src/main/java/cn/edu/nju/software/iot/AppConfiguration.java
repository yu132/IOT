package cn.edu.nju.software.iot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import cn.edu.nju.software.iot.gateWay.GateWayMesgHandler;
import cn.edu.nju.software.iot.gateWay.IOTDeviceAuth;
import cn.edu.nju.software.iot.shared.mqtt.MQTTClient;
import cn.edu.nju.software.iot.shared.netty.client.NettyClient;
import cn.hutool.core.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
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
    GateWayMesgHandler gateWayMesgHandler;

    @Bean(name = "mesgFromDeviceToCloudConsumer")
    public BiConsumer<String, String>
        getMesgFromDeviceToCloudConsumer(NettyClient nettyClient) {
        return (topic, str) -> {
            System.out.println("接收到数据：" + topic + " " + str);
            String data = Base64.encode(str);
            topic = topic.replaceAll("_fromDevice", "");
            nettyClient.sendMesg(topic + " " + data);
        };
    }

    @Bean(name = "mesgFromCloudToDeviceConsumer")
    public Consumer<String> getMesgFromCloudToDeviceConsumer(
        IOTDeviceAuth iOTDeviceAuth, MQTTClient mqttClient) {
        return (str) -> {
            String[] sp = str.split(" ");
            if (sp.length < 2) {// 放弃长度小于2的信息
                return;
            }
            String deviceName = sp[0];
            String data = Base64.decodeStr(sp[1]);
            System.out.println("接收到云端数据：" + deviceName + " " + data);
            if (deviceName.equals("gateway")) {
                gateWayMesgHandler.handleMesgFromCloud(data);
            }
            if (!iOTDeviceAuth.hasDevice(deviceName)) {
                return;
            }
            mqttClient.publish(deviceName + "_fromCloud", data);
        };
    }

}
