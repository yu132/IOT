package cn.edu.nju.software.iot;

import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

import javax.annotation.Resource;

import cn.edu.nju.software.iot.mqtt.gateWay.IOTDeviceAuth;
import cn.edu.nju.software.iot.shared.mqtt.MQTTClient;
import cn.edu.nju.software.iot.shared.netty.client.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**  
 * @ClassName: MQTTstarter  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2021年4月1日  
 *  
 */
@Component
public class MQTTStarter {

    @Autowired
    private MQTTClient mqttClient;

    @Autowired
    private NettyClient nettyClient;

    @Autowired
    private IOTDeviceAuth auth;

    @Resource(name = "mesgFromCloudToDeviceConsumer")
    private Consumer<String> todevice;

    public void run() throws Exception {
        try {
            mqttClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        auth.init();

        nettyClient.connect();

        nettyClient.setMesgFromColudServerHandler(todevice);

        LockSupport.park();
    }

}
