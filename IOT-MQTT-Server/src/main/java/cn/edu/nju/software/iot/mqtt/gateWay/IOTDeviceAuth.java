package cn.edu.nju.software.iot.mqtt.gateWay;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

import javax.annotation.Resource;

import cn.edu.nju.software.iot.shared.mqtt.MQTTClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**  
 * @ClassName: IOTDeviceAuth  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年3月22日  
 *  
 */
@Component
public class IOTDeviceAuth {

    @Autowired
    private MQTTClient client;

    private Set<String> iotDeviceId = new HashSet<>();

    @Resource(name = "mesgFromDeviceToCloudConsumer")
    BiConsumer<String, String> tocloud;

    private final static String DEVICE_PREFIX = "_fromDevice";

    public IOTDeviceAuth(
        @Value("${device.allow-id}") List<String> iotDeviceIds) {
        super();
        iotDeviceId.addAll(iotDeviceIds);
    }

    public void init() {
        for (String iotDeviceId : iotDeviceId) {
            addDeviceId(iotDeviceId);
        }
    }

    public void addDeviceId(String deviceId) {
        iotDeviceId.add(deviceId);
        try {
            client.subscribe(deviceId + DEVICE_PREFIX, tocloud);
        } catch (Exception e) {
            System.err.println(deviceId + "主题订阅失败");
        }
    }

    public void removeDeviceId(String deviceId) {
        iotDeviceId.remove(deviceId);
        try {
            client.unsubscribe(deviceId + DEVICE_PREFIX, tocloud);
        } catch (Exception e) {
            System.err.println(deviceId + "主题取消订阅失败");
        }
    }

    public boolean hasDevice(String deviceId) {
        return iotDeviceId.contains(deviceId);
    }

}
