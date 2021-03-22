package cn.edu.nju.software.iot;

import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

import javax.annotation.Resource;

import cn.edu.nju.software.iot.gateWay.IOTDeviceAuth;
import cn.edu.nju.software.iot.shared.mqtt.MQTTClient;
import cn.edu.nju.software.iot.shared.netty.client.NettyClient;
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
public class GateWayApplication implements CommandLineRunner {

    @Autowired
    private MQTTClient mqttClient;

    @Autowired
    private NettyClient nettyClient;

    @Autowired
    private IOTDeviceAuth auth;

    @Resource(name = "mesgFromCloudToDeviceConsumer")
    private Consumer<String> todevice;

    @Override
    public void run(String... args) throws Exception {
        mqttClient.connect();

        auth.init();

        nettyClient.connect();

        nettyClient.setMesgFromColudServerHandler(todevice);

        LockSupport.park();
    }

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

}
